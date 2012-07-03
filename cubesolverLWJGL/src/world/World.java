package world;

import java.util.ArrayList;

import cube.Cubie;
import cube.Face;
import cube.RCube;

public class World {
	
	Renderer renderer;
	
	/**
	 * Create the internal representation of the rubik's cube
	 */
	private void drawCube(RCube cube) {
		ArrayList<Cubie> cubies = cube.getCubies();
		boolean stop = false;
		if (rotation >= 90) {
			stop = true;
		}
		for (Cubie cubie : cubies) {
			if (cubie.isOnFace(turningFace)) {
				if (stop) {
					cubie.rotateCubieOnFace(turningFace, inverseTurn);
					drawCubie(cubie, Face.NONE);
				} else {
					drawCubie(cubie, turningFace);
				}
			} else {
				drawCubie(cubie, Face.NONE);
			}
		}
		if (stop) {
			rotation = 0;
			turningFace = Face.NONE;
			stop = false;
		}
	}
	
}
