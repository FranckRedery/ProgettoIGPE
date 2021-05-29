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
				if(Game.getInstance().getKills() == 6) {
					Game.getInstance().setRound(2);
				}
				
				if(Game.getInstance().getRound() == 1) {
					if(Game.getInstance().getLiveEnemies() == 0) {
						Game.getInstance().spawnSmallDragonLeft(-200);
						Game.getInstance().spawnSmallDragonRight(1005);
						Game.getInstance().spawnSmallDragonLeft(-300);
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
				
				Game.getInstance().smallDragonsmove();
				Game.getInstance().moveFireAttacks();
				Game.getInstance().lizardMove();
				
				
				
				
				
				
				if(Invulnerability == 0) {
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
