package main;

import gui.Stage;
import gui.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import world.World;
import cube.Face;
import cube.RCube;
import cube.Turn;

public class God {

	private static World world;
	private static RCube cube;
	private static UserInterface userInterface;


	public static void setWorld(World newWorld) {
		world = newWorld;		
	}

	public static void setCube(RCube newCube) {
		cube = newCube;		
	}
	
	public static void setInterface(UserInterface newUserInterface) {
		userInterface = newUserInterface;
	}

	public static void performTurns(ArrayList<Turn> turns) {
		world.performTurns(turns);
	}

	public static RCube getCube() {
		return cube;
	}
	
	public static void setStage(Stage stage) {
		userInterface.setStage(stage);
	}
	
	public static Stage getStage() {
		return userInterface.getStage();
	}

	/**
	 * Generates a random string from a list of characters 
	 * chosen to represent each face of the cube.
	 * @param length
	 * @return
	 */
	public static String generateString(int length) {
		String characters = "FBUDLR";
		Random rng = new Random();
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++) {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}

	/**
	 * Creates a list of Turns from a string of characters. The string will be a list 
	 * of initials to represent each face of the cube, optionally followed by a ' to indicate 
	 * and inverse turn.
	 * FIXME currently will error if ' is present at the beginning, also do other sanitisation
	 * @param turns
	 * @return
	 */
	public static ArrayList<Turn> parseTurnsFromString(String turns) {
		turns = turns.replaceAll(" ", "");
		ArrayList<Turn> turnsToReturn = new ArrayList<Turn>();
		
	
		for (int i = turns.length() - 1; i >= 0; i--) {
			boolean inverseTurn = false;
			if (turns.charAt(i) == '\'') {
				//prime turn
				inverseTurn = true;
				i--;
			}
			turnsToReturn.add(0,new Turn(
					Face.getFaceFromChar(turns.charAt(i)), 
					inverseTurn, 
					0
					));
		}
		return turnsToReturn;
	}
	
	/**
	 * Checks if two arrays are equal.
	 * Note: Arrays.equal is not used because it uses equal to check equality (not ==). 
	 * This means it does not think 0 and -0 are the same.
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean arrayEquals(float[] a, float[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == -0) {
				a[i] = 0;
			}
			if (b[i] == -0) {
				b[i] = 0;
			}
		}
		return Arrays.equals(a, b);
	}

	/**
	 * Returns the number with the highest absolute value.
	 * @param a
	 * @param b
	 * @return
	 */
	public static float orOfTwoNumbers(float a, float b) {
		if (Math.abs(a) > Math.abs(b)) {
			return a;
		} else {
			return b;
		}
	}
	
	public static float orOfThreeNumbers(float a, float b, float c) {
		return orOfTwoNumbers(orOfTwoNumbers(a, b),c);
	}
	
	/**
	 * Gets rid of things like 3 turns in the same direction
	 * FIXME: does not do prime
	 * @param turns
	 * @return
	 */
	public static String cleanUpTurns(String turnString) {
		String turns = turnString;
		int counter = 0;
		for (int i = 1; i < turns.length(); i++) {
			if (turns.charAt(i) == turns.charAt(i-1)) {
				counter++;
				if (counter == 2) {
					counter = 0;
					//replace with prime turn
					String newString = turns.substring(0, i-1);
					turns = newString + "'" + turns.substring(i+1, turns.length());
				}
			} else {
				counter = 0;
			}
		}
		return turns;
	}
}