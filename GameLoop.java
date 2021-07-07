package application;


import application.controller.AnimationController;
import application.model.Game;

public class GameLoop extends Thread{

	public AnimationController controller;
	private long invulnerability = 0;
	
	public GameLoop(AnimationController controller) {
		this.controller = controller;	
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				
				switch(Game.getInstance().getKills()) {
				
					case 4 : 
						Game.getInstance().setRound(2);
						break;
						
					case 15 : 
						Game.getInstance().setRound(3);
						break;
						
					case 27 : 
						Game.getInstance().setRound(4);
						break;
						
					case 40 : 
						Game.getInstance().setRound(5);
						break;
						
					case 54 : 
						Game.getInstance().setRound(6);
						break;
						
					case 69 : 
						Game.getInstance().setRound(7);
						break;
						
					case 85 : 
						Game.getInstance().setRound(8);
						break;
						
					case 99 : 
						Game.getInstance().setRound(9);
						break;
						
					default :
						break;
				}
				
				switch(Game.getInstance().getRound()) {
				
				case 1 : 
					if(Game.getInstance().getLiveEnemies() < 1) {
						Game.getInstance().spawnSmallDragonLeft(-100);
						Game.getInstance().spawnSmallDragonRight(1100);
					}
					break;
					
				case 2 : 
					if(Game.getInstance().getLiveEnemies() < 2) {
						Game.getInstance().spawnSmallDragonLeft(-200);
						Game.getInstance().spawnLizardRight(1300);
					}
					break;
					
				case 3 : 
					if(Game.getInstance().getLiveEnemies() < 3) {
						Game.getInstance().spawnSmallDragonRight(1100);
						Game.getInstance().spawnSmallDragonLeft(-250);
						Game.getInstance().spawnLizardLeft(-350);
						Game.getInstance().spawnDemonRight(1300);
						Game.getInstance().spawnDemonLeft(-100);
					}
					break;
					
				case 4 : 
					if(Game.getInstance().getLiveEnemies()<3) {
					Game.getInstance().spawnSmallDragonRight(1200);
					Game.getInstance().spawnDemonRight(1600);
					Game.getInstance().spawnDemonLeft(-300);
					Game.getInstance().spawnLizardRight(1250);
					Game.getInstance().spawnMedusaLeft(-200);
					Game.getInstance().spawnMedusaRight(1500);
					}
					break;
					
				case 5 : 
					if(Game.getInstance().getLiveEnemies()<4) {
						Game.getInstance().spawnMedusaLeft(-400);
						Game.getInstance().spawnMedusaRight(1200);
						Game.getInstance().spawnMedusaLeft(-200);
						Game.getInstance().spawnLizardLeft(-500);
						Game.getInstance().spawnSmallDragonRight(1100);
						Game.getInstance().spawnSmallDragonLeft(-150);
					}
					break;
				
				case 6 :
					if(Game.getInstance().getLiveEnemies()<4) {
						Game.getInstance().spawnDemonLeft(-400);
						Game.getInstance().spawnLizardRight(1200);
						Game.getInstance().spawnMedusaLeft(-200);
						Game.getInstance().spawnMedusaRight(1500);
						Game.getInstance().spawnSmallDragonRight(1100);
						Game.getInstance().spawnSmallDragonLeft(-150);
					}
					break;
					
				case 7 :
					if(Game.getInstance().getLiveEnemies()<4) {
						Game.getInstance().spawnDemonLeft(-400);
						Game.getInstance().spawnDemonRight(1300);
						Game.getInstance().spawnLizardRight(1200);
						Game.getInstance().spawnLizardLeft(-200);
						Game.getInstance().spawnMedusaRight(1500);
						Game.getInstance().spawnSmallDragonRight(1100);
						Game.getInstance().spawnSmallDragonLeft(-150);
					}
					break;
					
				case 8 : 
					if(Game.getInstance().getLiveEnemies()<4) {
						Game.getInstance().spawnDemonLeft(-400);
						Game.getInstance().spawnDemonRight(1300);
						Game.getInstance().spawnLizardRight(1200);
						Game.getInstance().spawnLizardLeft(-200);
						Game.getInstance().spawnMedusaLeft(-100);
						Game.getInstance().spawnMedusaRight(1500);
						Game.getInstance().spawnSmallDragonRight(1100);
						Game.getInstance().spawnSmallDragonLeft(-150);
					}
					break;
					
				case 9 :
					if(Game.getInstance().getLiveEnemies() == 0) {
						Game.getInstance().spawnDragon();
					}
					if(Game.getInstance().getLiveEnemies() == 1) {
						Game.getInstance().spawnSmallDragonRight(1050);
						Game.getInstance().spawnSmallDragonLeft(-450);
					}
					break;
					
				default : 
					break;
				}
				
				
				// se il drago è morto rimuovo tutti i nemici (nel round insieme a lui ci sono solo i piccoli draghi) 
				// e non ne faccio più spawnare
				if(Game.getInstance().getDragon().life == 0 && Game.liveEnemies > 0) {
					for(int i = 0; i<Game.getInstance().getSmalldragons().size();++i) {
						Game.getInstance().getSmalldragons().remove(i);
					}
					if(Game.getInstance().getSmalldragons().size() == 0 && Game.getInstance().getMedusa().size() == 0) {
						Game.liveEnemies = -1;
					}
				}
				
				// se la vita è < 5 chiedo di spawnare un cuore con una probabilità in base a round e vita
				if(Game.getInstance().getCharacter().life < 5 && !Game.getInstance().gameOver() && !Game.getInstance().isPause() && Game.getInstance().getDragon().life != 0) {
					Game.getInstance().spawnHeart(Game.getInstance().getRound());
				}
				
				// tutti i movimenti dei vari nemici 
				if(!Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					Game.getInstance().smallDragonsMove();
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
				

				
				if(invulnerability == 0 && !Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
					// se vengo attaccato divento invulnerabile
					if(Game.getInstance().myCharAttacked()) {
						if(Settings.audio) {
							Game.myCharacterHitted.start();
						}
						invulnerability = System.nanoTime();
						Game.getInstance().getCharacter().invulnerability = true;
					}
				}
				// passati 2 secondi l'invulnerabilità del mio personaggio sparisce 
				if((System.nanoTime() - invulnerability)/1000000000 == 2 && !Game.getInstance().isPause()) {
					invulnerability = 0;
					Game.getInstance().getCharacter().invulnerability = false;
				}
				// se si è in pausa si resetta il timer dell'invulnerabilità del personaggio
				else if(invulnerability != 0 && Game.getInstance().isPause()) {
					invulnerability = System.nanoTime();
				}
				
				// passati 2 secondi l'invulnerabilità del Boss (Drago) sparisce 
				if((System.nanoTime() - AnimationController.drakeInvuln)/1000000000 == 2 && !Game.getInstance().isPause()) {
					AnimationController.drakeInvuln = 0;
					Game.getInstance().getDragon().invulnerability = false;
				}
				// se si è in pausa si resetta il timer dell'invulnerabilità del Boss
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
