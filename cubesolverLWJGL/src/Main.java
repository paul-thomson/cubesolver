import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import world.World;

public class Main extends JFrame{

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
		
//		cubePanel = new CubePanelOld(world);
		CubePanelInfo panelInfo = new CubePanelInfo("Stage One - Cross", "firststageicon.png","To get the cross you turn the top layer to get a bottom layer piece above the correct position. Then perform the correct algorithm below:");
		panelInfo.addHint("F F", "s1hint1.png", "The edge is now oriented correctly so swivel it around into the correct position");
		panelInfo.addHint("U' R' F R", "s1hint2.png", "The edge is not oriented correctly so we position and orient it at the same time. Notice the second R ensures we do not ruin other cross pieces");
		panelInfo.addHint("L' U' L", "s1hint3.png", "If you need to move an edge piece which will then affect and correct cross piece remember to put the cross piece back");
		
		CubePanelTemplate cubePanel = new CubePanelTemplate(world,panelInfo);
		
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

			while(!Display.isCloseRequested() && !closeRequested)
			{
				
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