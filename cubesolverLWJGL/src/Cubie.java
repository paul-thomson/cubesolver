/**
 * This class holds information about each smaller cube (Cubie) of a rubik's cube.
 */
public class Cubie {
	
	/** The position of the cubie relative to the center cubie */
	float[] position = new float[3];
	
	/** The colours of each face of the cubie */
	float[][] faceColours = new float[6][3];

	public Cubie() {
		
	}
	
}
