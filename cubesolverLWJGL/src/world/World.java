package world;

import static org.lwjgl.opengl.GL11.glPopMatrix;

import java.util.ArrayList;

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
		
		for (Cubie cubie : cubies) {
			if (cubie.isOnFace(turningFace)) {
				if (turn.getRotationAngle() >= 90) {
					cube.stopTurning();
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
		renderer.init2D();
		renderer.render2D();
		
	}

	/**
	 * Performs some setup which is performed once before the rendering loop starts
	 */
	public void initialiseRendering() {
		renderer.initFont();
		renderer.getDelta(); // call once before loop to initialise lastFrame
		renderer.setLastFPS(renderer.getTime()); // call before loop to initialise fps timer		
	}
	
	/**
	 * An event handler. Deals with input from the keyboard and updates the necessary 
	 * objects
	 * @param delta
	 */
	public void update(int delta) {
		float[] rotations = cube.getRotation();
		float ROT_X = rotations[0];
		float ROT_Y = rotations[1];
		float ROT_Z = rotations[2];
		Turn turn = cube.getTurn();
		Face turningFace = turn.getTurningFace();
		float rotationAngle = turn.getRotationAngle();
		boolean inverseTurn = turn.isInverseTurn();
		
		if (rotationAngle != 0) {
			rotationAngle += 0.15f * delta;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) ROT_X -= 0.05f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ROT_X += 0.05f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) ROT_Y += 0.05f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) ROT_Y -= 0.05f * delta;
		
		if (turningFace == Face.NONE) { 
			if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
				rotationAngle += 0.15f * delta;
				turningFace = Face.UP;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				rotationAngle += 0.15f * delta;
				turningFace = Face.DOWN;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				rotationAngle += 0.15f * delta;
				turningFace = Face.FRONT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
				rotationAngle += 0.15f * delta;
				turningFace = Face.BACK;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
				rotationAngle += 0.15f * delta;
				turningFace = Face.LEFT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				rotationAngle += 0.15f * delta;
				turningFace = Face.RIGHT;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				inverseTurn = true;
			} else {
				inverseTurn = false;
			}
		}

		cube.setRotation(new float[]{ROT_X,ROT_Y,ROT_Z});
		cube.setTurning(new Turn(turningFace,inverseTurn,rotationAngle));
		// reset
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			//FIXME below is silly
			cube = new RCube(2.0f,0.05f);
		}
		renderer.updateFPS(); // update FPS Counter
	}
}
