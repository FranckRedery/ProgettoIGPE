package application.model;

import java.awt.Rectangle;

import application.Settings;

public class MyCharacter {

	public  int x;
	public  int y;
	public  int speed; 
	public  int life;
	
	
	public MyCharacter() {
		x = 100;
		y = 800;
		speed = 5;
		life = 5;
	}
	
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x, y, 40, 80);
		return r;
	}
	
}
