package application.model;

import java.awt.Rectangle;

import application.Settings;

public class MyCharacter {

	public  int x;
	public  int y;
	public  int speed; 
	public  int life;
	
	
	public MyCharacter() {
		x = 400;
		y = 800;
		speed = 10;
		life = 5;
	}
	
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x, y, 40, 40);
		return r;
	}
	
	public Rectangle attackRight() {
		Rectangle r = new Rectangle(x+30,y,40,40);
		return r;
	}
	
	public Rectangle attackLeft() {
		Rectangle r = new Rectangle(x-30,y,40,40);
		return r;
	}
}
