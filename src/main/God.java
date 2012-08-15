package main;

import java.util.ArrayList;

import cube.RCube;
import cube.Turn;
import world.World;

/**
 * 
 * 
 *
 */
public class God {

	static World world;
	static RCube cube;

	public static void setWorld(World newWorld) {
		world = newWorld;		
	}

	public static void setCube(RCube newCube) {
		cube = newCube;		
	}

	public static void performTurns(ArrayList<Turn> turns) {
		world.performTurns(turns);

	}

	public static RCube getCube() {
		return cube;
	}

}
