import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import static org.lwjgl.opengl.GL11.*; // static import means it does not need to be explicitly referenced


public class Main {

	/** position of quad */
	float POS_X = 0, POS_Y = 0, POS_Z = -2;
	/** angle of quad rotation */
	float rotation = 0;
	
	float ROT_X = 20;
	float ROT_Y = 35;

	/** time at last frame */
	long lastFrame;

	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	

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

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) POS_X -= 0.01f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) POS_X += 0.01f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) POS_Y += 0.01f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) POS_Y -= 0.01f * delta;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) ROT_X -= 0.05f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) ROT_X += 0.05f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) ROT_Y += 0.05f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) ROT_Y -= 0.05f * delta;

		// reset
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			POS_X = 0;
			POS_Y = 0;
			ROT_X = 20;
			ROT_Y = 35;
		}

		// keep quad on the screen
//		if (POS_X < 0) POS_X = 0;
//		if (POS_X > 800) POS_X = 800;
//		if (POS_Y < 0) POS_Y = 0;
//		if (POS_Y > 600) POS_Y = 600;

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
		glTranslatef(POS_X,POS_Y,-17.0f);
		glRotatef(ROT_X, 1, 0, 0);
		glRotatef(ROT_Y, 0, 1, 0);
		
		RCube cube = new RCube(2.0f,0.05f);
		drawCube(cube);
			
		glPopMatrix();
	}

	/**
	 * Create the internal representation of the rubik's cube
	 */
	private void drawCube(RCube cube) {
		ArrayList<Cubie> cubies = cube.getCubies();
		for (Cubie cubie : cubies) {
			drawCubie(cubie);
		}
	}
	
	/**
	 * Draws a cube from the cubie object. Essentially converting from the Java view 
	 * of an object into the OpenGL view.
	 * @param cubie
	 */
	private void drawCubie(Cubie cubie) {
		float[] position = cubie.getPosition();
		float[][] faceColours = cubie.getFaceColours();
		
		glPushMatrix();
			glTranslatef(position[0],position[1],position[2]);
			drawSmallCube(faceColours);
		glPopMatrix();
		
	}

	/**
	 * Draws a cube with the given colours
	 * @param c Up
	 * @param c Down
	 * @param c Front
	 * @param c Back
	 * @param c Left
	 * @param c Right
	 */
	private void drawSmallCube(float[][] c) {
		glBegin(GL_QUADS);
		glColor3f(c[0][0],c[0][1],c[0][2]);             // Set The Color To Green
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
		
		glColor3f(c[1][0],c[1][1],c[1][2]);             // Set The Color To Orange
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
		
		glColor3f(c[2][0],c[2][1],c[2][2]);             // Set The Color To Red
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
		
		glColor3f(c[3][0],c[3][1],c[3][2]);             // Set The Color To Yellow
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
		
		glColor3f(c[4][0],c[4][1],c[4][2]);             // Set The Color To Blue
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
		
		glColor3f(c[5][0],c[5][1],c[5][2]);             // Set The Color To Violet
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
		glEnd();

	}

	/**
	 * Applies the corresponding rotation to the current matrix. 
	 * This assumes any cubes drawn on top of this matrix are part 
	 * of the face being rotated.
	 * 
	 */
	private void rotateFace(Face face) {
		switch (face) {
		case FRONT:
			glTranslatef(0.0f,0.0f,2.05f);
			glRotatef(rotation,0,0,1);
			glTranslatef(0.0f,0.0f,-2.05f);
		case BACK:
			glTranslatef(0.0f,0.0f,-2.05f);
			glRotatef(-rotation,0,0,1);
			glTranslatef(0.0f,0.0f,2.05f);
			
		case UP:
			glTranslatef(0.0f,2.05f,0.0f);
			glRotatef(rotation,0,1,0);
			glTranslatef(0.0f,-2.05f,0.0f);
			
		case DOWN:
			glTranslatef(0.0f,-2.05f,0.0f);
			glRotatef(rotation,0,1,0);
			glTranslatef(0.0f,2.05f,0.0f);
			
		case LEFT:
			glTranslatef(-2.05f,0.0f,0.0f);
			glRotatef(rotation,1,0,0);
			glTranslatef(2.05f,0.0f,0.0f);
			
		case RIGHT:
			glTranslatef(2.05f,0.0f,0.0f);
			glRotatef(rotation,1,0,0);
			glTranslatef(-2.05f,0.0f,0.0f);
		
		}	
	}

	public static void main(String[] argv) {
		Main starter = new Main();
		starter.start();
	}
}
