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
	
	MIDDLE("M","M'"),
	S("S","S'"),
	E("E","E'"),
	UP2("u","u'"),
	DOWN2("d","d'"),
	LEFT2("l","l'"),
	RIGHT2("r","r'"),
	FRONT2("f","f'"),
	BACK2("b","b'"),
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
		case('z'):
			return Z;
		case('B'):
			return BACK;
		case('U'):
			return UP;
		case('Y'):
		case('y'):
			return Y;
		case('D'):
			return DOWN;
		case('L'):
			return LEFT;
		case('R'):
			return RIGHT;
		case('X'):
		case('x'):
			return X;
		case('f'):
			return FRONT2;
		case('b'):
			return BACK2;
		case('u'):
			return UP2;
		case('d'):
			return DOWN2;
		case('l'):
			return LEFT2;
		case('r'):
			return RIGHT2;
		case('M'):
			return MIDDLE;
		case('S'):
			return S;
		case('E'):
			return E;
		default:
			System.err.println("Could not find face from char : \"" + character + "\"");
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
