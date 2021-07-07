package application.view;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.imageio.ImageIO;

import application.model.Game;

public class DragonAnimations {
	
	public static final int WALK_LEFT = 1;
	public static final int WALK_RIGHT = 2;
	public static final int DEATH_LEFT = 3;
	public static final int DEATH_RIGHT = 4;
	
	private HashMap<Integer, DragonAnimation> animations;
	private DragonAnimation currentAnimation;

	public DragonAnimations(boolean right) {
		animations = new HashMap<Integer, DragonAnimation>();
		animations.put(WALK_LEFT, new DragonAnimation(getResources("enemy/Dragon/leftWalk")));
		animations.put(WALK_RIGHT, new DragonAnimation(getResources("enemy/Dragon/rightWalk")));
		animations.put(DEATH_LEFT, new DragonAnimation(getResources("enemy/Dragon/LeftDeath")));
		animations.put(DEATH_RIGHT, new DragonAnimation(getResources("enemy/Dragon/RightDeath")));
		setCurrentAnimation(right);
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

	// in base alla boolean con cui viene inizializzato si setta l'animazione , cammina dx oppure sx , morte dx oppure sx
	public void setCurrentAnimation(boolean right) {
		
		if(Game.getInstance().getDragon().life == 0 && right) {
			currentAnimation = animations.get(DEATH_RIGHT);
		}
		else if(Game.getInstance().getDragon().life == 0 && !right) {
			currentAnimation = animations.get(DEATH_LEFT);
		}
		
		if(Game.getInstance().getDragon().life > 0 && right) {
			currentAnimation = animations.get(WALK_RIGHT);
		}
		else if(Game.getInstance().getDragon().life > 0 && !right) {
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
