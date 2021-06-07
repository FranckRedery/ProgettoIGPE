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
				if(Game.getInstance().getKills() == 4) {
					Game.getInstance().setRound(2);
				}
				if(Game.getInstance().getKills() == 15) {
					Game.getInstance().setRound(3);
				}
				if(Game.getInstance().getKills() == 27) {
					Game.getInstance().setRound(4);
				}
				if(Game.getInstance().getKills() == 40) {
					Game.getInstance().setRound(5);
				}
				if(Game.getInstance().getKills() == 54) {
					Game.getInstance().setRound(6);
				}
				if(Game.getInstance().getKills() == 69) {
					Game.getInstance().setRound(7);
				}
				if(Game.getInstance().getKills() == 85) {
					Game.getInstance().setRound(8);
				}
				if(Game.getInstance().getKills() == 99) {
					Game.getInstance().setRound(9);
				}
				
				if(Game.getInstance().getRound() == 1) {
					
					if(Game.getInstance().getLiveEnemies() < 1) {
							Game.getInstance().spawnSmallDragonLeft(-100);
							Game.getInstance().spawnSmallDragonRight(1100);
					}
				}
				
				if(Game.getInstance().getRound() == 2) {
					if(Game.getInstance().getLiveEnemies() < 2) {
						if(random.nextInt(2) == 0) {
							Game.getInstance().spawnSmallDragonLeft(-200);
							Game.getInstance().spawnLizardRight(1300);
						}
						else {
							Game.getInstance().spawnSmallDragonRight(1200);
							Game.getInstance().spawnLizardLeft(-300);
						}
					}
				}
				
				if(Game.getInstance().getRound() == 3) {
					if(Game.getInstance().getLiveEnemies() < 3) {
						if(random.nextInt(2) == 0) {
							Game.getInstance().spawnSmallDragonRight(1100);
							Game.getInstance().spawnSmallDragonLeft(-250);
							Game.getInstance().spawnLizardLeft(-350);
							Game.getInstance().spawnDemonRight(1300);
							Game.getInstance().spawnDemonLeft(-100);
						}
						else {
							Game.getInstance().spawnSmallDragonLeft(random.nextInt(500)-1000);
							Game.getInstance().spawnSmallDragonRight(random.nextInt(500)+1000);
							Game.getInstance().spawnLizardRight(random.nextInt(700)+1000);
							Game.getInstance().spawnDemonLeft(-300);
							Game.getInstance().spawnDemonRight(1500);
						}
					}
				}
				
				if(Game.getInstance().getRound() == 4 && Game.getInstance().getLiveEnemies()<3) {
					Game.getInstance().spawnSmallDragonRight(1200);
					Game.getInstance().spawnDemonRight(1600);
					Game.getInstance().spawnDemonLeft(-300);
					Game.getInstance().spawnLizardRight(1250);
					Game.getInstance().spawnMedusaLeft(-200);
					Game.getInstance().spawnMedusaRight(1500);
				}
				
				if(Game.getInstance().getRound() == 5 && Game.getInstance().getLiveEnemies()<4) {
					Game.getInstance().spawnMedusaLeft(-400);
					Game.getInstance().spawnMedusaRight(1200);
					Game.getInstance().spawnMedusaLeft(-200);
					Game.getInstance().spawnLizardLeft(-500);
					Game.getInstance().spawnSmallDragonRight(1100);
					Game.getInstance().spawnSmallDragonLeft(-150);
				}
				if(Game.getInstance().getRound() == 6 && Game.getInstance().getLiveEnemies()<4) {
					Game.getInstance().spawnDemonLeft(-400);
					Game.getInstance().spawnLizardRight(1200);
					Game.getInstance().spawnMedusaLeft(-200);
					Game.getInstance().spawnMedusaRight(1500);
					Game.getInstance().spawnSmallDragonRight(1100);
					Game.getInstance().spawnSmallDragonLeft(-150);
				}
				if(Game.getInstance().getRound() == 7 && Game.getInstance().getLiveEnemies()<4) {
					Game.getInstance().spawnDemonLeft(-400);
					Game.getInstance().spawnDemonRight(1300);
					Game.getInstance().spawnLizardRight(1200);
					Game.getInstance().spawnLizardLeft(-200);
					Game.getInstance().spawnMedusaRight(1500);
					Game.getInstance().spawnSmallDragonRight(1100);
					Game.getInstance().spawnSmallDragonLeft(-150);
				}
				if(Game.getInstance().getRound() == 8 && Game.getInstance().getLiveEnemies()<4) {
					Game.getInstance().spawnDemonLeft(-400);
					Game.getInstance().spawnDemonRight(1300);
					Game.getInstance().spawnLizardRight(1200);
					Game.getInstance().spawnLizardLeft(-200);
					Game.getInstance().spawnMedusaLeft(-100);
					Game.getInstance().spawnMedusaRight(1500);
					Game.getInstance().spawnSmallDragonRight(1100);
					Game.getInstance().spawnSmallDragonLeft(-150);
				}
				
				if(Game.getInstance().getRound() == 9 && Game.getInstance().getLiveEnemies() == 0) {
					Game.getInstance().spawnDragon();
				}
				if(Game.getInstance().getRound() == 9 && Game.getInstance().getLiveEnemies() == 1) {
					Game.getInstance().spawnSmallDragonRight(1050);
					Game.getInstance().spawnSmallDragonLeft(-450);
				}
				
				if(Game.getInstance().getDragon().life == 0 && Game.liveEnemies > 0) {
					for(int i = 0; i<Game.getInstance().getSmalldragons().size();++i) {
						Game.getInstance().getSmalldragons().remove(i);
					}
					if(Game.getInstance().getSmalldragons().size() == 0 && Game.getInstance().getMedusa().size() == 0) {
						Game.liveEnemies = -1;
					}
				}
				
				if(Game.getInstance().getCharacter().life < 5 && !Game.getInstance().gameOver() && !Game.getInstance().isPause() && Game.getInstance().getDragon().life != 0) {
					Game.getInstance().spawnHeart(Game.getInstance().getRound());
				}
				
				if(!Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					Game.getInstance().smallDragonsmove();
					Game.getInstance().moveHearts();
					Game.getInstance().lizardMove();
					Game.getInstance().heartTaken();
					Game.getInstance().demonMove();
					Game.getInstance().medusaMove();
					if(Game.getInstance().getRound() == 9) {
						Game.getInstance().moveDragon();
					}
				}
				
				
				if(!Game.getInstance().isPause()) {
					Game.getInstance().moveFireAttacks();
				}
				

				
				if(Invulnerability == 0 && !Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					// se vengo attaccato divento invulnerabile
					if(Game.getInstance().myCharAttacked()) {
						if(Settings.audio) {
							Game.myCharacterHitted.start();
						}
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
				}
				
				// passati 2 secondi l'invulnerabilità sparisce 
				if((System.nanoTime() - AnimationController.drakeInvuln)/1000000000 == 2 && !Game.getInstance().isPause()) {
					AnimationController.drakeInvuln = 0;
					Game.getInstance().getDragon().invulnerability = false;
				}
				// se si è in pausa e passano i 2 secondi si resetta il timer dell'invulnerabilità
				else if(AnimationController.drakeInvuln != 0 && Game.getInstance().isPause()) {
					AnimationController.drakeInvuln = System.nanoTime();
				}
				
				
				controller.update();
				Thread.sleep(30);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
