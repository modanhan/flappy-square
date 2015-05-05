package game;

import org.lwjgl.input.Keyboard;

import globalvars.GlobalVars;
import static org.lwjgl.opengl.GL11.*;

public class Player {
	public static final int SIZE = 15;
	private static final float R = .9f, G = .9f, B = .9f, GRAVITY = -.25f;

	private float px, py, speed;

	public Player() {
		setSpeed(0);
		setPx(120);
		setPy(GlobalVars.HEIGHT / 2);
	}

	public void render() {
		glColor3f(R, G, B);
		glBegin(GL_QUADS);
		glVertex2f(px - SIZE, py - SIZE);
		glVertex2f(px - SIZE, py + SIZE);
		glVertex2f(px + SIZE, py + SIZE);
		glVertex2f(px + SIZE, py - SIZE);
		glEnd();
	}

	public void update() {
		speed += GRAVITY;
		py += speed;
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					speed+=10;
					if(speed>=10){
						speed=10;
					}
				}
			}
		}
		if(speed<=-100){
			speed=-100;
		}
		
		if(py>=GlobalVars.HEIGHT+120||py<=-120){
			Game.setMode(1);
		}
	}

	public float getPx() {
		return px;
	}

	public void setPx(float px) {
		this.px = px;
	}

	public float getPy() {
		return py;
	}

	public void setPy(float py) {
		this.py = py;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
