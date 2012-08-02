package world;

import static org.lwjgl.opengl.GL11.glPopMatrix;

import gui.UserInterface;

import java.util.ArrayList;


import org.lwjgl.opengl.GL11;

import cube.Cubie;
import cube.Face;
import cube.RCube;
import cube.Turn;

/**
 * Holds the {@link RCube cube} and the {@link Renderer} used to draw that cube.
 */
public class World {
	
	Renderer renderer;
	EventHandler eventHandler;
	RCube cube;
	
	
	public World() {
		renderer = new Renderer();	
		cube = new RCube(2.0f,0.05f);
		eventHandler = new EventHandler(this);
	}

	/**
	 * Use the {@link Renderer} to draw the cube and update the 
	 * state of the cube using {@link #drawCube}
	 */
	public void render() {
		GL11.glViewport(0, 0, UserInterface.openGLSize.width, UserInterface.openGLSize.height);
		GL11.glClearColor(0.93333f, 0.93333f, 0.93333f, 1);
		eventHandler.update(cube);
		renderer.init3D();
		renderer.prepare3D(cube);
		drawCube(cube);
		
		//FIXME put this in renderer
		glPopMatrix();
		
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
	
	public void performTurns(ArrayList<Turn> turns) {
		cube.addListToTurnQueue(turns);
	}
	
	public EventHandler getEventHandler() {
		return eventHandler;
	}
}
