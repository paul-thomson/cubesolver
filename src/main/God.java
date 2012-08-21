package main;

import java.util.ArrayList;
import java.util.Random;

import cube.Face;
import cube.RCube;
import cube.Turn;
import world.World;

public class God {

	static World world;
	static RCube cube;

	public static void setWorld(World newWorld) {
		world = newWorld;		
	}

	public static void setCube(RCube newCube) {
		cube = newCube;		
	}

	public static void performTurns(ArrayList<Turn> turns) {
		world.performTurns(turns);
	}

	public static RCube getCube() {
		return cube;
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

}
