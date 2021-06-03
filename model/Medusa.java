package application.model;

import java.awt.Rectangle;

public class Medusa {
	public  int x;
	public  int y = 807;
	public  int speed = 6; 
	public  int life = 1;
	public  boolean isRight;
	
	public Medusa(boolean right, int x) {
		this.isRight = right;
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r;
		if(isRight) {
			r = new Rectangle(x+25,y+30,38,47);
			return r;
		}
		r = new Rectangle(x+35, y+30, 38, 47);
		return r;
	}
}
