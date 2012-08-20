package cube;

/**
 * The different faces of a cube. NONE is also used for a default value.
 *
 */
public enum Face {
	FRONT ("F","F'"),
	Z ("Z","Z'"),
	BACK ("B","B'"),
	UP ("U","U'"),
	Y ("Y","Y'"),
	DOWN ("D","D'"),
	LEFT ("L","L'"),
	RIGHT ("R","R'"),
	X ("X","X'"),
	NONE ("N","N'");
	
	private final String turnCW;
	private final String turnCCW;
	
	private Face (String turnCW, String turnCCW) {
		this.turnCW = turnCW;
		this.turnCCW = turnCCW;
	}
	
	public static Face getFaceFromChar(char character) {
		//TODO find better way to do this
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
			System.err.println("Could not find face from char : " + character);
			return NONE;
		}
	}
	
	public String getCWTurn() {
		return turnCW;
	}
	
	public String getCCWTurn() {
		return turnCCW;
	}
}
