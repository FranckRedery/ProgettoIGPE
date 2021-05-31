package application;

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
				if(Game.getInstance().getKills() == 8) {
					Game.getInstance().setRound(2);
				}
				// deve sempre prima spawnare a sx e poi a dx altrimenti l'animazione iniziale viene settata al contrario
				if(Game.getInstance().getRound() == 1) {
					if(Game.getInstance().getLiveEnemies() == 0) {
						Game.getInstance().spawnSmallDragonLeft(-200);
						Game.getInstance().spawnSmallDragonRight(1005);
						Game.getInstance().spawnSmallDragonLeft(-300);
						Game.getInstance().spawnSmallDragonRight(1305);
					}
				}
				
				if(Game.getInstance().getRound() == 2) {
					if(Game.getInstance().getLiveEnemies() == 0) {
						Game.getInstance().spawnSmallDragonLeft(-200);
						Game.getInstance().spawnSmallDragonRight(1005);
						Game.getInstance().spawnLizardLeft(-100);
						Game.getInstance().spawnLizardRight(1205);
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
					Invulnerability = System.currentTimeMillis();
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
