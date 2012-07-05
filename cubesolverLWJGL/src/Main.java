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

import java.io.IOException;

import gui.UserInterface;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

import world.World;

public class Main {
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		try {
			LWJGLRenderer renderer = new LWJGLRenderer();
			UserInterface userInterface = new UserInterface();
			GUI gui = new GUI(userInterface, renderer);

			ThemeManager theme = ThemeManager.createThemeManager(
					UserInterface.class.getResource("chutzpah.xml"), renderer);

			gui.applyTheme(theme);


			World world = new World();
			world.initialiseRendering();

			while (!Display.isCloseRequested()) {
				
				world.render();
				gui.update();

				Display.update();
				
				Display.sync(60); // cap fps to 60fps
				
			}

			gui.destroy();
			theme.destroy();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Display.destroy();
	}

	public static void main(String[] argv) {
		Main starter = new Main();
		starter.start();
	}
}
