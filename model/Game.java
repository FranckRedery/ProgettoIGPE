package application.model;

import java.util.ArrayList;
import java.util.Random;

import application.Settings;
import application.view.Audio;
import application.view.DemonAnimations;
import application.view.GraphicPanel;
import application.view.MedusaAnimations;
import application.view.LizardAnimations;
import application.view.MyCharacterAnimations;
import application.view.SmallDragonAnimations;

public class Game {

	private MyCharacter myCharacter;
	private ArrayList<SmallDragon> smallDragons;
	private ArrayList<Lizard> lizards;
	private ArrayList<Demon> demons;
	private ArrayList<Medusa> meduse;
	private ArrayList<FireAttack> fireAttack;
	private ArrayList<Heart> hearts;
	private Dragon dragon;
	private static Game game = null;
	private boolean pause = false;
	private int kills = 0;
	private int round = 1;
	public static int liveEnemies = 0;
	
	private Audio heartTaken;
	private Audio kill = new Audio("kill.wav");
	private Audio roar = new Audio("roar.wav");
	private Audio dragonDead = new Audio("dragonDead.wav");
	private Audio fireAttackAudio;
	public static Audio myCharacterHitted;

	
	private Game() {
		myCharacter = new MyCharacter();
		smallDragons = new ArrayList<SmallDragon>();
		lizards = new ArrayList<Lizard>();
		demons = new ArrayList<Demon>();
		meduse = new ArrayList<Medusa>();
		fireAttack = new ArrayList<FireAttack>();
		hearts = new ArrayList<Heart>();
		dragon = new Dragon(-300);
		GraphicPanel.smallDragonAnimations = new ArrayList<SmallDragonAnimations>();
		GraphicPanel.lizardAnimations = new ArrayList<LizardAnimations>();	
		GraphicPanel.demonAnimations = new ArrayList<DemonAnimations>();
		GraphicPanel.medusaAnimations = new ArrayList<MedusaAnimations>();
		liveEnemies = 0;
		myCharacterHitted = new Audio("myCharacterHitted.wav");
		myCharacterHitted.reduceVolume();
		heartTaken = new Audio("heartTaken.wav");
		heartTaken.reduceVolume();
		fireAttackAudio = new Audio("fireAttack.wav");
		fireAttackAudio.reduceVolume();
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
	
	public void spawnSmallDragonLeft(int x) {
		SmallDragonAnimations animazione = new SmallDragonAnimations(true);
		GraphicPanel.smallDragonAnimations.add(animazione);
		SmallDragon drake = new SmallDragon(true,x);
		liveEnemies++;
		smallDragons.add(drake);
	}
	
	public void spawnSmallDragonRight(int x) {
		SmallDragonAnimations animazione = new SmallDragonAnimations(false);
		GraphicPanel.smallDragonAnimations.add(animazione);
		SmallDragon drake = new SmallDragon(false,x);
		liveEnemies++;
		smallDragons.add(drake);
	}
	
	public void smallDragonsMove() {
		
		for(int i = 0 ; i< smallDragons.size();++i) {
			if(myCharacter.x < smallDragons.get(i).x ) {
				smallDragons.get(i).isRight = false;
			}
			else {
				smallDragons.get(i).isRight = true;
			}
			
			if(smallDragons.get(i).isRight) {
				smallDragons.get(i).x += smallDragons.get(i).speed;
			}
			else {
				smallDragons.get(i).x -= smallDragons.get(i).speed;
			}
			
			// durante il movimento voglio che con una percentuale bassa ad ogni passo si generino attacchi
			// gli attacchi non voglio che siano generati quando il personaggio ed il nemico si trovano troppo vicini
			// altrimenti sarebbero troppo difficili da dodgare
			Random random = new Random();
			int r = random.nextInt(350);
			if(r == 1 && Math.abs(smallDragons.get(i).x- myCharacter.x) > 300 && smallDragons.get(i).x > 0 && smallDragons.get(i).x<1000) {
				FireAttack attacco = new FireAttack(smallDragons.get(i).x ,smallDragons.get(i).isRight);
				fireAttack.add(attacco);
				if(Settings.audio) {
					fireAttackAudio.restart();
				}
			}
		}
	}
	
	public void spawnLizardLeft(int x) {
		LizardAnimations animation = new LizardAnimations(true);
		animation.setCurrentAnimation(true);
		GraphicPanel.lizardAnimations.add(animation);	
		Lizard liz = new Lizard(true,x);
		liveEnemies++;
		lizards.add(liz);
	}
	
	public void spawnLizardRight(int x) {
		LizardAnimations animation = new LizardAnimations(false);
		animation.setCurrentAnimation(false);
		GraphicPanel.lizardAnimations.add(animation);	
		Lizard liz = new Lizard(false,x);
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
	
	public void spawnDemonLeft(int x) {
		DemonAnimations animation = new DemonAnimations(true);
		animation.setCurrentAnimation(true);
		GraphicPanel.demonAnimations.add(animation);	
		Demon demon = new Demon(true,x);
		liveEnemies++;
		demons.add(demon);
	}
	
	public void spawnDemonRight(int x) {
		DemonAnimations animation = new DemonAnimations(false);
		animation.setCurrentAnimation(false);
		GraphicPanel.demonAnimations.add(animation);	
		Demon demon = new Demon(false,x);
		liveEnemies++;
		demons.add(demon);
	}
	
	public void demonMove() {
		for(int i =0 ; i<demons.size();++i) {
			if(myCharacter.x < demons.get(i).x + 20) {
				demons.get(i).isRight = false;
			}
			else {
				demons.get(i).isRight = true;
			}
			
			if(demons.get(i).isRight) {
				demons.get(i).x += demons.get(i).speed;
			}
			else {
				demons.get(i).x -= demons.get(i).speed;
			}
		}
		
	}
	
	public void spawnMedusaLeft(int x) {
		MedusaAnimations animation = new MedusaAnimations(true);
		animation.setCurrentAnimation(true);
		GraphicPanel.medusaAnimations.add(animation);
		Medusa medusa = new Medusa(true,x);
		liveEnemies++;
		meduse.add(medusa);
	}
	
	public void spawnMedusaRight(int x) {
		MedusaAnimations animation = new MedusaAnimations(false);
		animation.setCurrentAnimation(false);
		GraphicPanel.medusaAnimations.add(animation);
		Medusa medusa = new Medusa(false,x);
		liveEnemies++;
		meduse.add(medusa);
	}
	
	public void medusaMove() {
		for(int i =0 ; i<meduse.size();++i) {
			if(!myCharacter.JumpLeft && !myCharacter.JumpRight && !myCharacter.JumpUP) {
				if(myCharacter.x < meduse.get(i).x) {
					meduse.get(i).isRight = false;
				}
				else {
					meduse.get(i).isRight = true;
				}
				
				if(meduse.get(i).isRight) {
					meduse.get(i).x += meduse.get(i).speed;
				}
				else {
					meduse.get(i).x -= meduse.get(i).speed;
				}
			}
		}
	}
	
	public void spawnDragon() {
		
		GraphicPanel.dragonAnimations.setCurrentAnimation(true);
		liveEnemies++;
		
	}
	
	public void moveDragon() {
		
		if(dragon.life == 0) {
			return;
		}
		
		// voglio che ci sia una probabilità che il drago effettui una carica di corsa contro il giocatore
		// solamente se però è ad una certa distanza , altrimenti non sarebbe possibile saltarlo e schivarlo
		Random rand = new Random();
		if(rand.nextInt(100) == 1 && !dragon.run && Math.abs(dragon.x - myCharacter.x) > 300) {
			dragon.run = true;
			dragon.speed = 20;
		}
		if(dragon.run == true && (dragon.x > 900 || dragon.x < -100)){
			dragon.run = false;
			dragon.speed = 2;
		}
		
		if(!dragon.run && myCharacter.x < dragon.x +100 && !myCharacter.JumpLeft && !myCharacter.JumpRight) {
			dragon.isRight = false;
			dragon.x -= dragon.speed;
		}
		else if(!dragon.run && myCharacter.x > dragon.x && !myCharacter.JumpLeft && !myCharacter.JumpRight) {
			dragon.isRight = true;
			dragon.x += dragon.speed;
		}
		if(dragon.run && dragon.isRight) {
			if(Settings.audio) {
				roar.start();
			}
			dragon.x += dragon.speed;
		}
		else if(dragon.run && !dragon.isRight) {
			if(Settings.audio) {
				roar.start();
			}
			dragon.x -= dragon.speed;
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
			Heart h = new Heart(x);
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
				if(Settings.audio) {
					heartTaken.start();
				}
				myCharacter.life++;
				hearts.remove(i);
			}
		}
	}
	
	public void moveLeft() {
		MyCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed >= 0)) {
			myCharacter.x -= myCharacter.speed;
		}
	}
	
	public void moveRight() {
		MyCharacterAnimations.isRight = true;
		if((myCharacter.x + myCharacter.speed <= Settings.windowSize - Settings.blockDim)) {
			myCharacter.x += myCharacter.speed;
		}
	}
	
	public void jumpUP() {
		myCharacter.y -= myCharacter.speed*8;
	}
	
	public void jumpDown() {
		myCharacter.y += myCharacter.speed*2;
	}
	
	public void jumpRightUp() {
		MyCharacterAnimations.isRight = true;
		if((myCharacter.x + myCharacter.speed*2 <= Settings.windowSize - Settings.blockDim)) {
			myCharacter.x += myCharacter.speed*2;
		}
		myCharacter.y -= myCharacter.speed*4;
	}
	
	public void jumpRightDown() {
		MyCharacterAnimations.isRight = true;
		if((myCharacter.x + myCharacter.speed*2 <= Settings.windowSize - Settings.blockDim)) {
			myCharacter.x += myCharacter.speed*2;
		}
		myCharacter.y += myCharacter.speed*4;
	}
	
	public void jumpLeftUp() {
		MyCharacterAnimations.isRight = false;
		if((myCharacter.x - myCharacter.speed*2 >= 0)) {
			myCharacter.x -= myCharacter.speed*2;
		}
		myCharacter.y -= myCharacter.speed*4;
	}
	
	public void jumpLeftDown() {
		MyCharacterAnimations.isRight = false;
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
		for(int i = 0; i<smallDragons.size();++i) {
			if(myCharacter.getRectangle().intersects(smallDragons.get(i).getRectangle())) {
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
		for(int i = 0; i<demons.size();++i) {
			if(myCharacter.getRectangle().intersects(demons.get(i).getRectangle())) {
				myCharacter.life--;
				return true;
			}
		}
		
		for(int i = 0; i<meduse.size();++i) {
			if(myCharacter.getRectangle().intersects(meduse.get(i).getRectangle())) {
				myCharacter.life--;
				return true;
			}
		}
		if(dragon.life > 0) {
			if(myCharacter.getRectangle().intersects(dragon.getRectangle())) {
				myCharacter.life--;
				return true;
			}
		}
		return false;
	}
	
	
	public boolean myCharAttackRight() {
		for(int i =0 ; i<smallDragons.size();++i) {
			if(myCharacter.attackRight().intersects(smallDragons.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				smallDragons.remove(i);
				GraphicPanel.smallDragonAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		for(int i = 0; i<lizards.size();++i) {
			if(myCharacter.attackRight().intersects(lizards.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				lizards.remove(i);
				GraphicPanel.lizardAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		for(int i = 0; i<demons.size();++i) {
			if(myCharacter.attackRight().intersects(demons.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				demons.remove(i);
				GraphicPanel.demonAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		for(int i = 0; i<meduse.size();++i) {
			if(myCharacter.attackRight().intersects(meduse.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				meduse.remove(i);
				GraphicPanel.medusaAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		if(dragon.life > 0) {
			if(myCharacter.attackRight().intersects(dragon.getRectangle()) && !dragon.invulnerability) {
				if(Settings.audio) {
					kill.restart();
				}
				dragon.life--;
				if(dragon.life == 0) {
					liveEnemies--;
					if(Settings.audio) {
						dragonDead.start();
					}
				}
				// boolean che serve per capire se il drago è stato attaccato per poi far partire l'invulnerabilità
				else {
					return true;
				}
			}
		}
		return false;
	}

	public boolean myCharAttackLeft() {
		for(int i = 0; i<smallDragons.size();++i) {
			if(myCharacter.attackLeft().intersects(smallDragons.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				smallDragons.remove(i);
				GraphicPanel.smallDragonAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		for(int i = 0; i<lizards.size();++i) {
			if(myCharacter.attackLeft().intersects(lizards.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				lizards.remove(i);
				GraphicPanel.lizardAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		for(int i = 0; i<demons.size();++i) {
			if(myCharacter.attackLeft().intersects(demons.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				demons.remove(i);
				GraphicPanel.demonAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		for(int i = 0; i<meduse.size();++i) {
			if(myCharacter.attackLeft().intersects(meduse.get(i).getRectangle())) {
				if(Settings.audio) {
					kill.restart();
				}
				meduse.remove(i);
				GraphicPanel.medusaAnimations.remove(i);
				liveEnemies--;
				kills++;
			}
		}
		if(dragon.life > 0) {
			if(myCharacter.attackLeft().intersects(dragon.getRectangle())  && !dragon.invulnerability) {
				if(Settings.audio) {
					kill.restart();
				}
				dragon.life--;
				if(dragon.life == 0) {
					liveEnemies--;
					if(Settings.audio) {
						dragonDead.start();
					}
				}
				else {
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Demon> getDemons() {
		return demons;
	}
	
	public ArrayList<Heart> getHearts() {
		return hearts;
	}

	
	public ArrayList<SmallDragon> getSmalldragons() {
		return smallDragons;
	}

	public ArrayList<Lizard> getLizards() {
		return lizards;
	}

	public ArrayList<FireAttack> getFireAttack() {
		return fireAttack;
	}
	
	
	public boolean isActionInProgress() {
		return myCharacter.actionInProgress;
	}

	public void setActionInProgress(boolean inProgress) {
		myCharacter.actionInProgress = inProgress;
	}

	public boolean isJumpLeft() {
		return myCharacter.JumpLeft;
	}

	public void setJumpLeft(boolean jumpLeft) {
		myCharacter.JumpLeft = jumpLeft;
	}

	public boolean isJumpRight() {
		return myCharacter.JumpRight;
	}

	public void setJumpRight(boolean jumpRight) {
		myCharacter.JumpRight = jumpRight;
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

	public boolean isJumpUP() {
		return myCharacter.JumpUP;
	}

	public void setJumpUP(boolean jumpUP) {
		myCharacter.JumpUP = jumpUP;
	}

	public ArrayList<Medusa> getMedusa() {
		return meduse;
	}

	public Dragon getDragon() {
		return dragon;
	}
}