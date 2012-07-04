package world;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import cube.Cubie;
import cube.Face;
import cube.RCube;
import cube.Turn;

public class Renderer {
	
	UnicodeFont font;
	
	//TODO move into constructor


	/** time at last frame */
	long lastFrame;

	/** frames per second */
	int fps;
	/** last fps time */
	private long lastFPS;
	
	
	/**
	 * Load all the textures for the font at startup so it is not done on the fly (which is slow) 
	 */
	public void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new UnicodeFont(awtFont);
		font.addAsciiGlyphs();   // Add Glyphs
		font.addGlyphs(400, 600); // Add Glyphs
		font.getEffects().add(new ColorEffect(java.awt.Color.WHITE)); // Add Effects
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}
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
		// TODO MOVE INTO UTILS OR SOMETHING
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - getLastFPS() > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			setLastFPS(getLastFPS() + 1000);
		}
		fps++;
	}
	
	public void init2D() {
		
		glShadeModel(GL_SMOOTH);       
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);                   

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);               
		glClearDepth(1);                                      

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
		DisplayMode displayMode = Display.getDisplayMode();
	    glOrtho(0.0f, (float) displayMode.getWidth(), (float) displayMode.getHeight(), 0.0f, 1.0f, -1.0f);

	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();

	}

	public void init3D() {
		glClearDepth(1.0); // Depth Buffer Setup
		glEnable(GL_DEPTH_TEST); // Enables Depth Testing
		glDisable(GL_BLEND);
		
		glDepthFunc(GL_LEQUAL);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); 

		DisplayMode displayMode = Display.getDisplayMode();
		GLU.gluPerspective(
				45.0f,
				(float) displayMode.getWidth() / (float) displayMode.getHeight(),
				0.1f,
				100.0f);
		glMatrixMode(GL_MODELVIEW);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}
	
	public void render2D() {
		
		Color.white.bind();
		
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
		
	}

	public void render3D(RCube cube) {
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		
		float[] position = cube.getPosition();
		float POS_X = position[0], POS_Y = position[1], POS_Z = position[2];
		float[] rotation = cube.getRotation();
		float ROT_X = rotation[0], ROT_Y = rotation[1], ROT_Z = rotation[2];
		
		glLoadIdentity();                   // Reset The Current Modelview Matrix
		glTranslatef(POS_X,POS_Y,POS_Z);
		glRotatef(ROT_X, 1, 0, 0);
		glRotatef(ROT_Y, 0, 1, 0);
		
	}

	
	
	/**
	 * Draws a cube from the cubie object. Essentially converting from the Java view 
	 * of an object into the OpenGL view.
	 * @param cubie
	 */
	void drawCubie(Cubie cubie, Turn turn) {
		float[] position = cubie.getPosition();
		float[][] faceColours = cubie.getFaceColours();
		
		glPushMatrix();
		if (turn != null) {
			rotateFace(turn);
		}
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
	private void rotateFace(Turn turn) {
		Face face = turn.getTurningFace();
		float rotation = turn.getRotationAngle();
		boolean inverse = turn.isInverseTurn();
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

	public long getLastFPS() {
		return lastFPS;
	}

	public void setLastFPS(long lastFPS) {
		this.lastFPS = lastFPS;
	}

}
