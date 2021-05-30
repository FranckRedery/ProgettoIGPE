package application.model;

import java.awt.Rectangle;

public class heart {
	
	public  int x;
	public  int y = 863;
	
	public heart(int x) {
		this.x = x;
	}
	
	public  Rectangle getRectangle() {
		Rectangle r = new Rectangle(x, y, 20, 20);
		return r;
	}
}
