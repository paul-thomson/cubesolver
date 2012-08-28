package gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.Main;

/**
 * Sets up the user interface and also adds the OpenGL window to a canvas. Doing this allows us to display 
 * OpenGL inside a swing frame.
 */
public class UserInterface {
	
	Frame frame;
	Canvas canvas;
	public static Dimension openGLSize = new Dimension(750, 500);
	CubePanelInfo[] listOfPanelInfo = new CubePanelInfo[7];
	CubePanelTemplate currentPanel;
	
	public void setup() {
		frame = new Frame("Cube Solver");
		frame.setLayout(new BorderLayout());
		canvas = new Canvas();
		canvas.setSize(openGLSize);

		canvas.addComponentListener(new ComponentAdapter() {
			//maybe does nothing now?
		});

		frame.addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e)
			{ canvas.requestFocusInWindow(); }
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{ Main.requestClose(); }
		});
		
		CubePanelInfo panelInfo1 = new CubePanelInfo("Stage One - Cross", "firststageicon.png","To get the cross you turn the top layer to get a bottom layer piece above the correct position. Then perform the correct algorithm below:");
		panelInfo1.addHint("F F", "s1hint1.png", "The edge is now oriented correctly so swivel it around into the correct position");
		panelInfo1.addHint("U' R' F R", "s1hint2.png", "The edge is not oriented correctly so we position and orient it at the same time. Notice the second R ensures we do not ruin other cross pieces");
		panelInfo1.addHint("L' U' L", "s1hint3.png", "If you need to move an edge piece which will then affect and correct cross piece remember to put the cross piece back");
		listOfPanelInfo[0] = panelInfo1;
		
		CubePanelInfo panelInfo2 = new CubePanelInfo("Stage Two - Corners", "s2icon.png", "Position and orient the corners by positioning a corner piece above its desired location in the first layer. Then perform the following algorithm until it is correctly oriented and positioned");
		panelInfo2.addHint("R U R' U'", "s2hint1.png", "The algorithm should not need to be performed more than 5 times.");
		listOfPanelInfo[1] = panelInfo2;
		
		CubePanelInfo panelInfo3 = new CubePanelInfo("Stage 3 - 2nd layer edges", "s3icon.png", "Position and orient edges by placing a desired edge above the center piece which matches the edge pieces lower colour. Then perform the correct algorithm");
		panelInfo3.addHint("U' L' U L U F U' F'", "s3hint1.png", "Don't know");
		panelInfo3.addHint("U R U' R' U' F' U F", "s3hint2.png", "Don't know");
		listOfPanelInfo[2] = panelInfo3;
		
		CubePanelInfo panelInfo4 = new CubePanelInfo("Stage 4 - Cross", "s4icon.png", "Orient edges to make the cross. Turn the top layer to make one of the shapes below.");
		panelInfo4.addHint("F R U R' U' F'", "s4hint1.png", "Don't know");
		panelInfo4.addHint("F U R U' R' F'", "s4hint2.png", "Don't know");
		panelInfo4.addHint("F R U R' U' F' F U R U' R' F'","s4hint3.png","Perform one, then move the top layer, then move the next");
		listOfPanelInfo[3] = panelInfo4;
		
		CubePanelInfo panelInfo5 = new CubePanelInfo("Stage 5 - Corner orientation", "s5icon.png", "Depending on the number of YELLOW sticks on top, move the top layer to match the images below then perform the algorithm");
		panelInfo5.addHint("R U R' U R U U R", "s5hint1.png", "If no corners have YELLOW stickers on top then put a corner in the bottom left with the sticker facing left");
		panelInfo5.addHint("R U R' U R U U R","s5hint2.png","If one corner has a YELLOW sticker on top put it in the bottom left then perform the algorithm");
		panelInfo5.addHint("R U R' U R U U R", "s5hint3.png", "If two corners have a YELLOW sticker on top put a corner in the bottom left with the YELLOW sticker facing down");
		listOfPanelInfo[4] = panelInfo5;
		
		CubePanelInfo panelInfo6 = new CubePanelInfo("Stage 6 - Corner positioning", "s6icon.png", "In this stage we want to rotate the top layer until we get \"headlights\" at the back of the cube");
		panelInfo6.addHint("R' F R' B B R F' R' B B R R", "s6hint1.png", "The headlights are at the back of the cube so perform the algorithm");
		panelInfo6.addHint("R' F R' B B R F' R' B B R R", "s6hint2.png", "To get headlights simply perform the algorithm then reevaluate");
		listOfPanelInfo[5] = panelInfo6;
		
		CubePanelInfo panelInfo7 = new CubePanelInfo("Stage 7 - Edge positioning", "s7icon.png", "Put the solved edge to the back of the cube and perform the algorithm until the other edges are solved");
		panelInfo7.addHint("R U' R U R U R U' R' U' R R", "s7hint1.png", "Perform the algorithm until the edges are positioned correctly");
		panelInfo7.addHint("R U' R U R U R U' R' U' R R", "s7hint2.png", "No edges are solved so perform the algorithm to solve one edge");
		listOfPanelInfo[6] = panelInfo7;
				
		currentPanel = new CubePanelTemplate(panelInfo1);
		frame.add(currentPanel, BorderLayout.EAST);
		frame.add(canvas, BorderLayout.WEST);		
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(1024, 700));
		frame.pack();
		frame.setVisible(true);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	/**
	 * This will error if 0 or 8+ is given
	 * @param stageNumber
	 */
	public void changePanel(int stageNumber) {
		CubePanelTemplate panel = new CubePanelTemplate(listOfPanelInfo[stageNumber-1]);
		frame.add(panel,BorderLayout.EAST);
		frame.remove(currentPanel);
		frame.revalidate();
		currentPanel = panel;
	}

	public void setStage(Stage stage) {
		changePanel(stage.getNumber());
	}
}
