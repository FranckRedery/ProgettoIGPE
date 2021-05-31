package application.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import application.Settings;
import application.view.GraphicPanel;
import application.view.lizardAnimations;
import application.view.myCharacterAnimations;
import application.view.smallDragonAnimations;
import application.view.smallDragonAnimation;

public class Game {

	private MyCharacter myCharacter;
	private ArrayList<smallDragon> smalldragons;
	private ArrayList<lizard> lizards;
	private ArrayList<fireAttack> fireAttack;
	private ArrayList<heart> hearts;
	private static Game game = null;// CON QUESTA TECNICA SI DICE CHE LA CLASSE GAME è SINGLETON CIOè UNICA !
	private boolean JumpRight = false;
	private boolean JumpLeft = false;
	private boolean actionInProgress = false;
	private boolean pause = false;
	private int kills = 0;
	private int round = 1;
	private int liveEnemies = 0;
	
	private Game() {
		myCharacter = new MyCharacter();
		smalldragons = new ArrayList<smallDragon>();
		lizards = new ArrayList<lizard>();
		fireAttack = new ArrayList<fireAttack>();
		hearts = new ArrayList<heart>();
		GraphicPanel.smallDragonanimations = new ArrayList<smallDragonAnimations>();
		GraphicPanel.lizardanimations = new ArrayList<lizardAnimations>();	
	}
	
	public static Game getInstance() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}
	
	public static void restartGame() {
		game = new Game();
	}
	
	public boolean gameOver() {
		return myCharacter.life == 0;
	}
	
	public void moveLeft() {
		myCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed >= 0)) {
			myCharacter.x -= myCharacter.speed;
		}
	}
	
	public void spawnSmallDragonLeft(int x) {
		smallDragon drake = new smallDragon(true,x);
		liveEnemies++;
		smalldragons.add(drake);
	}
	
	public void spawnSmallDragonRight(int x) {
		smallDragon drake = new smallDragon(false,x);
		liveEnemies++;
		smalldragons.add(drake);
	}
	
	public void smallDragonsmove() {
		
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
			
			// durante il movimento voglio che con una percentuale bassa ad ogni passo si generino attacchi
			Random random = new Random();
			int r = random.nextInt(500);
			if(r == 1) {
				fireAttack attacco = new fireAttack(smalldragons.get(i).x ,smalldragons.get(i).isRight);
				fireAttack.add(attacco);
			}
		}
	}
	
	public void spawnLizardLeft(int x) {
		lizard liz = new lizard(true,x);
		liveEnemies++;
		lizards.add(liz);
	}
	
	public void spawnLizardRight(int x) {
		lizard liz = new lizard(false,x);
		liveEnemies++;
		lizards.add(liz);
	}
	
	public void lizardMove() {
		for(int i =0 ; i<lizards.size();++i) {
			if(myCharacter.x < lizards.get(i).x +50) {
				lizards.get(i).isRight = false;
			}
			else {
				lizards.get(i).isRight = true;
			}
			
			if(lizards.get(i).isRight) {
				lizards.get(i).x += lizards.get(i).speed;
			}
			else {
				lizards.get(i).x -= lizards.get(i).speed;
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
	
	
	public void spawnHeart(int round) {
		
		Random random = new Random();
		// la probabilità di spawn del cuore è inversamente proporzionale al numero di vite mancanti 
		// e direttamente proporzionale ai round
		if(random.nextInt(4000) <= round * (5 - myCharacter.life)) {
			
			int x = random.nextInt(800) + 100;
			heart h = new heart(x);
			hearts.add(h);
		}
		
	}
	
	public void moveHearts() {
		for(int i = 0; i<hearts.size();++i) {
			if(hearts.get(i).y < 864) {
				hearts.get(i).y += 6;
			}
		}
	}
	
	
	public void heartTaken() {
		for(int i = 0; i<hearts.size();++i) {
			if(myCharacter.getRectangle().intersects(hearts.get(i).getRectangle())) {
				myCharacter.life++;
				hearts.remove(i);
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
				if(myCharacter.getRectangle().intersects(fireAttack.get(i).getRectangle())) {
					myCharacter.life--;
					return true;
				}	
			}
		for(int i = 0; i<smalldragons.size();++i) {
			if(myCharacter.getRectangle().intersects(smalldragons.get(i).getRectangle())) {
				myCharacter.life--;
				return true;
			}
		}
		
		for(int i = 0; i<lizards.size();++i) {
			if(myCharacter.getRectangle().intersects(lizards.get(i).getRectangle())) {
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
		for(int i = 0; i<lizards.size();++i) {
			if(myCharacter.attackRight().intersects(lizards.get(i).getRectangle())) {
				lizards.remove(i);
				GraphicPanel.lizardanimations.remove(i);
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
		for(int i = 0; i<lizards.size();++i) {
			if(myCharacter.attackLeft().intersects(lizards.get(i).getRectangle())) {
				lizards.remove(i);
				GraphicPanel.lizardanimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
	}	
	
	public ArrayList<heart> getHearts() {
		return hearts;
	}

	
	public ArrayList<smallDragon> getSmalldragons() {
		return smalldragons;
	}

	public ArrayList<lizard> getLizards() {
		return lizards;
	}

	public ArrayList<fireAttack> getFireAttack() {
		return fireAttack;
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

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
}
