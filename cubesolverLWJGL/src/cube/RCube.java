package cube;

import java.util.ArrayList;

/**
 * Internal representation of a rubik's cube
 */
public class RCube {
	
	/** position of cube */
	float POS_X = 0.0f, POS_Y = 0.0f, POS_Z = -17;
	/** Angle of the cube */
	float ROT_X = 20, ROT_Y = 35, ROT_Z = 0;
	
	/** Colours */
	float[] red = new float[]{1.0f,0.0f,0.0f};
	float[] green = new float[]{0.0f,1.0f,0.0f};
	float[] blue = new float[]{0.0f,0.0f,1.0f};
	float[] orange = new float[]{1.0f,0.4f,0.1f};
	float[] yellow = new float[]{1.0f,1.0f,0.1f};
	float[] white = new float[]{1.0f,1.0f,1.0f};
	float[] black = new float[]{0.2f,0.2f,0.2f};
	
	/** Unused */
	float[][] cube = new float[][]{yellow,white,red,orange,blue,green};
	/** Width of a cubie */
	float cubieWidth;
	/** Size of the gap between cubies */
	float gap;
	/** Total size of a cubie: width + gap */
	float size;
	/** The list of cubies (should be 27 in length) */
	ArrayList<Cubie> cubies;
	/** The current turn the cube is performing */
	Turn currentTurn;
	
	public RCube(float cubieWidth, float gap) {
		this.cubieWidth = cubieWidth;
		this.gap = gap;
		this.size = cubieWidth + gap;
		cubies = new ArrayList<Cubie>();
		currentTurn = new Turn();
		initCubies();
	}

	/**
	 * Create and add all 27 cubies to the list of cubies. This creates the Rubik's cube.
	 */
	private void initCubies() {
		
		/** First Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,size}, new float[][]{black,black,red,black,black,black},cubieWidth,gap)); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,size}, new float[][]{black,white,red,black,blue,black},cubieWidth,gap)); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,size}, new float[][]{black,white,red,black,black,black},cubieWidth,gap));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,size}, new float[][]{black,white,red,black,black,green},cubieWidth,gap)); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,size}, new float[][]{black,black,red,black,black,green},cubieWidth,gap)); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,size}, new float[][]{yellow,black,red,black,black,green},cubieWidth,gap)); 	// top right
		cubies.add(new Cubie(new float[]{0,size,size}, new float[][]{yellow,black,red,black,black,black},cubieWidth,gap)); 	// top middle
		cubies.add(new Cubie(new float[]{-size,size,size}, new float[][]{yellow,black,red,black,blue,black},cubieWidth,gap)); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,size}, new float[][]{black,black,red,black,blue,black},cubieWidth,gap)); 	// middle left
	
		/** Middle Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,0}, new float[][]{black,black,black,black,black,black},cubieWidth,gap)); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,0}, new float[][]{black,white,black,black,blue,black},cubieWidth,gap)); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,0}, new float[][]{black,white,black,black,black,black},cubieWidth,gap));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,0}, new float[][]{black,white,black,black,black,green},cubieWidth,gap)); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,0}, new float[][]{black,black,black,black,black,green},cubieWidth,gap)); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,0}, new float[][]{yellow,black,black,black,black,green},cubieWidth,gap)); 	// top right
		cubies.add(new Cubie(new float[]{0,size,0}, new float[][]{yellow,black,black,black,black,black},cubieWidth,gap)); 		// top middle
		cubies.add(new Cubie(new float[]{-size,size,0}, new float[][]{yellow,black,black,black,blue,black},cubieWidth,gap)); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,0}, new float[][]{black,black,black,black,blue,black},cubieWidth,gap)); 		// middle left
	
		/** Last Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,-size}, new float[][]{black,black,black,orange,black,black},cubieWidth,gap)); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,-size}, new float[][]{black,white,black,orange,blue,black},cubieWidth,gap)); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,-size}, new float[][]{black,white,black,orange,black,black},cubieWidth,gap));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,-size}, new float[][]{black,white,black,orange,black,green},cubieWidth,gap)); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,-size}, new float[][]{black,black,black,orange,black,green},cubieWidth,gap)); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,-size}, new float[][]{yellow,black,black,orange,black,green},cubieWidth,gap)); 	// top right
		cubies.add(new Cubie(new float[]{0,size,-size}, new float[][]{yellow,black,black,orange,black,black},cubieWidth,gap)); 		// top middle
		cubies.add(new Cubie(new float[]{-size,size,-size}, new float[][]{yellow,black,black,orange,blue,black},cubieWidth,gap)); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,-size}, new float[][]{black,black,black,orange,blue,black},cubieWidth,gap)); 		// middle left
	}
	
	public ArrayList<Cubie> getCubies() {
		return cubies;
	}

	public Turn getTurn() {
		return currentTurn;
	}
	
	public void setTurning(Turn turn) {
		this.currentTurn = turn;
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

	public void stopTurning() {
		currentTurn = new Turn();
	}

}
