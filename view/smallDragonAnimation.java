package application.view;

import java.awt.Image;
import java.util.ArrayList;

import application.model.Game;

public class smallDragonAnimation {

	private ArrayList<Image> images;
	private int index;
	private Image currentImage;
	
	
	public smallDragonAnimation(ArrayList<Image> images) {
		this.index = 0;
		this.images = images;
		this.currentImage = null;
		
		if(images.size() > 0)
			this.currentImage = images.get(0);
	}
	
	public boolean update() {
		
		index++;
		if(index >= images.size()) { 
			index = 0;
			return false;
		}
		this.currentImage = images.get(index);
		return true;
	}
	
	public Image getCurrentImage() {
		return currentImage;
	}
}
