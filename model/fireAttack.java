package application.model;

import java.awt.Rectangle;

public class fireAttack {

	public int x;
	public int y = 810;
	public boolean isRight;
	public int speed = 5;
	
	
	public fireAttack(int x, boolean right) {
		this.isRight = right;
		this.x = x;
	}
	
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x, y, 25, 20);
		return r;
	}
	
}
