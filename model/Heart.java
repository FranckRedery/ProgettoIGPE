package application.model;

import java.awt.Rectangle;

public class Heart {
	
	public  int x;
	public  int y = 0;
	
	public Heart(int x) {
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x, y, 20, 20);
		return r;
	}
}
