import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import static org.lwjgl.opengl.GL11.*; // static import means it does not need to be explicitly referenced


public class Main2 {

	/** position of quad */
	float x = 0, y = 0, z = -2;
	/** angle of quad rotation */
	float rotation = 0;

	/** time at last frame */
	long lastFrame;

	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	/** Colours */
	float[] red = new float[]{1.0f,0.0f,0.0f};
	float[] green = new float[]{0.0f,1.0f,0.0f};
	float[] blue = new float[]{0.0f,0.0f,1.0f};
	float[] orange = new float[]{1.0f,0.4f,0.1f};
	float[] yellow = new float[]{1.0f,1.0f,0.1f};
	float[] white = new float[]{1.0f,1.0f,1.0f};
	float[] black = new float[]{0.0f,0.0f,0.0f};
	
	float[][] cube = new float[][]{yellow,white,red,orange,blue,green};

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();

			update(delta);
			renderGL();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		rotation += 0.15f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.01f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.01f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y += 0.01f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y -= 0.01f * delta;
		
		// keep quad on the screen
//		if (x < 0) x = 0;
//		if (x > 800) x = 800;
//		if (y < 0) y = 0;
//		if (y > 600) y = 600;

		updateFPS(); // update FPS Counter
	}

	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		glClearDepth(1.0); // Depth Buffer Setup
		glEnable(GL_DEPTH_TEST); // Enables Depth Testing
		glDepthFunc(GL_LEQUAL); // The Type Of Depth Testing To Do
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); // Reset The Projection Matrix

		// Calculate The Aspect Ratio Of The Window
		DisplayMode displayMode = Display.getDisplayMode();
		GLU.gluPerspective(
				45.0f,
				(float) displayMode.getWidth() / (float) displayMode.getHeight(),
				0.1f,
				100.0f);
		glMatrixMode(GL_MODELVIEW); // Select The Modelview Matrix

		// Really Nice Perspective Calculations
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}

	public void renderGL() {
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 

		glLoadIdentity();                   // Reset The Current Modelview Matrix
		glTranslatef(x,y,-15.0f);
		glRotatef(45, 0, 1, 0);
		
			/** Front layer */
			glPushMatrix();
				glTranslatef(0.0f,0.0f,2.05f);
				drawSmallCube(black,black,red,black,black,black);
			glPopMatrix();
				
			glPushMatrix();
				glTranslatef(-2.05f,-2.05f,2.05f);		// bottom left
				drawSmallCube(black,white,red,black,blue,black);
			glPopMatrix();
				
			glPushMatrix();
				glTranslatef(0.0f,-2.05f,2.05f);		// bottom middle
				drawSmallCube(black,white,red,black,black,black);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(2.05f,-2.05f,2.05f);		// bottom right
				drawSmallCube(black,white,red,black,black,green);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(2.05f,0.0f,2.05f);		// middle right
				drawSmallCube(black,black,red,black,black,green);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(2.05f,2.05f,2.05f);		// top right
				drawSmallCube(yellow,black,red,black,black,green);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(0.0f,2.05f,2.05f);		// top middle
				drawSmallCube(yellow,black,red,black,black,black);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(-2.05f,2.05f,2.05f);		// top left
				drawSmallCube(yellow,black,red,black,blue,black);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(-2.05f,0.0f,2.05f);		// middle left
				drawSmallCube(black,black,red,black,blue,black);
			glPopMatrix();
			
			
			/** Middle Layer */
			
			glPushMatrix();
				glTranslatef(0.0f,0.0f,0.0f);
				drawSmallCube(black,black,black,black,black,black);
			glPopMatrix();
				
			glPushMatrix();
				glTranslatef(-2.05f,-2.05f,0.0f);		// bottom left
				drawSmallCube(black,white,black,black,blue,black);
			glPopMatrix();
				
			glPushMatrix();
				glTranslatef(0.0f,-2.05f,0.0f);		// bottom middle
				drawSmallCube(black,white,black,black,black,black);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(2.05f,-2.05f,0.0f);		// bottom right
				drawSmallCube(black,white,black,black,black,green);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(2.05f,0.0f,0.0f);		// middle right
				drawSmallCube(black,black,black,black,black,green);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(2.05f,2.05f,0.0f);		// top right
				drawSmallCube(yellow,black,black,black,black,green);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(0.0f,2.05f,0.0f);		// top middle
				drawSmallCube(yellow,black,black,black,black,black);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(-2.05f,2.05f,0.0f);		// top left
				drawSmallCube(yellow,black,black,black,blue,black);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(-2.05f,0.0f,0.0f);		// middle left
				drawSmallCube(black,black,black,black,blue,black);
			glPopMatrix();
			
		/** Back Layer */
			glPushMatrix();
				glTranslatef(0.0f,0.0f,-2.05f);
				drawSmallCube(black,black,black,orange,black,black);
			glPopMatrix();
				
			glPushMatrix();
				glTranslatef(-2.05f,-2.05f,-2.05f);		// bottom left
				drawSmallCube(black,white,black,orange,blue,black);
			glPopMatrix();
				
			glPushMatrix();
				glTranslatef(0.0f,-2.05f,-2.05f);		// bottom middle
				drawSmallCube(black,white,black,orange,black,black);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(2.05f,-2.05f,-2.05f);		// bottom right
				drawSmallCube(black,white,black,orange,black,green);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(2.05f,0.0f,-2.05f);		// middle right
				drawSmallCube(black,black,black,orange,black,green);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(2.05f,2.05f,-2.05f);		// top right
				drawSmallCube(yellow,black,black,orange,black,green);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(0.0f,2.05f,-2.05f);		// top middle
				drawSmallCube(yellow,black,black,orange,black,black);
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(-2.05f,2.05f,-2.05f);		// top left
				drawSmallCube(yellow,black,black,orange,blue,black);
			glPopMatrix();
			
			glPushMatrix();
				glTranslatef(-2.05f,0.0f,-2.05f);		// middle left
				drawSmallCube(black,black,black,orange,blue,black);
			glPopMatrix();
			
		glPopMatrix();
	}

	/**
	 * Draws a cube with the given colours
	 * @param c0 Up
	 * @param c1 Down
	 * @param c2 Front
	 * @param c3 Back
	 * @param c4 Left
	 * @param c5 Right
	 */
	private void drawSmallCube(float[] c0, float[] c1,
							float[] c2, float[] c3,
							float[] c4, float[] c5) {
		glBegin(GL_QUADS);
		glColor3f(c0[0],c0[1],c0[2]);             // Set The Color To Green
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
		
		glColor3f(c1[0],c1[1],c1[2]);             // Set The Color To Orange
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
		
		glColor3f(c2[0],c2[1],c2[2]);             // Set The Color To Red
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
		
		glColor3f(c3[0],c3[1],c3[2]);             // Set The Color To Yellow
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
		
		glColor3f(c4[0],c4[1],c4[2]);             // Set The Color To Blue
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
		
		glColor3f(c5[0],c5[1],c5[2]);             // Set The Color To Violet
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
		glEnd();

	}

	/**
	 * Draw a cube around the given 3d point... but this shouldn't 
	 * be used because instead you should translate first and draw around origin.
	 * @param x
	 * @param y
	 * @param z
	 */
	@SuppressWarnings("unused")
	private void drawCubeSilly(float x, float y, float z) {
		glColor3f(x,1.0f,0.0f);             // Set The Color To Green
		glVertex3f(x + 1,y + 1,z - 1);         // Top Right Of The Quad (Top)
		glVertex3f(x - 1,y + 1,z - 1);         // Top Left Of The Quad (Top)
		glVertex3f(x - 1,y + 1,z + 1);         // Bottom Left Of The Quad (Top)
		glVertex3f(x + 1,y + 1,z + 1);         // Bottom Right Of The Quad (Top)

		glColor3f(1.0f,0.5f,0.0f);             // Set The Color To Orange
		glVertex3f(x + 1,y - 1,z + 1);         // Top Right Of The Quad (Bottom)
		glVertex3f(x - 1,y - 1,z + 1);         // Top Left Of The Quad (Bottom)
		glVertex3f(x - 1,y - 1,z - 1);         // Bottom Left Of The Quad (Bottom)
		glVertex3f(x + 1,y - 1,z - 1);         // Bottom Right Of The Quad (Bottom)

		glColor3f(1.0f,0.0f,0.0f);             // Set The Color To Red
		glVertex3f(x + 1,y + 1,z + 1);         // Top Right Of The Quad (Front)
		glVertex3f(x - 1,y + 1,z + 1);         // Top Left Of The Quad (Front)
		glVertex3f(x - 1,y - 1,z + 1);         // Bottom Left Of The Quad (Front)
		glVertex3f(x + 1,y - 1,z + 1);         // Bottom Right Of The Quad (Front)

		glColor3f(1.0f,1.0f,0.0f);             // Set The Color To Yellow
		glVertex3f(x + 1,y - 1,z - 1);         // Bottom Left Of The Quad (Back)
		glVertex3f(x - 1,y - 1,z - 1);         // Bottom Right Of The Quad (Back)
		glVertex3f(x - 1,y + 1,z - 1);         // Top Right Of The Quad (Back)
		glVertex3f(x + 1,y + 1,z - 1);         // Top Left Of The Quad (Back)

		glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
		glVertex3f(-1.0f,y + 1,z+  1);         // Top Right Of The Quad (Left)
		glVertex3f(-1.0f,y + 1,z - 1);         // Top Left Of The Quad (Left)
		glVertex3f(-1.0f,y - 1,z - 1);         // Bottom Left Of The Quad (Left)
		glVertex3f(-1.0f,y - 1,z + 1);         // Bottom Right Of The Quad (Left)

		glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
		glVertex3f( 1.0f,y + 1,z - 1);         // Top Right Of The Quad (Right)
		glVertex3f( 1.0f,y + 1,z + 1);         // Top Left Of The Quad (Right)
		glVertex3f( 1.0f,y - 1,z + 1);         // Bottom Left Of The Quad (Right)
		glVertex3f( 1.0f,y - 1,z - 1);         // Bottom Right Of The Quad (Right)
	}

	public static void main(String[] argv) {
		Main2 starter = new Main2();
		starter.start();
	}
}
