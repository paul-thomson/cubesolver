package solver;

import gui.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import main.God;
import cube.Cubie;
import cube.Face;
import cube.Position;
import cube.RCube;

public class Solver implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Solving...");
		if (God.getCube().isSolved()) {
			System.out.println("SOLVED");
			return;
		}
		Stage currentStage = God.getStage();
		switch (currentStage) {
		case ONE: 
			solveStageOne();
			break;
		case TWO:
			solveStageTwo();
			break;
		case THREE:
			solveStageThree();
			break;
		case FOUR:
			solveStageFour();
			break;
		case FIVE:
			solveStageFive();
			break;
		case SIX:
			solveStageSix();
			break;
		case SEVEN:
			solveStageSeven();
			break;
		
		}
		
	}

	private void solveStageOne() {
		// find which pieces aren't in the right positions
		float[][] edgeColours = new float[][]{RCube.RED,RCube.GREEN,RCube.ORANGE,RCube.BLUE};
		double shortestSolutionLength = Double.POSITIVE_INFINITY;
		String shortestSolution = "";

		for(float[] colour : edgeColours) {
			String currentSolution = "";
			RCube cube = new RCube(God.getCube());
			Cubie cubie = cube.getCubie(new float[][]{RCube.WHITE, colour});
			// now we have one of the first layer edges, check if it is in the correct position
			if (cube.isCubieSolved(cube, cubie)) {
				continue;
			}

			/* We now want the edge to go on to the top layer so we have 
			 * two different procedures: one for if the edge is on the middle 
			 * layer and one if it is on the bottom layer
			 */
			Position startingPos = cubie.getPosition();
			if (startingPos.isOnMiddleLayer()) {
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
						if (tempCube.getCubie(new float[][]{colour,RCube.WHITE}).getPosition().isOnTopLayer()) {
							currentSolution += getOnTop;
						} else {
							currentSolution += getOnTopPrime;
						}
						break;
					}
				}
			} else if (startingPos.isOnBottomLayer()) {
				for (Face face : new Face[]{Face.LEFT,Face.RIGHT,Face.FRONT,Face.BACK}) {
					if (cubie.isOnFace(face)) {
						currentSolution += face.getCWTurn();
						currentSolution += face.getCWTurn();
					}
				}
			}
			
			//do not need an "else" because if it is on top layer we do nothing

			//check if it is in the right position in top layer; if it is not, get it there
			//first perform previous algorithm on new cube
			cube.addListToTurnQueue(God.parseTurnsFromString(currentSolution));
			cube.performSimulatedTurns();

			// check if the edge we are working with is adjacent to the center piece
			while (!cube.getCubie(new float[][]{colour,RCube.WHITE}).getPosition().
					isAdjacent( 
							cube.getCubie(new float[][]{colour}).getPosition())
					) {
				//do one turn then check again
				currentSolution += "U";
				cube.addListToTurnQueue(God.parseTurnsFromString("U"));
				cube.performSimulatedTurns();
			}
			
			while (!cubie.isOnFace(Face.FRONT)) {
				currentSolution += "Y";
				cube.addListToTurnQueue(God.parseTurnsFromString("Y"));
				cube.performSimulatedTurns();
			}
			currentSolution = God.cleanUpTurns(currentSolution);
			// we only want to perform the shortest solution
			if (currentSolution.length() < shortestSolutionLength) {
				shortestSolution = currentSolution;
				shortestSolutionLength = shortestSolution.length();
			}
		}

		if (shortestSolutionLength != Double.POSITIVE_INFINITY) {
			God.performTurns(God.parseTurnsFromString(shortestSolution));
		} else {
			System.out.println("First stage solved!");
			God.nextStage();
		}
	}
	
	private void solveStageTwo() {
		//TODO add += to currentsolution DUH
		double shortestSolutionLength = Double.POSITIVE_INFINITY;
		String shortestSolution = "";

		float[][] cornerColours = new float[][]{RCube.RED,RCube.GREEN,RCube.ORANGE,RCube.BLUE};

		for (int i = 0; i < cornerColours.length; i++) {
			String currentSolution = "";
			RCube cube = new RCube(God.getCube());
			float[] colour1 = cornerColours[i];
			float[] colour2 = cornerColours[(i+1) % cornerColours.length];
			Cubie cubie = cube.getCubie(new float[][]{colour1, colour2, RCube.WHITE});

			if (cube.isCubieSolved(cube, cubie)) {
				continue;
			}

			// now we know the corner is not correctly solved so SOLVE IT
			if (cubie.getPosition().isOnBottomLayer()) {

				// put cubie to front right position, then perform algorithm
				while (!(cubie.isOnFace(Face.FRONT) & cubie.isOnFace(Face.RIGHT))) {
					currentSolution = currentSolution + "Y";
					cube.addListToTurnQueue(God.parseTurnsFromString("Y"));
					cube.performSimulatedTurns();
				}
				currentSolution = currentSolution + "RUR'";
				cube.addListToTurnQueue(God.parseTurnsFromString("RUR'"));
				cube.performSimulatedTurns();
			} 

			// now it must be on top layer so rotate UP until in correct position

			Position pos = cube.getCornerPosition(colour1, colour2, RCube.YELLOW);
			while (!cubie.getPosition().equals(pos)) {
				currentSolution = currentSolution + "U";
				cube.addListToTurnQueue(God.parseTurnsFromString("U"));
				cube.performSimulatedTurns();
			}

			//turn to front
			while (!(cubie.isOnFace(Face.FRONT) & cubie.isOnFace(Face.RIGHT))) {
				currentSolution = currentSolution + "Y";
				cube.addListToTurnQueue(God.parseTurnsFromString("Y"));
				cube.performSimulatedTurns();
			}
			currentSolution = God.cleanUpTurns(currentSolution);

			// we only want to perform the shortest solution
			if (currentSolution.length() < shortestSolutionLength) {
				shortestSolution = currentSolution;
				shortestSolutionLength = shortestSolution.length();
			}

		}
		if (shortestSolutionLength != Double.POSITIVE_INFINITY) {
			God.performTurns(God.parseTurnsFromString(shortestSolution));
		} else {
			System.out.println("Second stage solved!");
			God.nextStage();
		}
	}

	
	private void solveStageThree() {
		float[][] edgeColours = new float[][]{RCube.RED,RCube.GREEN,RCube.ORANGE,RCube.BLUE};
		double shortestSolutionLength = Double.POSITIVE_INFINITY;
		String shortestSolution = "";
	
		for(int i = 0; i < edgeColours.length; i++) {
			String currentSolution = "";
			RCube cube = new RCube(God.getCube());
			float[] colour1 = edgeColours[i];
			float[] colour2 = edgeColours[(i+1) % edgeColours.length];
			Cubie cubie = cube.getCubie(new float[][]{colour1, colour2});
			
			if (cube.isCubieSolved(cube, cubie)) {
				continue;
			}
			
			if (cubie.getPosition().isOnMiddleLayer()) {
				while (!(cubie.isOnFace(Face.FRONT) & cubie.isOnFace(Face.RIGHT))) {
					currentSolution += "Y";
					cube.addListToTurnQueue(God.parseTurnsFromString("Y"));
					cube.performSimulatedTurns();
				}
				currentSolution += "U R U' R' U' F' U F";
			}
			
			//now it is on top layer
			
			float[] frontColour = Arrays.equals(cubie.getTopColour(),colour1) ? colour2 : colour1;
			
			Cubie center = cube.getCubie(new float[][]{frontColour});
			while (!center.isOnFace(Face.FRONT)) {
				currentSolution += "Y";
				cube.addListToTurnQueue(God.parseTurnsFromString("Y"));
				cube.performSimulatedTurns();
			}
			
			while (!cubie.isOnFace(Face.FRONT)) {
				currentSolution += "U";
				cube.addListToTurnQueue(God.parseTurnsFromString("U"));
				cube.performSimulatedTurns();
			}
			currentSolution = God.cleanUpTurns(currentSolution);
			// we only want to perform the shortest solution
			if (currentSolution.length() < shortestSolutionLength) {
				shortestSolution = currentSolution;
				shortestSolutionLength = shortestSolution.length();
			}
		}
		if (shortestSolutionLength != Double.POSITIVE_INFINITY) {
			God.performTurns(God.parseTurnsFromString(shortestSolution));
		} else {
			System.out.println("Third stage solved!");
			God.nextStage();
		}
	}

	private void solveStageFour() {
		// TODO Auto-generated method stub
		
	}

	private void solveStageFive() {
		// TODO Auto-generated method stub
		
	}

	private void solveStageSix() {
		// TODO Auto-generated method stub
		
	}

	private void solveStageSeven() {
		// TODO Auto-generated method stub
		
	}
}
