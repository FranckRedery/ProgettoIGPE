package application.model;

import java.awt.Rectangle;

public class Dragon {
	
	public  int x;
	public  int y = 700;
	public  int speed = 2; 
	public  int life = 5;
	public  boolean isRight = true;
	public  boolean run = false;
	public  boolean invulnerability = false;
	
	public Dragon(int x) {
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r;
		if(isRight) {
			r = new Rectangle(x+100,y+130,80,47);
			return r;
		}
		r = new Rectangle(x+65, y+130, 80, 47);
		return r;
	}
}
