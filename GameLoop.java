package application;

import java.util.Random;

import application.controller.AnimationController;
import application.model.Game;

public class GameLoop extends Thread{

	public AnimationController controller;
	public GameLoop(AnimationController controller) {
		this.controller = controller;	
	}
	
	@Override
	public void run() {
		long Invulnerability = 0;
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
				// deve sempre prima spawnare a sx e poi a dx altrimenti l'animazione iniziale viene settata al contrario
				if(Game.getInstance().getRound() == 1) {
					if(Game.getInstance().getLiveEnemies() < 4) {
						if(random.nextInt(2) == 0) {
							Game.getInstance().spawnSmallDragonLeft(random.nextInt(700)-1000);
						}
						else {
							Game.getInstance().spawnSmallDragonRight(random.nextInt(300)+1000);
						}
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
				
				
				if(Game.getInstance().getCharacter().life < 5 && !Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					Game.getInstance().spawnHeart(Game.getInstance().getRound());
				}
				
				if(!Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					Game.getInstance().smallDragonsmove();
					Game.getInstance().moveHearts();
					Game.getInstance().lizardMove();
					Game.getInstance().heartTaken();
				}
				
				if(!Game.getInstance().isPause()) {
					Game.getInstance().moveFireAttacks();
				}
				
				// se sono stato attaccato e metto in pausa, l'invulnerabilità tolta la pausa non deve scomparire subito ! 
				if(Invulnerability != 0 && Game.getInstance().isPause()) {
					Invulnerability = System.currentTimeMillis()-Invulnerability;
				}
				
				if(Invulnerability == 0 && !Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					// se vengo attaccato divento invulnerabile
					if(Game.getInstance().myCharAttacked()) {
						Invulnerability = System.currentTimeMillis();
						Game.getInstance().getCharacter().invulnerability = true;
					}
				}
				// passati 2 secondi l'invulnerabilità sparisce 
				if((System.currentTimeMillis() - Invulnerability)/1000 == 2) {
					Invulnerability = 0;
					Game.getInstance().getCharacter().invulnerability = false;
				}
				
				
				
				
				
				controller.update();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
