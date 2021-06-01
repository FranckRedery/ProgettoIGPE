package application;

import java.util.Random;

import application.controller.AnimationController;
import application.model.Game;

public class GameLoop extends Thread{

	public AnimationController controller;
	private long Invulnerability = 0;
	public GameLoop(AnimationController controller) {
		this.controller = controller;	
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				Random random = new Random();
				if(Game.getInstance().getKills() == 8) {
					Game.getInstance().setRound(2);
				}
				if(Game.getInstance().getKills() == 20) {
					Game.getInstance().setRound(3);
				}
				if(Game.getInstance().getKills() == 40) {
					Game.getInstance().setRound(4);
				}
				// deve sempre prima spawnare a sx e poi a dx altrimenti l'animazione iniziale viene settata al contrario
				if(Game.getInstance().getRound() == 1) {
					
					if(Game.getInstance().getLiveEnemies() < 1) {
							Game.getInstance().spawnSmallDragonLeft(random.nextInt(900)-1000);
							Game.getInstance().spawnSmallDragonRight(random.nextInt(100)+1000);
					}
				}
				
				if(Game.getInstance().getRound() == 2) {
					if(Game.getInstance().getLiveEnemies() < 5) {
						if(random.nextInt(2) == 0) {
							Game.getInstance().spawnSmallDragonLeft(random.nextInt(700)-1000);
							Game.getInstance().spawnLizardRight(random.nextInt(500)+1000);
						}
						else {
							Game.getInstance().spawnSmallDragonRight(random.nextInt(300)+1000);
							Game.getInstance().spawnLizardLeft(random.nextInt(500)-1000);
						}
					}
				}
				
				if(Game.getInstance().getRound() == 3) {
					if(Game.getInstance().getLiveEnemies() < 6) {
						if(random.nextInt(2) == 0) {
							Game.getInstance().spawnSmallDragonRight(random.nextInt(500)+1000);
							Game.getInstance().spawnSmallDragonLeft(random.nextInt(500)-1000);
							Game.getInstance().spawnLizardLeft(random.nextInt(300)-1000);
							Game.getInstance().spawnDemonRight(random.nextInt(900)+1000);
							Game.getInstance().spawnDemonLeft(random.nextInt(100)-1000);
						}
						else {
							Game.getInstance().spawnSmallDragonLeft(random.nextInt(500)-1000);
							Game.getInstance().spawnSmallDragonRight(random.nextInt(500)+1000);
							Game.getInstance().spawnLizardRight(random.nextInt(700)+1000);
							Game.getInstance().spawnDemonLeft(random.nextInt(100)-1000);
							Game.getInstance().spawnDemonRight(random.nextInt(900)+1000);
						}
					}
				}
				
				
				if(Game.getInstance().getCharacter().life < 5 && !Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					Game.getInstance().spawnHeart(Game.getInstance().getRound());
				}
				
				if(!Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					Game.getInstance().smallDragonsmove();
					Game.getInstance().moveHearts();
					Game.getInstance().lizardMove();
					Game.getInstance().heartTaken();
					Game.getInstance().demonMove();
				}
				
				if(!Game.getInstance().isPause()) {
					Game.getInstance().moveFireAttacks();
				}
				

				
				if(Invulnerability == 0 && !Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					// se vengo attaccato divento invulnerabile
					if(Game.getInstance().myCharAttacked()) {
						Invulnerability = System.nanoTime();
						Game.getInstance().getCharacter().invulnerability = true;
					}
				}
				// passati 2 secondi l'invulnerabilità sparisce 
				if((System.nanoTime() - Invulnerability)/1000000000 == 2 && !Game.getInstance().isPause()) {
					Invulnerability = 0;
					Game.getInstance().getCharacter().invulnerability = false;
				}
				// se si è in pausa e passano i 2 secondi si resetta il timer dell'invulnerabilità 
				else if(Invulnerability != 0 && Game.getInstance().isPause()) {
					Invulnerability = System.nanoTime();
					System.out.println(Invulnerability);
				}
				
				
				
				
				controller.update();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
