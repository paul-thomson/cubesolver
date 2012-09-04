package world;


import main.God;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import cube.Face;
import cube.RCube;
import cube.Turn;

public class EventHandler {

	long lastFrame;
	int fps;
	private long lastFPS;
	
	public EventHandler() {
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
		cube.continueTurning(0.15f * delta);
		Face turningFace = Face.NONE;

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
				cube.addListToTurnQueue(
						God.parseTurnsFromString(
								God.cleanUpTurns(
										God.generateString(21))));
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

}
