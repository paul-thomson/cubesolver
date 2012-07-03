/**		float[] lightPosition = new float[]{1,1,0,1};
		float[] lightAmbient = new float[]{0,0,0,1};
		float[] lightDiffuse = new float[]{1,1,1,1};
		float[] lightSpecular = new float[]{1,1,1,1};
		
		ByteBuffer temp = ByteBuffer.allocateDirect(16);
		temp.order(ByteOrder.nativeOrder());
		glLight(GL_LIGHT1, GL_AMBIENT, (FloatBuffer)temp.asFloatBuffer().put(lightAmbient).flip());              
		glLight(GL_LIGHT1, GL_DIFFUSE, (FloatBuffer)temp.asFloatBuffer().put(lightDiffuse).flip());            
		glLight(GL_LIGHT1, GL_POSITION,(FloatBuffer)temp.asFloatBuffer().put(lightPosition).flip());  
		glLight(GL_LIGHT1, GL_SPECULAR,(FloatBuffer)temp.asFloatBuffer().put(lightSpecular).flip());
		glEnable(GL_LIGHT1);  
		glEnable(GL_LIGHTING);
		
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_AMBIENT_AND_DIFFUSE, GL_EMISSION);
		
		float[] materialSpecular = new float[]{1,1,1,1};
		float[] materialEmission = new float[]{0,0,0,1};
		
		glMaterial(GL_SPECULAR, GL_COLOR, (FloatBuffer)temp.asFloatBuffer().put(materialSpecular).flip());
		glMaterial(GL_EMISSION, GL_COLOR, (FloatBuffer)temp.asFloatBuffer().put(materialEmission).flip());
TODO REMOVE THIS COMMENT */

import java.awt.Font;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import world.Renderer;
import world.World;

import cube.Cubie;
import cube.Face;
import cube.RCube;

import static org.lwjgl.opengl.GL11.*; // static import means it does not need to be explicitly referenced


public class Main {
	
	

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		World world = new World();
		
		Renderer renderer = new Renderer();
		
		renderer.initFont();
		renderer.getDelta(); // call once before loop to initialise lastFrame
		renderer.setLastFPS(renderer.getTime()); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = renderer.getDelta();

			renderer.update(delta);
			renderer.init3D(); // init OpenGL
			renderer.render3D();
			renderer.init2D();
			renderer.render2D();
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}

		Display.destroy();
	}

	

	public static void main(String[] argv) {
		Main starter = new Main();
		starter.start();
	}
}
