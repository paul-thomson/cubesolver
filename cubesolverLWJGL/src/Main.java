import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import world.World;

public class Main extends JFrame {

	private static boolean closeRequested = false;
	private static Dimension openGLSize = new Dimension(750, 500);

	public static void main(String[] args)
	{
		Frame frame = new Frame("Cube Solver");
		frame.setLayout(new BorderLayout());
		final Canvas canvas = new Canvas();
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
			{ closeRequested = true; }
		});
		World world = new World();
		
		CubePanelInfo panelInfo1 = new CubePanelInfo("Stage One - Cross", "firststageicon.png","To get the cross you turn the top layer to get a bottom layer piece above the correct position. Then perform the correct algorithm below:");
		panelInfo1.addHint("F F", "s1hint1.png", "The edge is now oriented correctly so swivel it around into the correct position");
		panelInfo1.addHint("U' R' F R", "s1hint2.png", "The edge is not oriented correctly so we position and orient it at the same time. Notice the second R ensures we do not ruin other cross pieces");
		panelInfo1.addHint("L' U' L", "s1hint3.png", "If you need to move an edge piece which will then affect and correct cross piece remember to put the cross piece back");
		
		CubePanelInfo panelInfo2 = new CubePanelInfo("Stage Two - Corners", "s2icon.png", "Position and orient the corners by positioning a corner piece above its desired location in the first layer. Then perform the following algorithm until it is correctly oriented and positioned");
		panelInfo2.addHint("R U R' U'", "s2hint1.png", "The algorithm should not need to be performed more than 6 times.");
		
		CubePanelInfo panelInfo3 = new CubePanelInfo("Stage 3 - 2nd layer edges", "s3icon.png", "Position and orient edges by placing a desired edge above the center piece which matches the edge pieces lower colour. Then perform the correct algorithm");
		panelInfo3.addHint("U' L' U L U F U' F'", "s3hint1.png", "Don't know");
		panelInfo3.addHint("U R U' R U' F' U F", "s3hint2.png", "Don't know");
		
		CubePanelInfo panelInfo4 = new CubePanelInfo("Stage 4 - Cross", "s4icon.png", "Orient edges to make the cross. Turn the top layer to make one of the shapes below.");
		panelInfo4.addHint("F R U R' U' F'", "s4hint1.png", "Don't know");
		panelInfo4.addHint("F U R U' R' F'", "s4hint2.png", "Don't know");
		panelInfo4.addHint("F R U R' U' F' F U R U' R' F'","s4hint3.png","Perform one, then move the top layer, then move the next");
		
		CubePanelTemplate cubePanel = new CubePanelTemplate(world,panelInfo4);
		
		frame.add(cubePanel, BorderLayout.EAST);		
		frame.add(canvas, BorderLayout.WEST);

		try {
			Display.setParent(canvas);
			Display.setVSyncEnabled(true);
			
			frame.setResizable(false);
			frame.setMinimumSize(new Dimension(1024, 700));
			frame.pack();
			frame.setVisible(true);
			Display.create();
			
			world.initialiseRendering();

			while(!Display.isCloseRequested() && !closeRequested) {
				
				GL11.glViewport(0, 0, openGLSize.width, openGLSize.height);

				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				world.render();
				Display.update();
			}

			Display.destroy();
			frame.dispose();
			System.exit(0);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
}