/**
 * This class holds information about each smaller cube (Cubie) of a rubik's cube.
 */
public class Cubie {
	
	/** The position of the cubie relative to the center cubie */
	float[] position = new float[3];
	
	/** The colours of each face of the cubie */
	float[][] faceColours = new float[6][3];
	
	float cubieWidth;
	float gap;
	float size;

	public Cubie(float[] position, float[][] faceColours, float cubieWidth, float gap) {
		this.position = position;
		this.faceColours = faceColours;
		this.cubieWidth = cubieWidth;
		this.gap = gap;
		this.size = cubieWidth + gap; 
	}
	
	public boolean isOnFace(Face face) {
		
		switch (face) {
		case FRONT:
			if (position[2] > cubieWidth) {
				return true;
			}
			break;
		case BACK:
			if (position[2] < -cubieWidth) {
				return true;
			}
			break;
			
		case UP:
			if (position[1] > cubieWidth) {
				return true;
			}
			break;
			
		case DOWN:
			if (position[1] < -cubieWidth) {
				return true;
			}
			break;
			
		case LEFT:
			if (position[0] < -cubieWidth) {
				return true;
			}			
			break;
			
		case RIGHT:
			if (position[0] > cubieWidth) {
				return true;
			}
			break;
		}
		return false;
	}

	public float[] getPosition() {
		return position;
	}

	public float[][] getFaceColours() {
		return faceColours;
	}
	
	
}
