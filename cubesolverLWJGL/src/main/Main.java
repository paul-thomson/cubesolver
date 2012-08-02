package main;
import static org.lwjgl.opengl.GL11.glEnable;
import gui.UserInterface;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import world.World;

public class Main {

	private static boolean closeRequested = false;
	public static Texture texture;
	static UserInterface userInterface;


	public static void main(String[] args)
	{
		World world = new World();
		userInterface = new UserInterface(world.getEventHandler());
		userInterface.setup();

		try {
			Display.setParent(userInterface.getCanvas());
			Display.setVSyncEnabled(true);
			
			Display.create();
						
			//texture stuff
			glEnable(GL11.GL_TEXTURE_2D);
			
			  try {
				  texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/testtexture.png"));
				  
				  System.out.println("Texture loaded: "+texture);
				  System.out.println(">> Image width: "+texture.getImageWidth());
				  System.out.println(">> Image height: "+texture.getImageHeight());
				  System.out.println(">> Texture width: "+texture.getTextureWidth());
				  System.out.println(">> Texture height: "+texture.getTextureHeight());
				  System.out.println(">> Texture ID: "+texture.getTextureID());
				  
				  
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}

			while(!Display.isCloseRequested() && !closeRequested) {
				
				world.render();
				Display.update();
			}

			Display.destroy();
			userInterface.dispose();
			System.exit(0);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void requestClose() {
		closeRequested = true;
	}
	
}