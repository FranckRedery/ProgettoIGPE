package application.model;

import java.awt.Rectangle;

public class SmallDragon {

	
	public  int x;
	public  int y = 821;
	public  int speed = 2; 
	public  int life = 1;
	public  boolean isRight;
	
	public SmallDragon(boolean right,int x) {
		this.isRight = right;
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x+25, y+40, 50, 20);
		return r;
	}
	
}
