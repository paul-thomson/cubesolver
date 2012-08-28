package cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import main.God;


/**
 * Internal representation of a rubik's cube
 */
public class RCube {

	/** position of cube */
	float POS_X = 0.0f, POS_Y = 0.0f, POS_Z = -17;
	/** Angle of the cube */
	float ROT_X = 20, ROT_Y = -35, ROT_Z = 0;

	/** Colours */
	public static float[] RED = new float[]{1.0f,0.0f,0.0f};
	public static float[] GREEN = new float[]{0.0f,1.0f,0.0f};
	public static float[] BLUE = new float[]{0.0f,0.0f,1.0f};
	public static float[] ORANGE = new float[]{1.0f,0.45f,0.0f};
	public static float[] YELLOW = new float[]{1.0f,1.0f,0.0f};
	public static float[] WHITE = new float[]{1.0f,1.0f,1.0f};
	public static float[] BLACK = new float[]{0.0f,0.0f,0.0f};

	/** Unused */
	@SuppressWarnings("unused")
	private float[][] cube = new float[][]{YELLOW,WHITE,RED,ORANGE,BLUE,GREEN};
	/** Width of a cubie */
	private float cubieWidth;
	/** Size of the gap between cubies */
	private float gap;
	/** Total size of a cubie: width + gap */
	private float size;
	/** The list of cubies (should be 27 in length) */
	private ArrayList<Cubie> cubies;
	/** The current turn the cube is performing */
	private LinkedList<Turn> turnQueue;

	public RCube(float cubieWidth, float gap) {
		this.cubieWidth = cubieWidth;
		this.gap = gap;
		this.size = cubieWidth + gap;
		this.cubies = new ArrayList<Cubie>();
		this.turnQueue = new LinkedList<>();
		initCubies();
	}

	/**
	 * Clone constructor: does not copy turnQueue
	 * @param cube
	 */
	public RCube(RCube cube) {
		this(cube.cubieWidth,cube.gap);
		// now clone cubies
		ArrayList<Cubie> oldCubies = cube.cubies;
		for (int i = 0; i < oldCubies.size(); i++) {
			cubies.set(i, new Cubie(oldCubies.get(i)));
		}
	}

	/**
	 * Create and add all 27 cubies to the list of cubies. This creates the Rubik's cube.
	 */
	private void initCubies() {

		/** First Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,size}, new float[][]{BLACK,BLACK,RED,BLACK,BLACK,BLACK},cubieWidth,gap)); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,size}, new float[][]{BLACK,WHITE,RED,BLACK,BLUE,BLACK},cubieWidth,gap)); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,size}, new float[][]{BLACK,WHITE,RED,BLACK,BLACK,BLACK},cubieWidth,gap));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,size}, new float[][]{BLACK,WHITE,RED,BLACK,BLACK,GREEN},cubieWidth,gap)); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,size}, new float[][]{BLACK,BLACK,RED,BLACK,BLACK,GREEN},cubieWidth,gap)); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,size}, new float[][]{YELLOW,BLACK,RED,BLACK,BLACK,GREEN},cubieWidth,gap)); 	// top right
		cubies.add(new Cubie(new float[]{0,size,size}, new float[][]{YELLOW,BLACK,RED,BLACK,BLACK,BLACK},cubieWidth,gap)); 	// top middle
		cubies.add(new Cubie(new float[]{-size,size,size}, new float[][]{YELLOW,BLACK,RED,BLACK,BLUE,BLACK},cubieWidth,gap)); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,size}, new float[][]{BLACK,BLACK,RED,BLACK,BLUE,BLACK},cubieWidth,gap)); 	// middle left

		/** Middle Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,0}, new float[][]{BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},cubieWidth,gap)); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,0}, new float[][]{BLACK,WHITE,BLACK,BLACK,BLUE,BLACK},cubieWidth,gap)); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,0}, new float[][]{BLACK,WHITE,BLACK,BLACK,BLACK,BLACK},cubieWidth,gap));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,0}, new float[][]{BLACK,WHITE,BLACK,BLACK,BLACK,GREEN},cubieWidth,gap)); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,0}, new float[][]{BLACK,BLACK,BLACK,BLACK,BLACK,GREEN},cubieWidth,gap)); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,0}, new float[][]{YELLOW,BLACK,BLACK,BLACK,BLACK,GREEN},cubieWidth,gap)); 	// top right
		cubies.add(new Cubie(new float[]{0,size,0}, new float[][]{YELLOW,BLACK,BLACK,BLACK,BLACK,BLACK},cubieWidth,gap)); 		// top middle
		cubies.add(new Cubie(new float[]{-size,size,0}, new float[][]{YELLOW,BLACK,BLACK,BLACK,BLUE,BLACK},cubieWidth,gap)); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,0}, new float[][]{BLACK,BLACK,BLACK,BLACK,BLUE,BLACK},cubieWidth,gap)); 		// middle left

		/** Last Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,-size}, new float[][]{BLACK,BLACK,BLACK,ORANGE,BLACK,BLACK},cubieWidth,gap)); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,-size}, new float[][]{BLACK,WHITE,BLACK,ORANGE,BLUE,BLACK},cubieWidth,gap)); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,-size}, new float[][]{BLACK,WHITE,BLACK,ORANGE,BLACK,BLACK},cubieWidth,gap));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,-size}, new float[][]{BLACK,WHITE,BLACK,ORANGE,BLACK,GREEN},cubieWidth,gap)); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,-size}, new float[][]{BLACK,BLACK,BLACK,ORANGE,BLACK,GREEN},cubieWidth,gap)); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,-size}, new float[][]{YELLOW,BLACK,BLACK,ORANGE,BLACK,GREEN},cubieWidth,gap)); 	// top right
		cubies.add(new Cubie(new float[]{0,size,-size}, new float[][]{YELLOW,BLACK,BLACK,ORANGE,BLACK,BLACK},cubieWidth,gap)); 		// top middle
		cubies.add(new Cubie(new float[]{-size,size,-size}, new float[][]{YELLOW,BLACK,BLACK,ORANGE,BLUE,BLACK},cubieWidth,gap)); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,-size}, new float[][]{BLACK,BLACK,BLACK,ORANGE,BLUE,BLACK},cubieWidth,gap)); 		// middle left
	}

	/**
	 * Gets the cubie with the list of colours given.
	 * @param faces
	 * @return null if cubie not found
	 */
	public Cubie getCubie(float[][] faces) {
		ArrayList<float[]> wantedColours = new ArrayList<float[]>(Arrays.asList(faces));
		wantedColours.add(BLACK);

		OUTERLOOP: for (Cubie cubie : cubies) {
			ArrayList<float[]> cubieColours = new ArrayList<float[]>(Arrays.asList(cubie.getFaceColours()));
			for (float[] colour : cubieColours) {
				if (!wantedColours.contains(colour)) {
					continue OUTERLOOP;
				}
			}
			for (float[] colour : wantedColours) {
				if (!cubieColours.contains(colour)) {
					continue OUTERLOOP;
				}
			}
			return cubie;
		}
		return null;
	}

	/** Adds the turn to the end of the turn queue */
	public void addToTurnQueue(Turn turn) {
		turnQueue.add(turn);
	}

	/**
	 * Used for adding entire algorithms to the queue.
	 * 
	 * Not sure about parameter... ???? 
	 * @param turns
	 */
	public void addListToTurnQueue(Iterable<Turn> turns) {
		for (Turn turn : turns) {
			addToTurnQueue(turn);
		}
	}

	public void continueTurning(float amount) {
		if (turnQueue.peek() != null) {
			turnQueue.peek().continueTurning(amount);
			Turn turn = getTurn();
			if (turn.getRotationAngle() >= 90) {
				stopTurning(turn);
			}
		} else {
			
		}
	}

	private void stopTurning(Turn turn) {
		for (Cubie cubie : cubies) {
			if (cubie.isOnFace(turn.getTurningFace())) {
				cubie.rotateCubieOnFace(turn);
			}
		}
		turnQueue.pop();
	}

	public ArrayList<Cubie> getCubies() {
		return cubies;
	}

	/** Returns the first element of the turn turnQueue	 */
	public Turn getTurn() {
		if (turnQueue.size() != 0) {
			return turnQueue.peek();
		} else {
			return new Turn();
		}
	}

	public float[] getPosition() {
		return new float[]{POS_X,POS_Y,POS_Z};
	}

	public float[] getRotation() {
		return new float[]{ROT_X,ROT_Y,ROT_Z};
	}

	public void setPosition(float[] pos) {
		POS_X = pos[0];
		POS_Y = pos[1];
		POS_Z = pos[2];
	}

	public void setRotation(float[] rot) {
		ROT_X = rot[0];
		ROT_Y = rot[1];
		ROT_Z = rot[2];
	}

	public boolean isTurning() {
		return turnQueue.size() > 0;
	}

	/**
	 * Performs turns without displaying them.
	 * IMPORTANT NOTE: still changes the positions etc of cubies
	 */
	public void performSimulatedTurns() {
		while (isTurning()) {
			continueTurning(90);
		}
	}

	/**
	 * Gets the position of the edge cubie inbetween the two faces (denoted by colour of center cubie)
	 * @param colour1
	 * @param colour2
	 * @return
	 */
	public Position getEdgeFromCentres(float[] colour1, float[] colour2) {
		Cubie cubie1 = getCubie(new float[][]{colour1});
		Cubie cubie2 = getCubie(new float[][]{colour2});
		float[] pos1 = cubie1.getPosition().toArray();
		float[] pos2 = cubie2.getPosition().toArray();
		float[] newPos = new float[]{
				God.orOfTwoNumbers(pos1[0], pos2[0]),
				God.orOfTwoNumbers(pos1[1], pos2[1]),
				God.orOfTwoNumbers(pos1[2], pos2[2])
		};
	
		return new Position(newPos);
	}
	
	public Position getCornerPosition(float[] colour1, float[] colour2, float[] colour3) {
		Cubie cubie1 = getCubie(new float[][]{colour1});
		Cubie cubie2 = getCubie(new float[][]{colour2});
		Cubie cubie3 = getCubie(new float[][]{colour3});
		float[] pos1 = cubie1.getPosition().toArray();
		float[] pos2 = cubie2.getPosition().toArray();
		float[] pos3 = cubie3.getPosition().toArray();
		float[] newPos = new float[]{
				God.orOfThreeNumbers(pos1[0], pos2[0],pos3[0]),
				God.orOfThreeNumbers(pos1[1], pos2[1],pos3[1]),
				God.orOfThreeNumbers(pos1[2], pos2[2],pos3[2])
		};
		return new Position(newPos);
	}
	
	/**
	 * Compares the cube to a new cube to see if they are equal 
	 * (compares the colours of the faces and the positions)
	 * @return
	 */
	public boolean isSolved() {
		RCube solvedCube = new RCube(this.cubieWidth,this.gap);
		ArrayList<Cubie> solvedCubies = solvedCube.getCubies();
		for (int i = 0; i < solvedCubies.size(); i++) {
			Cubie solvedCubie = solvedCubies.get(i);
			Cubie currentCubie = cubies.get(i);
			if (!solvedCubie.getPosition().equals(currentCubie.getPosition())) {
				return false;
			}
			float[][] solvedColours = solvedCubie.getFaceColours();
			float[][] currentColours = currentCubie.getFaceColours();
			for (int j = 0; j < solvedColours.length; j++) {
				if (!God.arrayEquals(solvedColours[j], currentColours[j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * By comparing the cubie to the solved version it determines if 
	 * the cubie is solved.
	 * @param cube: this is need so we can position the solved cube the same 
	 * way as the cube the cubie is from
	 * @param cubie
	 * @return
	 */
	public boolean isCubieSolved(RCube cube, Cubie cubie) {
		RCube solvedCube = new RCube(this.cubieWidth,this.gap);
		//need to position cube correctly: we assume it has only done Y turns:
		Cubie centreSolved = solvedCube.getCubie(new float[][]{RCube.RED});
		Cubie centre = cube.getCubie(new float[][]{RCube.RED});
		while (!centreSolved.isEqual(centre)) {
			solvedCube.addListToTurnQueue(God.parseTurnsFromString("Y"));
			solvedCube.performSimulatedTurns();
		}
		
		Cubie solvedCubie = solvedCube.getCubie(cubie.getFaceColours());
		return solvedCubie.isEqual(cubie);
	}
}
