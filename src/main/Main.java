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
	public static Texture textureHighlight;
	static UserInterface userInterface;


	public static void main(String[] args)
	{

		World world = new World();
		userInterface = new UserInterface();
		userInterface.setup();

		God.setWorld(world);
		God.setCube(world.getCube());
		God.setInterface(userInterface);

		try {
			Display.setParent(userInterface.getCanvas());
			Display.setVSyncEnabled(true);
			Display.create();

			//texture stuff
			glEnable(GL11.GL_TEXTURE_2D);

			try {
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/testtexture.png"));
				textureHighlight = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/testtexturehighlight.png"));

				//				  System.out.println(">> Image width: "+texture.getImageWidth());
				//				  System.out.println(">> Image height: "+texture.getImageHeight());
				//				  System.out.println(">> Texture width: "+texture.getTextureWidth());
				//				  System.out.println(">> Texture height: "+texture.getTextureHeight());


			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}

			while(!Display.isCloseRequested() & !closeRequested) {
				world.render();
				Display.update();

			}
			Display.destroy();
			userInterface.dispose();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	public static void requestClose() {
		closeRequested = true;
	}

}