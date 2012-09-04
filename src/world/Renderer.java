package world;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LEQUAL;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import gui.UserInterface;
import main.Main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;

import cube.Cubie;
import cube.Face;
import cube.RCube;
import cube.Turn;


/**
 * The class used for rendering the {@link World} and the GUI.
 */
public class Renderer {
		
	
	/**
	 * Change OpenGL into a suitable mode for drawing 2D images 
	 * (like GUI).
	 */
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

	/**
	 * Change OpenGL into a mode suitable for drawing 3D images (the rubik's cube).
	 */
	public void init3D() {
		glClearDepth(1.0); // Depth Buffer Setup
		glEnable(GL_DEPTH_TEST); // Enables Depth Testing
		glDisable(GL_BLEND);
		
		glDepthFunc(GL_LEQUAL);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); 

//		DisplayMode displayMode = Display.getDisplayMode();
		GLU.gluPerspective(
				45.0f,
				(float) UserInterface.pane.getWidth() / (float) UserInterface.pane.getHeight(),
				0.1f,
				100.0f);
		glMatrixMode(GL_MODELVIEW);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		//TODO texture stuff
		Color.white.bind();
		Main.texture.bind();
		
	}

	/**
	 * Set up the 3D world for drawing the cube
	 * @param cube
	 */
	public void prepare3D(RCube cube) {
		// Clear The Screen And The Depth Buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		
		float[] position = cube.getPosition();
		float POS_X = position[0], POS_Y = position[1], POS_Z = position[2];
		float[] rotation = cube.getRotation();
		float ROT_X = rotation[0], ROT_Y = rotation[1];// ROT_Z = rotation[2];
		
		glLoadIdentity();
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
		float[] position = cubie.getPosition().toArray();
		float[][] faceColours = cubie.getFaceColours();
		
		glPushMatrix();
		if (turn != null) {
			rotateFace(turn);
		}
			glTranslatef(position[0],position[1],position[2]);
			if (cubie.isHighlighted()) {
				Main.textureHighlight.bind();
			}
			drawSmallCube(faceColours);
			Main.texture.bind();
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
//		glBegin(GL_QUADS);
//		glColor3f(c[0][0],c[0][1],c[0][2]);             // Set The Color To Green
//		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
//		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
//		glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
//		glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
//		
//		glColor3f(c[1][0],c[1][1],c[1][2]);             // Set The Color To Orange
//		glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
//		glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
//		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
//		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
//		
//		glColor3f(c[2][0],c[2][1],c[2][2]);             // Set The Color To Red
//		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
//		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
//		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
//		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
//		
//		glColor3f(c[3][0],c[3][1],c[3][2]);             // Set The Color To Yellow
//		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
//		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
//		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
//		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
//		
//		glColor3f(c[4][0],c[4][1],c[4][2]);             // Set The Color To Blue
//		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
//		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
//		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
//		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
//		
//		glColor3f(c[5][0],c[5][1],c[5][2]);             // Set The Color To Violet
//		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
//		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
//		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
//		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
//		glEnd();

		glBegin(GL_QUADS);
		glColor3f(c[0][0],c[0][1],c[0][2]);             // Set The Color To Green
		GL11.glTexCoord2f(1,0);
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
		GL11.glTexCoord2f(0,0);
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
		GL11.glTexCoord2f(0,1);
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
		GL11.glTexCoord2f(1,1);
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
		
		glColor3f(c[1][0],c[1][1],c[1][2]);             // Set The Color To Orange
		GL11.glTexCoord2f(1,0);
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
		GL11.glTexCoord2f(0,0);
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
		GL11.glTexCoord2f(0,1);
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
		GL11.glTexCoord2f(1,1);
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
		
		glColor3f(c[2][0],c[2][1],c[2][2]);             // Set The Color To Red
		GL11.glTexCoord2f(1,0);
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
		GL11.glTexCoord2f(0,0);
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
		GL11.glTexCoord2f(0,1);
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
		GL11.glTexCoord2f(1,1);
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
		
		glColor3f(c[3][0],c[3][1],c[3][2]);             // Set The Color To Yellow
		GL11.glTexCoord2f(0,1);
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
		GL11.glTexCoord2f(1,1);
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
		GL11.glTexCoord2f(1,0);
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
		GL11.glTexCoord2f(0,0);
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
		
		glColor3f(c[4][0],c[4][1],c[4][2]);             // Set The Color To Blue
		GL11.glTexCoord2f(1,0);
		glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
		GL11.glTexCoord2f(0,0);
		glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
		GL11.glTexCoord2f(0,1);
		glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
		GL11.glTexCoord2f(1,1);
		glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
		
		glColor3f(c[5][0],c[5][1],c[5][2]);             // Set The Color To Violet
		GL11.glTexCoord2f(1,0);
		glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
		GL11.glTexCoord2f(0,0);
		glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
		GL11.glTexCoord2f(0,1);
		glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
		GL11.glTexCoord2f(1,1);
		glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
		glEnd();

		
	}

	/**
	 * Applies the corresponding rotation to the current matrix. 
	 * This assumes any cubes drawn on top of this matrix are part 
	 * of the face being rotated.
	 */
	private void rotateFace(Turn turn) {
		Face face = turn.getTurningFace();
		float rotation = turn.getRotationAngle();
		boolean inverse = turn.isInverseTurn();
		float angle;
		switch (face) {
		case Z:
		case S:
		case FRONT2:
		case FRONT:
			angle = inverse ? rotation : -rotation;
			glTranslatef(0.0f,0.0f,2.05f);
			glRotatef(angle,0,0,1);
			glTranslatef(0.0f,0.0f,-2.05f);
			break;
		case BACK2:
		case BACK:
			angle = inverse ? -rotation : rotation;
			glTranslatef(0.0f,0.0f,-2.05f);
			glRotatef(angle,0,0,1);
			glTranslatef(0.0f,0.0f,2.05f);
			break;
		case Y:
		case UP2:
		case UP:
			angle = inverse ? rotation : -rotation;
			glTranslatef(0.0f,2.05f,0.0f);
			glRotatef(angle,0,1,0);
			glTranslatef(0.0f,-2.05f,0.0f);
			break;
		case E:
		case DOWN2:
		case DOWN:
			angle = inverse ? -rotation : rotation;
			glTranslatef(0.0f,2.05f,0.0f);
			glRotatef(angle,0,1,0);
			glTranslatef(0.0f,-2.05f,0.0f);
			break;
		case MIDDLE:
		case LEFT2:
		case LEFT:
			angle = inverse ? -rotation : rotation;
			glTranslatef(-2.05f,0.0f,0.0f);
			glRotatef(angle,1,0,0);
			glTranslatef(2.05f,0.0f,0.0f);
			break;
		case X:
		case RIGHT2:
		case RIGHT:
			angle = inverse ? rotation : -rotation;
			glTranslatef(2.05f,0.0f,0.0f);
			glRotatef(angle,1,0,0);
			glTranslatef(-2.05f,0.0f,0.0f);
			break;
		default:
			System.err.println("PROBLEM with rotateFace in Renderer");
		}	
	}
}
