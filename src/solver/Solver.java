package solver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.God;
import cube.Cubie;
import cube.Face;
import cube.RCube;

public class Solver implements ActionListener {
	
	private static final double EPSILON = 5.96e-08;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Solving...");
		solveStageOne();
	}
	
	public void solveStageOne() {
		RCube cube = new RCube(God.getCube());
		// find which pieces aren't in the right positions
		float[][] edgeColours = new float[][]{RCube.RED,RCube.GREEN,RCube.ORANGE,RCube.BLUE};
		
		ArrayList<Cubie> unsolvedCubies = new ArrayList<Cubie>();
		ArrayList<String> howToSolveCubie = new ArrayList<String>();
		ArrayList<float[]> colours = new ArrayList<float[]>();
		
		for(float[] colour : edgeColours) {
			Cubie cubie = cube.getCubie(new float[][]{RCube.WHITE, colour});
			// now we have one of the first layer edges, check if it is in the right position
			float[] desiredPosition = getEdgeFromCentres(cube, RCube.WHITE, colour);
			if (!God.arrayEquals(cubie.getPosition(), (desiredPosition)) | (cubie.getBottomColour() != RCube.WHITE	)) {
				unsolvedCubies.add(cubie);
				howToSolveCubie.add("");
				colours.add(colour);
			}
		}
		
		// then find a solution for each piece
		for (int i = 0; i < unsolvedCubies.size(); i++) {
			Cubie cubie = unsolvedCubies.get(i);
			//check if it is on top layer; if it is not, get it there
			float[] pos = cubie.getPosition();
			if (isOnMiddleLayer(pos)) {
				for (Face face : new Face[]{Face.LEFT,Face.RIGHT,Face.FRONT,Face.BACK}) {
					if (cubie.isOnFace(face)) {
						RCube tempCube = new RCube(cube);
						String getOnTop = face.getCCWTurn().
								concat("U").
								concat(face.getCWTurn());
						
						String getOnTopPrime = face.getCWTurn().
								concat("U").
								concat(face.getCCWTurn());
						
						tempCube.addListToTurnQueue(God.parseTurnsFromString(getOnTop));
						tempCube.performSimulatedTurns();
						if (isOnTopLayer(tempCube.getCubie(new float[][]{colours.get(i),RCube.WHITE}).getPosition())) {
							howToSolveCubie.set(i, howToSolveCubie.get(i).concat(getOnTop));
						} else {
							howToSolveCubie.set(i, howToSolveCubie.get(i).concat(getOnTopPrime));
						}
						break;
					}
				}
			} else if (isOnBottomLayer(pos)) {
				for (Face face : new Face[]{Face.LEFT,Face.RIGHT,Face.FRONT,Face.BACK}) {
					if (cubie.isOnFace(face)) {
						howToSolveCubie.set(i, howToSolveCubie.get(i).concat(face.getCWTurn().concat(face.getCWTurn())));
					}
				}
			}
			
			//check if it is in the right position in top layer; if it is not, get it there
			//first perform previous algorithm on new cube
			RCube partSolvedCube = new RCube(cube);
			partSolvedCube.addListToTurnQueue(God.parseTurnsFromString(howToSolveCubie.get(i)));
			partSolvedCube.performSimulatedTurns();
			
			while (!isAdjacent(
					partSolvedCube.getCubie(new float[][]{colours.get(i),RCube.WHITE}).getPosition(), 
					partSolvedCube.getCubie(new float[][]{colours.get(i)}).getPosition())
					) {
				//find out how many turns to do
				howToSolveCubie.set(i,howToSolveCubie.get(i).concat("U"));
				partSolvedCube.addListToTurnQueue(God.parseTurnsFromString("U"));
				partSolvedCube.performSimulatedTurns();
				
			}
		}
		
		// then find out which one is the best to perform
		int index = -1;
		double shortest = Double.POSITIVE_INFINITY; 
		for (int i = 0; i < howToSolveCubie.size(); i++) {
			if (howToSolveCubie.get(i).length() < shortest) {
				shortest = howToSolveCubie.get(i).length();
				index = i;
			}
		}
		if (index != -1) {
			God.getCube().getCubie(new float[][]{RCube.WHITE,colours.get(index)}).setHighlight(true);
			God.performTurns(God.parseTurnsFromString(howToSolveCubie.get(index)));
			God.getCube().getCubie(new float[][]{RCube.WHITE,colours.get(index)}).setHighlight(false);
		} else {
			System.out.println("First stage solved!");
		}
	}
	
	/*************************************************************************************************/
	
	/**
	 * Gets the position of the edge cubie inbetween the two faces (denoted by colour of center cubie)
	 * @param colour1
	 * @param colour2
	 * @return
	 */
	public static float[] getEdgeFromCentres(RCube cube, float[] colour1, float[] colour2) {
		Cubie cubie1 = cube.getCubie(new float[][]{colour1});
		Cubie cubie2 = cube.getCubie(new float[][]{colour2});
		float[] firstPos = cubie1.getPosition();
		float[] secondPos = cubie2.getPosition();
		float[] newPos = new float[]{
				orOfNumbers(firstPos[0], secondPos[0]),
				orOfNumbers(firstPos[1], secondPos[1]),
				orOfNumbers(firstPos[2], secondPos[2])
		};

		return newPos;
	}

	/**
	 * Returns the number with the highest absolute value.
	 * @param a
	 * @param b
	 * @return
	 */
	public static float orOfNumbers(float a, float b) {
		if (Math.abs(a) > Math.abs(b)) {
			return a;
		} else {
			return b;
		}
	}
	
	/**
	 * Simple check to see if the position is a position on the top layer. Assumes centre of 
	 * the cube is 0 on y axis.
	 * @param pos
	 * @return
	 */
	public static boolean isOnTopLayer(float[] pos) {
		if (pos[1] > 0) {
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
	public static boolean isOnBottomLayer(float[] pos) {
		if (pos[1] < 0) {
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
	public static boolean isOnMiddleLayer(float[] pos) {
		if (pos[1] == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns true if no more than 1 axis has a different value
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static boolean isAdjacent(float[] pos1, float[] pos2) {
		int counter = 0;

		for (int i = 0; i < 3; i++) {
			// check for inequality
			if (Math.abs(pos1[i]/pos2[i] - 1) > EPSILON) {
				if (counter++ != 0) {
					return false;
				}
			}
		}
		return true;
	}
}
