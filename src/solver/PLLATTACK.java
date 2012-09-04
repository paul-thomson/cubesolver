package solver;

import java.util.ArrayList;
import java.util.Arrays;

import main.God;
import cube.RCube;
import cube.Turn;

/**
 * All caps means it is a better class
 *
 */
public class PLLATTACK {
	private static int numberOfCubes = 0;
	private static double lastTime = 0;

	public static void performTheAttack() {
		ArrayList<Turn[]> turns = new ArrayList<Turn[]>();
		ArrayList<Turn> parsed = new ArrayList<Turn>(); 
//		//G(d)
//		parsed = God.parseTurnsFromString("RUR'y'RRu'RU'R'UR'uRR");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//G(c)
//		parsed = God.parseTurnsFromString("RRu'RU'RUR'uRRyRU'R'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//G(b)
//		parsed = God.parseTurnsFromString("R'U'RyRRuR'URU'Ru'RR");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//G(a)
//		parsed = God.parseTurnsFromString("RRuR'UR'U'Ru'RRy'R'UR");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//U(b)
//		parsed = God.parseTurnsFromString("RU'RURURU'R'U'RR");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//U(a)
//		parsed = God.parseTurnsFromString("RRURUR'U'R'U'R'UR'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
		

//		//Y
//		parsed = God.parseTurnsFromString("FRU'R'U'RUR'F'RUR'U'R'FRF'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//J(b)
//		parsed = God.parseTurnsFromString("RUR'F'RUR'U'R'FRRU'R'U'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//J(a)
//		parsed = God.parseTurnsFromString("R'UL'UURU'R'UURLU'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//N(b)
//		parsed = God.parseTurnsFromString("R'UL'UURU'LR'UL'UURU'L");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//N(a)
//		parsed = God.parseTurnsFromString("LU'RUUL'UR'LU'RUUL'UR'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//R(b)
//		parsed = God.parseTurnsFromString("R'UURUUR'FRUR'U'R'F'RRU'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
//		//H
//		parsed = God.parseTurnsFromString("M'M'U'M'M'U'U'M'M'U'M'M'");
//		turns.add(parsed.toArray(new Turn[parsed.size()]));
		
		
		//E
		parsed = God.parseTurnsFromString("xRU'R'DRUR'uuR'URDR'U'Rx'");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//T
		parsed = God.parseTurnsFromString("RUR'U'R'FRRU'R'U'RUR'F'");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//A(b)
		parsed = God.parseTurnsFromString("x'RU'RDDR'URDDRRx");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//A(a)
		parsed = God.parseTurnsFromString("xR'UR'DDRU'R'DDRRx'");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//Z
		parsed = God.parseTurnsFromString("M'M'U'M'M'U'M'U'U'M'M'U'U'M'U'U'");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//R(a)
		parsed = God.parseTurnsFromString("LUUL'UULF'L'U'LULFLLU");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//F
		parsed = God.parseTurnsFromString("R'UUR'd'R'F'RRU'R'UR'FRU'F");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		//V
		parsed = God.parseTurnsFromString("R'UR'd'R'F'RRU'R'UR'FRF");
		turns.add(parsed.toArray(new Turn[parsed.size()]));
		
		permutation(turns);
		System.out.println("PLLATTACK failed");
		System.exit(0);
	}

	public  static void permutation(ArrayList<Turn[]> turns) {
		lastTime = System.currentTimeMillis();
		permutation(new ArrayList<Turn[]>(), turns); 
		System.out.println("Time taken = " + (double) (System.currentTimeMillis() - lastTime)/1000);
		
	}

	private static void permutation(ArrayList<Turn[]> prefix, ArrayList<Turn[]> turns) {
		int n = turns.size();
		if (n == 0) {
			numberOfCubes++;
			System.out.println(numberOfCubes);
			RCube cube = new RCube(2.0f,0.05f);
			for (int i = 0; i < prefix.size(); i++) {
				cube.addListToTurnQueue(Arrays.asList(prefix.get(i)));
				cube.performSimulatedTurns();
				if (cube.isSolved()) {
					System.out.println("At position " + i + " in the list the cube is solved: ");
					printListOfTurns(prefix);
					System.out.println("Time taken = " + (double) (System.currentTimeMillis() - lastTime)/1000);
					System.exit(0);
				}
			}
			if (cube.isSolved()) {
				System.out.println("All turns completed: ");
				printListOfTurns(prefix);
				System.out.println("Time taken = " + (double) (System.currentTimeMillis() - lastTime)/1000);
				System.exit(0);
			}
		} else {
			for (int i = 0; i < n; i++) {
				ArrayList<Turn[]> prefixNew = new ArrayList<Turn[]>(prefix);
				ArrayList<Turn[]> turnsNew = new ArrayList<Turn[]>(turns);
				prefixNew.add(turns.get(i));
				turnsNew.remove(i);
				permutation(prefixNew, turnsNew);
			}
		}
	}

	public static void printListOfTurns(ArrayList<Turn[]> turns) {
		for (Turn[] algs : turns) {
			for (Turn turn : algs) {
				System.out.print(turn);
			}
			System.out.println("");
		}
	}

}
