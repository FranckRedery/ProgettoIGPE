package application.view;

import java.awt.Image;
import java.util.ArrayList;

import application.model.Game;

public class CharacterAnimation {

	private ArrayList<Image> images;
	private int index;
	private Image currentImage;
	
	
	public CharacterAnimation(ArrayList<Image> images) {
		this.index = 0;
		this.images = images;
		this.currentImage = null;
		if(images.size() > 0)
			this.currentImage = images.get(0);
	}
	
	public boolean update() {
		
		if(Game.getInstance().isJumpRight()) {
			if(index < 5) {
				Game.getInstance().jumpRightUP();
			}
			else if(index > 5){
				Game.getInstance().jumpRightDOWN();
			}
		}
		else if(Game.getInstance().isJumpLeft()) {
			if(index < 5) {
				Game.getInstance().jumpLeftUP();
			}
			else if(index > 5){
				Game.getInstance().jumpLeftDOWN();
			}	
		}
		
		
		index++;
		if(index >= images.size()) { 
			Game.getInstance().setActionInProgress(false);
			Game.getInstance().setJumpRight(false);
			Game.getInstance().setJumpLeft(false);
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