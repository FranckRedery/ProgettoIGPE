package application.model;

import java.awt.Rectangle;

public class fireAttack {

	public int x;
	public int y = 810;
	public boolean isRight;
	public int speed = 7;
	
	
	public fireAttack(int x, boolean right) {
		this.isRight = right;
		if(isRight) {
			this.x = x+10;
		}
		else {
			this.x = x-10;
		}
	}
	
	
	public  Rectangle getRectangle() {
		Rectangle r;
		if(isRight) {
			r = new Rectangle(x+45,y+50,30,20);
			return r;
		}
		r = new Rectangle(x+30, y+50, 30, 20);
		return r;
	}
	
}
