package cube;

import main.God;

public class Position {

	private float x;
	private float y;
	private float z;
	public static final double EPSILON = 5.96e-08;
	
	public Position(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(float[] pos) {
		this.x = pos[0];
		this.y = pos[1];
		this.z = pos[2];
	}
	
	public Position(Position position) {
		this.x = position.X();
		this.y = position.Y();
		this.z = position.Z();
	}
	
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]"; 
	}
	
	public float[] toArray() {
		return new float[]{x,y,z};
	}
	
	public float X() {
		return x;
	}
	
	public float Y() {
		return y;
	}
	
	public float Z() {
		return z;
	}
	
	public boolean equals(Position pos) {
		float[] a = toArray();
		float[] b = pos.toArray();
		return God.arrayEquals(a, b);
	}

	/**
	 * Simple check to see if the position is a position on the top layer. Assumes centre of 
	 * the cube is 0 on y axis.
	 * @param pos
	 * @return
	 */
	public boolean isOnTopLayer() {
		if (y > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Simple check to see if the position is a position on the top layer. Assumes centre of 
	 * the cube is 0 on y axis.
	 * @param pos
	 * @return
	 */
	public boolean isOnBottomLayer() {
		if (y < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Simple check to see if the position is a position on the top layer. Assumes centre of 
	 * the cube is 0 on y axis.
	 * @param pos
	 * @return
	 */
	public boolean isOnMiddleLayer() {
		if (y == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if no more than 1 axis has a different value
	 * @return
	 */
	public boolean isAdjacent(Position adjPos) {
		int counter = 0;
		float[] pos = toArray();
		float[] adj = adjPos.toArray();
		for (int i = 0; i < 3; i++) {
			// check for inequality
			if (Math.abs(pos[i]/adj[i] - 1) > EPSILON) {
				if (counter++ != 0) {
					return false;
				}
			}
		}
		return true;
	}
}
