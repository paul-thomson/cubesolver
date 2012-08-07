package world;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import cube.Face;
import cube.RCube;
import cube.Turn;

public class EventHandler implements ActionListener {

	World world;
	long lastFrame;
	int fps;
	private long lastFPS;
	
	public EventHandler(World world) {
		this.world = world;
		getDelta(); // call once before loop to initialise lastFrame
		setLastFPS(getTime()); // call before loop to initialise fps timer		
	}

	/**
	 * An event handler. Deals with input from the keyboard and updates the necessary 
	 * objects
	 * @param delta
	 */
	public void update(RCube cube) {
		int delta = getDelta();
		/* Use a copy of the turn */
		Turn turn = new Turn(cube.getTurn());
		Face turningFace = turn.getTurningFace();

		if (turningFace != Face.NONE) {
			cube.continueTurning(0.15f * delta);
		}
		//FIXME use case statements

		if (!cube.isTurning()) { 
			if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
				turningFace = Face.UP;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				turningFace = Face.DOWN;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				turningFace = Face.FRONT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
				turningFace = Face.BACK;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
				turningFace = Face.LEFT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				turningFace = Face.RIGHT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
				turningFace = Face.X;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
				turningFace = Face.Y;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
				turningFace = Face.Z;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
									cube.addListToTurnQueue(parseTurnsFromString(generateString(21)));
			}
			if (turningFace != Face.NONE) { 
				cube.addToTurnQueue(new Turn(turningFace,Keyboard.isKeyDown(Keyboard.KEY_LSHIFT),0));
			}
		}
		updateFPS();
	}
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - getLastFPS() > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			setLastFPS(getLastFPS() + 1000);
		}
		fps++;
	}

	public long getLastFPS() {
		return lastFPS;
	}

	public void setLastFPS(long lastFPS) {
		this.lastFPS = lastFPS;
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
	 * FIXME currently will error if ' is present at the beginning, also do other sanitise stuff
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		world.performTurns(EventHandler.parseTurnsFromString(e.getActionCommand()));	
	}

}
