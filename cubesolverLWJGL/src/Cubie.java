/**
 * This class holds information about each smaller cube (Cubie) of a rubik's cube.
 */
public class Cubie {
	
	/** The position of the cubie relative to the center cubie */
	float[] position = new float[3];
	
	/** The colours of each face of the cubie */
	float[][] faceColours = new float[6][3];

	public Cubie(float[] position, float[][] faceColours) {
		this.position = position;
		this.faceColours = faceColours;
	}
	
	public boolean isOnFace(Face face) {
		
		switch (face) {
		case FRONT:
			if (position[2] > 2) {
				return true;
			}
		case BACK:
			if (position[2] < -2) {
				return true;
			}
			
		case UP:
			if (position[1] > 2) {
				return true;
			}
			
		case DOWN:
			if (position[1] < -2) {
				return true;
			}
			
		case LEFT:
			if (position[0] < -2) {
				return true;
			}			
			
		case RIGHT:
			if (position[0] > 2) {
				return true;
			}
		}
		
		return false;
	}
	
}
