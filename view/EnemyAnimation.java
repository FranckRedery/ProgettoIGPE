package application.view;

import java.awt.Image;
import java.util.ArrayList;

import application.model.Game;

public class EnemyAnimation {

		private ArrayList<Image> images;
		private int index;
		private Image currentImage;
		
		
		public EnemyAnimation(ArrayList<Image> images) {
			this.index = 0;
			this.images = images;
			this.currentImage = null;
			
			if(images.size() > 0)
				this.currentImage = images.get(0);
		}
		
		public boolean update() {
			if(!Game.getInstance().isPause() && !Game.getInstance().gameOver()) {
				index++;
			}
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
