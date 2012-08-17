package solver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import main.God;
import cube.Cubie;
import cube.RCube;

public class Solver implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO SOLVE THE CUBE
		System.out.println("NOT DONE YET");
		solveStageOne();
	}
	
	public void solveStageOne() {
		RCube cube = God.getCube();
		//first break down the problem: find which pieces aren't in the right positions
		float[][] edgeColours = new float[][]{RCube.RED,RCube.GREEN,RCube.ORANGE,RCube.BLUE};
		ArrayList<Cubie> unsolvedCubies = new ArrayList<Cubie>();
		
		for(float[] colour : edgeColours) {
			Cubie cubie = cube.getCubie(new float[][]{RCube.WHITE, colour});
			// now we have one of the first layer edges, check if it is in the right position
			float[] desiredPosition = cube.getEdgeFromCentres(RCube.WHITE, colour);
			System.out.println("desiredPosition = " + desiredPosition[0] + ", "
													+ desiredPosition[1] + ", "
													+ desiredPosition[2]);
			System.out.println("cubiePosition = " 	+ cubie.getPosition()[0] + ", "
													+ cubie.getPosition()[1] + ", "
													+ cubie.getPosition()[2]);
			if (Arrays.equals(cubie.getPosition(), (desiredPosition))) {
				unsolvedCubies.add(cubie);
			}
		}
		
		// then find a solution for each piece
		for (Cubie cubie : unsolvedCubies) {
			// probably methods in RCube to get into top position which can start with getting to top layer
			
			
			
		}
		
		// then find out which one is the best to perform
	}
}
