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
	private static JPanel cubePanel;

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
		cubePanel = new CubePanel();
		
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
			
			World world = new World();
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