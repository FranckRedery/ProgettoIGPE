package application.model;

import java.awt.Rectangle;

public class smallDragon {

	
	public  int x;
	public  int y = 820;
	public  int speed = 1; 
	public  int life = 1;
	public  boolean isRight;
	
	public smallDragon(boolean right,int x) {
		this.isRight = right;
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x+25, y+40, 50, 20);
		return r;
	}
	
}
