package application.model;

import java.awt.Rectangle;

import application.Settings;
import application.view.myCharacterAnimations;

public class Game {

	private MyCharacter myCharacter;
	private static Game game = null;// CON QUESTA TECNICA SI DICE CHE LA CLASSE GAME è SINGLETON CIOè UNICA !
	private boolean JumpRight = false;
	private boolean JumpLeft = false;
	private boolean actionInProgress = false;
	
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
		myCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed >= 0)) {
			myCharacter.x -= myCharacter.speed;
		}
	}
	
	
	public void moveRight() {
		myCharacterAnimations.isRight = true;
		if((myCharacter.x + myCharacter.speed <= Settings.WINDOW_SIZE - Settings.BLOCK_DIM)) {
			myCharacter.x += myCharacter.speed;
		}
	}
	
	public void jumpRightUP() {
		myCharacterAnimations.isRight = true;
		if((myCharacter.x + myCharacter.speed*2 <= Settings.WINDOW_SIZE - Settings.BLOCK_DIM)) {
			myCharacter.x += myCharacter.speed*2;
		}
		myCharacter.y -= myCharacter.speed*4;
	}
	
	public void jumpRightDOWN() {
		myCharacterAnimations.isRight = true;
		if((myCharacter.x + myCharacter.speed*2 <= Settings.WINDOW_SIZE - Settings.BLOCK_DIM)) {
			myCharacter.x += myCharacter.speed*2;
		}
		myCharacter.y += myCharacter.speed*4;
	}
	
	public void jumpLeftUP() {
		myCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed*2 >= 0)) {
			myCharacter.x -= myCharacter.speed*2;
		}
		myCharacter.y -= myCharacter.speed*4;
	}
	
	public void jumpLeftDOWN() {
		myCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed*2 >= 0)) {
			myCharacter.x -= myCharacter.speed*2;
		}
		myCharacter.y += myCharacter.speed*4;
	}
	
	public MyCharacter getCharacter() {
		return myCharacter;
	}
	
	public void attaccato() {
		Rectangle rettangolo = myCharacter.getRectangle();
		if(rettangolo.intersects(rettangolo)) {
			myCharacter.life--;
		}
	}

	public boolean isActionInProgress() {
		return actionInProgress;
	}

	public void setActionInProgress(boolean inProgress) {
		this.actionInProgress = inProgress;
	}

	public boolean isJumpLeft() {
		return JumpLeft;
	}

	public void setJumpLeft(boolean jumpLeft) {
		JumpLeft = jumpLeft;
	}

	public boolean isJumpRight() {
		return JumpRight;
	}

	public void setJumpRight(boolean jumpRight) {
		JumpRight = jumpRight;
	}
}
