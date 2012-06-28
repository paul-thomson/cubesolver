import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.util.glu.GLU;
import static org.lwjgl.opengl.GL11.*; // static import means it does not need to be explicitly referenced


public class Main {

	/** position of quad */
	float POS_X = 0, POS_Y = 0, POS_Z = -2;
	
	float ROT_X = 20;
	float ROT_Y = 35;

	/** time at last frame */
	long lastFrame;

	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	/** The face which is currently turning */
	Face turningFace = Face.NONE;
	/** If the face is turning inverseTurn */
	boolean inverseTurn = false;
	/** angle of face rotation */
	float rotation = 0;
	
	RCube cube = null;

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
		if (rotation != 0) {
			rotation += 0.15f * delta;
		}

//		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) POS_X -= 0.01f * delta;
//		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) POS_X += 0.01f * delta;
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) POS_Y += 0.01f * delta;
//		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) POS_Y -= 0.01f * delta;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) ROT_X -= 0.05f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) ROT_X += 0.05f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) ROT_Y += 0.05f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) ROT_Y -= 0.05f * delta;
		
		if (turningFace == Face.NONE) { 
			if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
				rotation += 0.15f * delta;
				turningFace = Face.UP;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				rotation += 0.15f * delta;
				turningFace = Face.DOWN;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				rotation += 0.15f * delta;
				turningFace = Face.FRONT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
				rotation += 0.15f * delta;
				turningFace = Face.BACK;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
				rotation += 0.15f * delta;
				turningFace = Face.LEFT;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				rotation += 0.15f * delta;
				turningFace = Face.RIGHT;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				inverseTurn = true;
			} else {
				inverseTurn = false;
			}
		}

		// reset
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			POS_X = 0;
			POS_Y = 0;
			ROT_X = 20;
			ROT_Y = 35;
			rotation = 0;
			turningFace = Face.NONE;
			inverseTurn = false;
			cube = null;
		}

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
		if (cube == null) {
			cube = new RCube(2.0f,0.05f);
		}
		drawCube(cube);
			
		glPopMatrix();
	}

	/**
	 * Create the internal representation of the rubik's cube
	 */
	private void drawCube(RCube cube) {
		ArrayList<Cubie> cubies = cube.getCubies();
		boolean stop = false;
		if (rotation >= 90) {
			stop = true;
		}
		for (Cubie cubie : cubies) {
			if (cubie.isOnFace(turningFace)) {
				if (stop) {
					cubie.rotateCubieOnFace(turningFace, inverseTurn);
					drawCubie(cubie,Face.NONE);
				} else {
					drawCubie(cubie, turningFace);
				}
			} else {
				drawCubie(cubie,Face.NONE);
			}
		}
		if (stop) {
			rotation = 0;
			turningFace = Face.NONE;
			stop = false;
		}
	}
	
	/**
	 * Draws a cube from the cubie object. Essentially converting from the Java view 
	 * of an object into the OpenGL view.
	 * @param cubie
	 */
	private void drawCubie(Cubie cubie, Face face) {
		float[] position = cubie.getPosition();
		float[][] faceColours = cubie.getFaceColours();
		
		glPushMatrix();
		rotateFace(face, inverseTurn);
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
	private void rotateFace(Face face, boolean inverse) {
		float angle;
		switch (face) {
		case FRONT:
			angle = inverse ? rotation : -rotation;
			glTranslatef(0.0f,0.0f,2.05f);
			glRotatef(angle,0,0,1);
			glTranslatef(0.0f,0.0f,-2.05f);
			break;
			
		case BACK:
			angle = inverse ? -rotation : rotation;
			glTranslatef(0.0f,0.0f,-2.05f);
			glRotatef(angle,0,0,1);
			glTranslatef(0.0f,0.0f,2.05f);
			break;
			
		case UP:
			angle = inverse ? rotation : -rotation;
			glTranslatef(0.0f,2.05f,0.0f);
			glRotatef(angle,0,1,0);
			glTranslatef(0.0f,-2.05f,0.0f);
			break;
			
		case DOWN:
			angle = inverse ? rotation : -rotation;
			glTranslatef(0.0f,-2.05f,0.0f);
			glRotatef(angle,0,1,0);
			glTranslatef(0.0f,2.05f,0.0f);
			break;
			
		case LEFT:
			angle = inverse ? -rotation : rotation;
			glTranslatef(-2.05f,0.0f,0.0f);
			glRotatef(angle,1,0,0);
			glTranslatef(2.05f,0.0f,0.0f);
			break;
			
		case RIGHT:
			angle = inverse ? rotation : -rotation;
			glTranslatef(2.05f,0.0f,0.0f);
			glRotatef(angle,1,0,0);
			glTranslatef(-2.05f,0.0f,0.0f);
			break;
		
		}	
	}

	public static void main(String[] argv) {
		Main starter = new Main();
		starter.start();
	}
}
