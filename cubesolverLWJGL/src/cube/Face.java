package cube;

/**
 * The different faces of a cube. NONE is also used for a default value.
 *
 */
public enum Face {
	FRONT,
	Z,
	BACK,
	UP,
	Y,
	DOWN,
	LEFT,
	RIGHT,
	X,
	NONE;
	
	public static Face getFaceFromChar(char character) {
		switch (character) {
		case('F'):
			return FRONT;
		case('Z'):
			return Z;
		case('B'):
			return BACK;
		case('U'):
			return UP;
		case('Y'):
			return Y;
		case('D'):
			return DOWN;
		case('L'):
			return LEFT;
		case('R'):
			return RIGHT;
		case('X'):
			return X;
		default:
			System.err.println("Could not find face from char");
			return NONE;
		}
	}
}
