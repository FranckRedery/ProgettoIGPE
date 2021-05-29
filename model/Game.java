package application.model;

import java.awt.Rectangle;
import java.util.ArrayList;

import application.Settings;
import application.view.GraphicPanel;
import application.view.myCharacterAnimations;
import application.view.smallDragonAnimations;
import application.view.smallDragonAnimation;

public class Game {

	private MyCharacter myCharacter;
	public static ArrayList<smallDragon> smalldragons;
	public static ArrayList<fireAttack> fireAttack;
	private static Game game = null;// CON QUESTA TECNICA SI DICE CHE LA CLASSE GAME è SINGLETON CIOè UNICA !
	private boolean JumpRight = false;
	private boolean JumpLeft = false;
	private boolean actionInProgress = false;
	private int kills = 0;
	private int round = 1;
	private int liveEnemies = 0;
	
	private Game() {
		myCharacter = new MyCharacter();
		smalldragons = new ArrayList<smallDragon>();
		fireAttack = new ArrayList<fireAttack>();
		
	}
	
	public static Game getInstance() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}
	
	public void moveLeft() {
		myCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed >= 0)) {
			myCharacter.x -= myCharacter.speed;
		}
	}
	
	public void spawnSmallDragonLeft() {
		smallDragon drake = new smallDragon(true);
		liveEnemies = getLiveEnemies() + 1;
		smalldragons.add(drake);
	}
	
	public void spawnSmallDragonRight() {
		smallDragon drake = new smallDragon(false);
		liveEnemies = getLiveEnemies() + 1;
		smalldragons.add(drake);
	}
	
	
	public void smallDragonsmove() {
		
		// durante il movimento voglio che con una percentuale bassa ad ogni passo
		
		for(int i = 0 ; i< smalldragons.size();++i) {
			if(myCharacter.x < smalldragons.get(i).x ) {
				smalldragons.get(i).isRight = false;
			}
			else {
				smalldragons.get(i).isRight = true;
			}
			
			if(smalldragons.get(i).isRight) {
				smalldragons.get(i).x += smalldragons.get(i).speed;
			}
			else {
				smalldragons.get(i).x -= smalldragons.get(i).speed;
			}
			
			int prob = (int) (Math.random() * 500);
			if(prob <= 1) {
				fireAttack attacco = new fireAttack(smalldragons.get(i).x ,smalldragons.get(i).isRight);
				fireAttack.add(attacco);
			}
		}
	}
	
	public void moveFireAttacks() {
		for(int i = 0; i<fireAttack.size();++i) {
			if(fireAttack.get(i).isRight) {
				fireAttack.get(i).x += fireAttack.get(i).speed;
			}
			else {
				fireAttack.get(i).x -= fireAttack.get(i).speed;
			}
			// se va fuori schermo tolgo l'attacco
			if(fireAttack.get(i).x > 1000 || fireAttack.get(i).x < 0) {
				fireAttack.remove(i);
			}
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
	
	public boolean myCharAttacked() {
		
		for(int i = 0; i<fireAttack.size();++i) {
			if(fireAttack.get(i).isRight) {
				if(myCharacter.getRectangle().intersects(fireAttack.get(i).getRectangleRight())) {
					myCharacter.life--;
					return true;
				}
			}
			else {
				if(myCharacter.getRectangle().intersects(fireAttack.get(i).getRectangleLeft())) {
					myCharacter.life--;
					return true;
				}
			}
			
		}
		for(int i = 0; i<smalldragons.size();++i) {
			if(myCharacter.getRectangle().intersects(smalldragons.get(i).getRectangle())) {
				myCharacter.life--;
				return true;
			}
		}
		return false;
	}
	
	public void myCharAttackRight() {
		for(int i =0 ; i<smalldragons.size();++i) {
			if(myCharacter.attackRight().intersects(smalldragons.get(i).getRectangle())) {
				smalldragons.remove(i);
				GraphicPanel.smallDragonanimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
	}

	public void myCharAttackLeft() {
		for(int i = 0; i<smalldragons.size();++i) {
			if(myCharacter.attackLeft().intersects(smalldragons.get(i).getRectangle())) {
				smalldragons.remove(i);
				GraphicPanel.smallDragonanimations.remove(i);
				liveEnemies--;
				kills++;
			}
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

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getLiveEnemies() {
		return liveEnemies;
	}
}
