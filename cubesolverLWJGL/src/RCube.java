import java.util.ArrayList;

/**
 * Internal representation of a rubik's cube
 *
 */
public class RCube {
	
	/** Colours */
	float[] red = new float[]{1.0f,0.0f,0.0f};
	float[] green = new float[]{0.0f,1.0f,0.0f};
	float[] blue = new float[]{0.0f,0.0f,1.0f};
	float[] orange = new float[]{1.0f,0.4f,0.1f};
	float[] yellow = new float[]{1.0f,1.0f,0.1f};
	float[] white = new float[]{1.0f,1.0f,1.0f};
	float[] black = new float[]{0.0f,0.0f,0.0f};
	
	/** Unused */
	float[][] cube = new float[][]{yellow,white,red,orange,blue,green};
	
	float cubieWidth;
	float gap;
	float size;
	ArrayList<Cubie> cubies;
	
	public RCube(float cubieWidth, float gap) {
		this.cubieWidth = cubieWidth;
		this.gap = gap;
		this.size = cubieWidth + gap;
		cubies = new ArrayList<Cubie>();
		initCubies();
	}

	private void initCubies() {
		/** First Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,size}, new float[][]{black,black,red,black,black,black})); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,size}, new float[][]{black,white,red,black,blue,black})); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,size}, new float[][]{black,white,red,black,black,black}));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,size}, new float[][]{black,white,red,black,black,green})); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,size}, new float[][]{black,black,red,black,black,green})); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,size}, new float[][]{yellow,black,red,black,black,green})); 	// top right
		cubies.add(new Cubie(new float[]{0,size,size}, new float[][]{yellow,black,red,black,black,black})); 	// top middle
		cubies.add(new Cubie(new float[]{-size,size,size}, new float[][]{yellow,black,red,black,blue,black})); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,size}, new float[][]{black,black,red,black,blue,black})); 	// middle left
	
		/** Middle Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,0}, new float[][]{black,black,black,black,black,black})); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,0}, new float[][]{black,white,black,black,blue,black})); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,0}, new float[][]{black,white,black,black,black,black}));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,0}, new float[][]{black,white,black,black,black,green})); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,0}, new float[][]{black,black,black,black,black,green})); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,0}, new float[][]{yellow,black,black,black,black,green})); 	// top right
		cubies.add(new Cubie(new float[]{0,size,0}, new float[][]{yellow,black,black,black,black,black})); 		// top middle
		cubies.add(new Cubie(new float[]{-size,size,0}, new float[][]{yellow,black,black,black,blue,black})); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,0}, new float[][]{black,black,black,black,blue,black})); 		// middle left
	
		/** Last Layer */
		cubies.add(new Cubie(new float[]{0.0f,0.0f,-size}, new float[][]{black,black,black,orange,black,black})); 	// center
		cubies.add(new Cubie(new float[]{-size,-size,-size}, new float[][]{black,white,black,orange,blue,black})); 	// bottom left
		cubies.add(new Cubie(new float[]{0.0f,-size,-size}, new float[][]{black,white,black,orange,black,black}));	// bottom middle
		cubies.add(new Cubie(new float[]{size,-size,-size}, new float[][]{black,white,black,orange,black,green})); 	// bottom right
		cubies.add(new Cubie(new float[]{size,0,-size}, new float[][]{black,black,black,orange,black,green})); 		// middle right
		cubies.add(new Cubie(new float[]{size,size,-size}, new float[][]{yellow,black,black,orange,black,green})); 	// top right
		cubies.add(new Cubie(new float[]{0,size,-size}, new float[][]{yellow,black,black,orange,black,black})); 		// top middle
		cubies.add(new Cubie(new float[]{-size,size,-size}, new float[][]{yellow,black,black,orange,blue,black})); 	// top left
		cubies.add(new Cubie(new float[]{-size,0,-size}, new float[][]{black,black,black,orange,blue,black})); 		// middle left
	
	}
	
	public ArrayList<Cubie> getCubies() {
		return cubies;
	}

}
