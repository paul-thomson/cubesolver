package cube;

import java.awt.Point;
import java.awt.geom.Point2D.Float;
import java.util.Arrays;

/**
 * This class holds information about each smaller cube (Cubie) of a rubik's cube.
 */
public class Cubie {

	/** The position of the cubie relative to the center cubie */
	private Position position = new Position(0,0,0);
	/** The colours of each face of the cubie */
	private float[][] faceColours = new float[6][3];
	/** The width of a cubie */
	private float cubieWidth;
	/** The size of the gaps between cubies */
	private float gap;
	/** The total size of one cube: width + gap */
	private float size;
	
	private boolean highlight = false;

	public Cubie(Position position, float[][] faceColours, float cubieWidth, float gap) {
		this.position = position;
		this.faceColours = faceColours;
		this.cubieWidth = cubieWidth;
		this.gap = gap;
		this.size = cubieWidth + gap; 
	}
	
	public Cubie(float[] pos, float[][] faceColours, float cubieWidth, float gap) {
		this(new Position(pos), faceColours, cubieWidth, gap) ;
	}
	
	/**
	 * Clone constructor
	 * @param cubie
	 */
	public Cubie(Cubie cubie) {
		this.position = new Position(cubie.position);
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
	
	public float getSize() {
		return this.size;
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
			if (position.Y() > cubieWidth) {
				return true;
			}
			break;
		case DOWN:
			if (position.Y() < -cubieWidth) {
				return true;
			}
			break;
		case FRONT:
			if (position.Z() > cubieWidth) {
				return true;
			}
			break;
		case BACK:
			if (position.Z() < -cubieWidth) {
				return true;
			}
			break;

		case LEFT:
			if (position.X() < -cubieWidth) {
				return true;
			}			
			break;

		case RIGHT:
			if (position.X() > cubieWidth) {
				return true;
			}
			break;
		case MIDDLE:
			if (position.X() < cubieWidth & position.X() > -cubieWidth) {
				return true;
			}
			break;
		case S:
			if (position.Z() < cubieWidth & position.Z() > -cubieWidth) {
				return true;
			}
			break;
		case E:
			if (position.Y() < cubieWidth & position.Y() > -cubieWidth) {
				return true;
			}
			break;
		case FRONT2:
			if (isOnFace(Face.FRONT) | isOnFace(Face.S)) {
				return true;
			}
			break;
		case BACK2:
			if (isOnFace(Face.BACK) | isOnFace(Face.S)) {
				return true;
			}
			break;
		case UP2:
			if (isOnFace(Face.UP) | isOnFace(Face.E)) {
				return true;
			}
			break;
		case DOWN2:
			if (isOnFace(Face.DOWN) | isOnFace(Face.E)) {
				return true;
			}
			break;
		case RIGHT2:
			if (isOnFace(Face.RIGHT) | isOnFace(Face.MIDDLE)) {
				return true;
			}
			break;
		case LEFT2:
			if (isOnFace(Face.LEFT) | isOnFace(Face.MIDDLE)) {
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
		float[] posArray = this.position.toArray();
		if (inverse) {
			switch (face) {	
			case Y:
			case UP2:
			case UP:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[4],faceColours[5],
						faceColours[3],faceColours[2]});

				setPosition(new Position(posArray[2],posArray[1],-posArray[0]));
				break;
			case E:
			case DOWN2:
			case DOWN:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[5],faceColours[4],
						faceColours[2],faceColours[3]});
				setPosition(new Position(-posArray[2],posArray[1],posArray[0]));
				
				break;
			case Z:
			case S:
			case FRONT2:
			case FRONT:
				setFaceColours(new float[][]{	faceColours[5],faceColours[4],
						faceColours[2],faceColours[3],
						faceColours[0],faceColours[1]});

				setPosition(new Position(-posArray[1],posArray[0],posArray[2]));
				break;

			case BACK2:
			case BACK:
				setFaceColours(new float[][]{	faceColours[4],faceColours[5],
						faceColours[2],faceColours[3],
						faceColours[1],faceColours[0]});

				setPosition(new Position(posArray[1],-posArray[0],posArray[2]));
				break;

			case MIDDLE:
			case LEFT2:
			case LEFT:
				setFaceColours(new float[][]{	faceColours[2],faceColours[3],
						faceColours[1],faceColours[0],
						faceColours[4],faceColours[5]});

				setPosition(new Position(posArray[0],posArray[2],-posArray[1]));
				break;
			case X:
			case RIGHT2:
			case RIGHT:
				setFaceColours(new float[][]{	faceColours[3],faceColours[2],
						faceColours[0],faceColours[1],
						faceColours[4],faceColours[5]});

				setPosition(new Position(posArray[0],-posArray[2],posArray[1]));
				break;
			default:
				System.err.println("Could not swap colours in Cubie");
				break;
			}
		} else {
			switch (face) {	
			case Y:
			case UP2:
			case UP:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[5],faceColours[4],
						faceColours[2],faceColours[3]});

				setPosition(new Position(-posArray[2],posArray[1],posArray[0]));
				break;

			case E:
			case DOWN2:
			case DOWN:
				setFaceColours(new float[][]{	faceColours[0],faceColours[1],
						faceColours[4],faceColours[5],
						faceColours[3],faceColours[2]});
				setPosition(new Position(posArray[2],posArray[1],-posArray[0]));
				
				break;
			case Z:
			case S:
			case FRONT2:
			case FRONT:
				setFaceColours(new float[][]{	faceColours[4],faceColours[5],
						faceColours[2],faceColours[3],
						faceColours[1],faceColours[0]});

				setPosition(new Position(posArray[1],-posArray[0],posArray[2]));
				break;

			case BACK2:
			case BACK:
				setFaceColours(new float[][]{	faceColours[5],faceColours[4],
						faceColours[2],faceColours[3],
						faceColours[0],faceColours[1]});

				setPosition(new Position(-posArray[1],posArray[0],posArray[2]));
				break;

			case MIDDLE:
			case LEFT2:
			case LEFT:
				setFaceColours(new float[][]{	faceColours[3],faceColours[2],
						faceColours[0],faceColours[1],
						faceColours[4],faceColours[5]});

				setPosition(new Position(posArray[0],-posArray[2],posArray[1]));
				break;
			case X:
			case RIGHT2:
			case RIGHT:
				setFaceColours(new float[][]{faceColours[2],faceColours[3],
						faceColours[1],faceColours[0],
						faceColours[4],faceColours[5]});

				setPosition(new Position(posArray[0],posArray[2],-posArray[1]));	
				break;
			default:
				System.err.println("Could not swap colours in Cubie");
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

	public Position getPosition() {
		return position;
	}

	public float[][] getFaceColours() {
		return faceColours;
	}

	private void setPosition(Position position) {
		this.position = position;
	}

	private void setFaceColours(float[][] faceColours) {
		this.faceColours = faceColours;
	}
	
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	
	public boolean isHighlighted() {
		return this.highlight;
	}
	
	public float[] getTopColour() {
		return faceColours[0];
	}
	
	public float[] getBottomColour() {
		return faceColours[1];
	}
	
	public float[] getFrontColour() {
		return faceColours[2];
	}
	
	public float[] getBackColour() {
		return faceColours[3];
	}
	
	public float[] getLeftColour() {
		return faceColours[4];
	}
	
	public float[] getRightColour() {
		return faceColours[5];
	}
	
	public boolean isEqual(Cubie cubie) {
		return((cubie.getPosition().equals(this.position)) & 
				Arrays.equals(cubie.getFaceColours(),this.faceColours));
	}
}
