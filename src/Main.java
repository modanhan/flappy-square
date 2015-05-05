import game.Game;
import globalvars.GlobalVars;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Main {

	static void init() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(GlobalVars.WIDTH,
				GlobalVars.HEIGHT));

		Display.create();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GlobalVars.WIDTH, 0, GlobalVars.HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		Game.init();
	}

	static void update() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		Game.update();
		
		Display.sync(60);
		Display.update();
	}

	public static void main(String[] args) {
		try {
			init();
			while (!Display.isCloseRequested()) {
				update();
			}
			Display.destroy();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
