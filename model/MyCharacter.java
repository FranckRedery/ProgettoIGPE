package application.model;

import java.awt.Rectangle;

public class MyCharacter {

	public  int x;
	public  int y;
	public  int speed; 
	public  int life;
	public boolean invulnerability = false;
	public boolean right = true;
	public boolean JumpRight = false;
	public boolean JumpLeft = false;
	public boolean JumpUP = false;
	public boolean actionInProgress = false;

	public MyCharacter() {
		x = 400;
		y = 800;
		speed = 10;
		life = 5;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x+37, y+23, 20, 55);
		return r;
	}
	
	public Rectangle attackRight() {
		Rectangle r = new Rectangle(x+40,y+25,65,60);
		return r;
	}
	public Rectangle attackLeft() {
		Rectangle r = new Rectangle(x-5,y+25,65,60);
		return r;
	}
}
