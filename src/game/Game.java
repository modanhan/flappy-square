package game;

import globalvars.GlobalVars;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Game {
	private static Player p;
	private static Map m;
	private static int mode = 0;
	private static int score;

	public static void init() {
		Display.setTitle(GlobalVars.TITLE);
		score=0;
		p = new Player();
		m = new Map(p);
	}

	public static void update() {
		if (mode == 0) {
			Display.setTitle(GlobalVars.TITLE+" Score: "+score);
			p.update();
			m.update();
			p.render();
			m.render();
		} else if (mode == 1) {
			Display.setTitle(GlobalVars.GAMEOVER+" Score: "+score);
			while (Keyboard.next()) {
				if (Keyboard.getEventKeyState()) {
					if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
						init();
						mode=0;
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
						System.exit(0);
					}
				}
			}
		}
	}

	public static void incrementScore(){
		score++;
	}
	
	public static void setMode(int mode) {
		Game.mode=mode;
	}
}
