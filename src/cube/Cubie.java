package cube;

import java.awt.Point;
import java.awt.geom.Point2D.Float;

/**
 * This class holds information about each smaller cube (Cubie) of a rubik's cube.
 */
public class Cubie {

	/** The position of the cubie relative to the center cubie */
	float[] position = new float[3];
	/** The colours of each face of the cubie */
	float[][] faceColours = new float[6][3];
	/** The width of a cubie */
	float cubieWidth;
	/** The size of the gaps between cubies */
	float gap;
	/** The total size of one cube: width + gap */
	float size;

	public Cubie(float[] position, float[][] faceColours, float cubieWidth, float gap) {
		this.position = position;
		this.faceColours = faceColours;
		this.cubieWidth = cubieWidth;
		this.gap = gap;
		this.size = cubieWidth + gap; 
	}
	
	/**
	 * Clone constructor
	 * @param cubie
	 */
	public Cubie(Cubie cubie) {
		this.position = cubie.position.clone();
		this.cubieWidth = cubie.cubieWidth;
		this.gap = cubie.gap;
		this.size = cubie.cubieWidth + cubie.gap;
		//need to clone face colours
		float[][] faceColoursClone = new float[faceColours.length][];
		for (int i = 0; i < faceColours.length; i++) {
			faceColoursClone[i] = cubie.getFaceColours()[i];
		}
		this.faceColours = faceColoursClone;
	}

	/**
	 * Returns true if the object is on the given face
	 * @param face
	 */
	public boolean isOnFace(Face face) {
		switch (face) {
		case X:
		case Y:
		case Z:
			return true;
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

	/**
	 * Rotates the values of the cubie (faceColours and position) as if the face is rotating 
	 * 90 degrees
	 * @param face
	 */
	public void rotateCubieOnFace(Turn turn) {
		//TODO this is messy, should clean up
		Face face = turn.getTurningFace();
		boolean inverse = turn.isInverseTurn();
		if (inverse) {
			switch (face) {	
			case Y:
			case UP:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[4],faceColours[5],
						faceColours[3],faceColours[2]});

				setPosition(new float[]{position[2],position[1],-position[0]});
				break;

			case DOWN:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[4],faceColours[5],
						faceColours[3],faceColours[2]});

				setPosition(new float[]{position[2],position[1],-position[0]});
				break;
			case Z:
			case FRONT:
				setFaceColours(new float[][]{	faceColours[5],faceColours[4],
						faceColours[2],faceColours[3],
						faceColours[0],faceColours[1]});

				setPosition(new float[]{-position[1],position[0],position[2]});
				break;

			case BACK:
				setFaceColours(new float[][]{	faceColours[4],faceColours[5],
						faceColours[2],faceColours[3],
						faceColours[1],faceColours[0]});

				setPosition(new float[]{position[1],-position[0],position[2]});
				break;
			
			case LEFT:
				setFaceColours(new float[][]{	faceColours[2],faceColours[3],
						faceColours[1],faceColours[0],
						faceColours[4],faceColours[5]});

				setPosition(new float[]{position[0],position[2],-position[1]});
				break;
			case X:
			case RIGHT:
				setFaceColours(new float[][]{	faceColours[3],faceColours[2],
						faceColours[0],faceColours[1],
						faceColours[4],faceColours[5]});

				setPosition(new float[]{position[0],-position[2],position[1]});
				break;
			}
		} else {
			switch (face) {	
			case Y:
			case UP:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[5],faceColours[4],
						faceColours[2],faceColours[3]});

				setPosition(new float[]{-position[2],position[1],position[0]});
				break;

			case DOWN:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[5],faceColours[4],
						faceColours[2],faceColours[3]});

				setPosition(new float[]{-position[2],position[1],position[0]});
				break;
			case Z:
			case FRONT:
				setFaceColours(new float[][]{	faceColours[4],faceColours[5],
						faceColours[2],faceColours[3],
						faceColours[1],faceColours[0]});

				setPosition(new float[]{position[1],-position[0],position[2]});
				break;

			case BACK:
				setFaceColours(new float[][]{	faceColours[5],faceColours[4],
						faceColours[2],faceColours[3],
						faceColours[0],faceColours[1]});

				setPosition(new float[]{-position[1],position[0],position[2]});
				break;

			case LEFT:
				setFaceColours(new float[][]{	faceColours[3],faceColours[2],
						faceColours[0],faceColours[1],
						faceColours[4],faceColours[5]});

				setPosition(new float[]{position[0],-position[2],position[1]});
				break;
			case X:
			case RIGHT:
				setFaceColours(new float[][]{	faceColours[2],faceColours[3],
						faceColours[1],faceColours[0],
						faceColours[4],faceColours[5]});

				setPosition(new float[]{position[0],position[2],-position[1]});	
				break;
			}
		}
	}

	/**
	 * Returns the point (x,y) 90 degrees CW around the origin
	 * @param x
	 * @param y
	 * @return
	 */
	public Float rotatePoint(float x, float y) {
		return new Point.Float(y,-x);
	}

	public float[] getPosition() {
		return position;
	}

	public float[][] getFaceColours() {
		return faceColours;
	}

	private void setPosition(float[] position) {
		this.position = position;
	}

	private void setFaceColours(float[][] faceColours) {
		this.faceColours = faceColours;
	}
}
