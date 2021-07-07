package application.view;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class MyCharacterAnimations {

	
	public static final int WALK_LEFT = 1;
	public static final int WALK_RIGHT = 2;
	public static final int ATTACK_LEFT = 3;
	public static final int ATTACK_RIGHT = 4;
	public static final int IDLE_LEFT = 5;
	public static final int IDLE_RIGHT = 6;
	public static final int JUMP_LEFT = 7;
	public static final int JUMP_RIGHT = 8;
	public static boolean isRight = true;
	
	private HashMap<Integer, CharacterAnimation> animations;
	private CharacterAnimation currentAnimation;

	MyCharacterAnimations() {
		animations = new HashMap<Integer, CharacterAnimation>();
		animations.put(WALK_LEFT, new CharacterAnimation(getResources("myCharacterLeft/Walking")));
		animations.put(WALK_RIGHT, new CharacterAnimation(getResources("myCharacterRight/Walking")));
		animations.put(ATTACK_LEFT, new CharacterAnimation(getResources("myCharacterLeft/attack")));
		animations.put(ATTACK_RIGHT, new CharacterAnimation(getResources("myCharacterRight/attack")));
		animations.put(IDLE_LEFT, new CharacterAnimation(getResources("myCharacterLeft/Idle")));
		animations.put(IDLE_RIGHT, new CharacterAnimation(getResources("myCharacterRight/Idle")));
		animations.put(JUMP_LEFT, new CharacterAnimation(getResources("myCharacterLeft/jump")));
		animations.put(JUMP_RIGHT, new CharacterAnimation(getResources("myCharacterRight/jump")));
		currentAnimation = animations.get(IDLE_RIGHT);		
	}

	Image getCurrentImage() {		
		return currentAnimation.getCurrentImage();
	}
	
	void changeAnimation(int type) {
		if(!animations.containsKey(type) || (currentAnimation != animations.get(IDLE_RIGHT) && currentAnimation != animations.get(IDLE_LEFT))) {
			return;
		}
		currentAnimation = animations.get(type);
	}

	void update() {
		

		if (currentAnimation == null) {
			return;
		}
		
		if(currentAnimation.update()) {
			return;
		}
		else if(!isRight) {
			currentAnimation = animations.get(IDLE_LEFT);
			return;
		}
		else if(isRight) {
			currentAnimation = animations.get(IDLE_RIGHT);
			return;
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