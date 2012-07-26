package world;

import static org.lwjgl.opengl.GL11.glPopMatrix;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import cube.Cubie;
import cube.Face;
import cube.RCube;
import cube.Turn;

/**
 * Holds the {@link RCube cube} and the {@link Renderer} used to draw that cube.
 */
public class World {
	
	Renderer renderer;
	RCube cube;
	
	
	public World() {
		renderer = new Renderer();
	}
	
	/**
	 * Update the internal representation of the {@link RCube cube} and 
	 * make calls to the {@link Renderer} to draw the cube.
	 */
	private void drawCube(RCube cube) {
		ArrayList<Cubie> cubies = cube.getCubies();
		Turn turn = cube.getTurn();
		Face turningFace = turn.getTurningFace();
		if (turn.getRotationAngle() >= 90) {
			cube.stopTurning();
		}
		for (Cubie cubie : cubies) {
			if (cubie.isOnFace(turningFace)) {
				if (turn.getRotationAngle() >= 90) {
					cubie.rotateCubieOnFace(turn);
					renderer.drawCubie(cubie, null);
				} else {
					renderer.drawCubie(cubie, turn);
				}
			} else {
				renderer.drawCubie(cubie, null);
			}
		}
	}

	/**
	 * Use the {@link Renderer} to draw the cube and update the 
	 * state of the cube using {@link #drawCube}
	 */
	public void render() {
		int delta = renderer.getDelta();
		//FIXME below is silly?
		if (cube == null) {
			cube = new RCube(2.0f,0.05f);
		}
		update(delta);
		renderer.init3D();
		renderer.prepare3D(cube);
		drawCube(cube);
		
		//FIXME put this in renderer
		glPopMatrix();
		
	}

	/**
	 * Performs some setup which is performed once before the rendering loop starts
	 */
	public void initialiseRendering() {
		renderer.getDelta(); // call once before loop to initialise lastFrame
		renderer.setLastFPS(renderer.getTime()); // call before loop to initialise fps timer		
	}
	
	/**
	 * An event handler. Deals with input from the keyboard and updates the necessary 
	 * objects
	 * @param delta
	 */
	public void update(int delta) {
		//TODO remove all rotation stuff
		float[] rotations = cube.getRotation();
		float ROT_X = rotations[0];
		float ROT_Y = rotations[1];
		float ROT_Z = rotations[2];
		/* Use a copy of the turn */
		Turn turn = new Turn(cube.getTurn());
		Face turningFace = turn.getTurningFace();
		
		
		if (turningFace != Face.NONE) {
			cube.continueTurning(0.15f * delta);
		}
		//FIXME use case statements

		
		//TODO remove later
//		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) ROT_X -= 0.05f * delta;
//		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ROT_X += 0.05f * delta;
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) ROT_Y += 0.05f * delta;
//		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) ROT_Y -= 0.05f * delta;

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
		cube.setRotation(new float[]{ROT_X,ROT_Y,ROT_Z});
		renderer.updateFPS(); // update FPS Counter
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
	 * FIXME currently will error if ' is present at the beginning
	 * @param turns
	 * @return
	 */
	public static ArrayList<Turn> parseTurnsFromString(String turns) {
		ArrayList<Turn> turnsToReturn = new ArrayList<Turn>();
		
		for (int i = turns.length() - 1; i >= 0; i--) {
			boolean inverseTurn = false;
			if (turns.charAt(i) == '\'') {
				//prime turn
				inverseTurn = true;
				i--;
			}
			turnsToReturn.add(new Turn(
					Face.getFaceFromChar(turns.charAt(i)), 
					inverseTurn, 
					0
					));
		}
		return turnsToReturn;
	}
}
