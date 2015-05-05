package game;

import static org.lwjgl.opengl.GL11.*;
import globalvars.GlobalVars;

import java.util.ArrayList;

public class Map {
	Player p;
	ArrayList<Pillar> al;

	public Map(Player p) {
		this.p = p;
		al = new ArrayList<Map.Pillar>();
		for (int i = 0; i < 7; i++) {
			al.add(new Pillar(500 + 200 * i, p));
		}
	}

	public void render() {
		for (Pillar i : al) {
			i.render();
		}
	}

	public void update() {
		for (Pillar i : al) {
			i.update();
		}
	}

	public class Pillar {
		private static final float R = .5f, G = .5f, B = .5f, WIDTH = 10,
				HEIGHT = 600, GAP = 75;

		private int height, px;
		private boolean passed = false;
		private Player p;

		public Pillar(int px, Player p) {
			height = GlobalVars.HEIGHT / 6
					+ (int) (Math.random() * GlobalVars.HEIGHT / 6 * 4);
			this.px = px;
			this.p = p;
		}

		public void render() {
			glColor3f(R, G, B);
			glBegin(GL_QUADS);
			glVertex2f(px - WIDTH, height - GAP - HEIGHT);
			glVertex2f(px + WIDTH, height - GAP - HEIGHT);
			glVertex2f(px + WIDTH, height - GAP);
			glVertex2f(px - WIDTH, height - GAP);
			glVertex2f(px - WIDTH, height + GAP + HEIGHT);
			glVertex2f(px + WIDTH, height + GAP + HEIGHT);
			glVertex2f(px + WIDTH, height + GAP);
			glVertex2f(px - WIDTH, height + GAP);
			glEnd();
		}

		public void update() {
			px -= 1;
			if (px <= p.getPx()) {
				if (!passed) {
					Game.incrementScore();
				}
				passed = true;
			}
			if (px <= -100) {
				px = 1300;
				passed = false;
			}
			if (Math.abs(p.getPx() - px) <= Player.SIZE + WIDTH) {
				if (Math.abs(p.getPy() - height) > GAP - Player.SIZE) {
					Game.setMode(1);
				}
			}
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
	}

}
