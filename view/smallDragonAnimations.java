package application.view;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class smallDragonAnimations {

	public static final int WALK_LEFT = 1;
	public static final int WALK_RIGHT = 2;
	
	private HashMap<Integer, smallDragonAnimation> animations;
	private smallDragonAnimation currentAnimation;

	public smallDragonAnimations() {
		animations = new HashMap<Integer, smallDragonAnimation>();
		animations.put(WALK_LEFT, new smallDragonAnimation(getResources("enemy/smallDragon/leftWalk")));
		animations.put(WALK_RIGHT, new smallDragonAnimation(getResources("enemy/smallDragon/rightWalk")));
		currentAnimation = animations.get(WALK_RIGHT);
	}

	Image getCurrentImage() {		
		return currentAnimation.getCurrentImage();
	}
	
	
	void update() {
		if (currentAnimation == null) {
			return;
		}
		if(currentAnimation.update()) {
			return;
		}
		
	}

	public void setCurrentAnimation(boolean right) {
		if(right) {
			currentAnimation = animations.get(WALK_RIGHT);
		}
		else {
			currentAnimation = animations.get(WALK_LEFT);
		}
	}
	
	private ArrayList<Image> getResources(String name) {
		ArrayList<Image> images = new ArrayList<Image>();
		try {
			String path = "/application/resources/" + name + "/";
			File f = new File(getClass().getResource(path).getPath());
			ArrayList<File> listOfResources = new ArrayList<File>();
			for (File r : f.listFiles()) {
				listOfResources.add(r);				
			}

			Collections.sort(listOfResources, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (File img : listOfResources) {				
				images.add(ImageIO.read(getClass().getResourceAsStream(path + img.getName())));
			}
		} catch (Exception e) {
			System.err.println("Cannot load resources!");
		}
		return images;
	}
}
