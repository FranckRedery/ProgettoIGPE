package application.model;

import java.awt.Rectangle;

import application.Settings;
import application.view.myCharacterAnimations;

public class Game {

	private MyCharacter myCharacter;
	private static Game game = null;// CON QUESTA TECNICA SI DICE CHE LA CLASSE GAME è SINGLETON CIOè UNICA !
	
	private Game() {
		myCharacter = new MyCharacter();
		
	}
	
	public static Game getInstance() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}
	
	public void creamappa() {
		
	}
	
	
	public void moveLeft() {
		if((myCharacter.x - myCharacter.speed >= 0)) {
			myCharacterAnimations.isRight = false;
			myCharacter.x -= myCharacter.speed;
		}
	}
	
	
	public void moveRight() {
		if((myCharacter.x + myCharacter.speed <= Settings.WINDOW_SIZE - Settings.BLOCK_DIM)) {
			myCharacterAnimations.isRight = true;
			myCharacter.x += myCharacter.speed;
		}
	}
	
	public void jumpRight() {
		
	}
	
	public void jumpLeft() {}
	
	public MyCharacter getCharacter() {
		return myCharacter;
	}
	
	public void attaccato() {
		Rectangle rettangolo = myCharacter.getRectangle();
		if(rettangolo.intersects(rettangolo)) {
			myCharacter.life--;
		}
	}
}
