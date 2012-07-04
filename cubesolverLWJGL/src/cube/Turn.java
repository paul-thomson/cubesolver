package cube;

/**
 * A turn is defined by the {@link Face} which is being turned, the current 
 * angle of rotation and whether it is an inverse turn or not (inverse is CCW).
 *
 */
public class Turn {
	
	private float rotationAngle;
	private Face turningFace;
	private boolean inverseTurn;
	
	/**
	 * Creates a default turn with 0 angle, turning face NONE and no inverse turn.
	 */
	public Turn() {
		this.rotationAngle = 0;
		this.turningFace = Face.NONE;
		this.inverseTurn = false;
	}
	
	public Turn(Face turningFace, boolean inverseTurn, float rotationAngle) {
		this.rotationAngle = rotationAngle;
		this.turningFace = turningFace;
		this.inverseTurn = inverseTurn;
	}

	public float getRotationAngle() {
		return rotationAngle;
	}

	public Face getTurningFace() {
		return turningFace;
	}

	public boolean isInverseTurn() {
		return inverseTurn;
	}
}
