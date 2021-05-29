package application.model;

import java.awt.Rectangle;

public class lizard {

	public  int x;
	public  int y = 762;
	public  int speed = 3; 
	public  int life = 1;
	public  boolean isRight;
	
	public lizard(boolean right, int x) {
		this.isRight = right;
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r;
		if(isRight) {
			r = new Rectangle(x+70,y+80,40,40);
			return r;
		}
		r = new Rectangle(x+90, y+80, 40, 40);
		return r;
	}

}