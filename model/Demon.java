package application.model;

import java.awt.Rectangle;

public class Demon {

	public  int x;
	public  int y = 739;
	public  int speed = 4; 
	public  int life = 1;
	public  boolean isRight;
	
	public Demon(boolean right, int x) {
		this.isRight = right;
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r;
		if(isRight) {
			r = new Rectangle(x+60,y+80,35,60);
			return r;
		}
		r = new Rectangle(x+105, y+80, 35, 60);
		return r;
	}
}
