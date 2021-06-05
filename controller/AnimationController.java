package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import application.view.myCharacterAnimations;
import application.model.Game;
import application.model.MyCharacter;
import application.view.CharacterAnimation;
import application.view.GraphicPanel;

public class AnimationController extends KeyAdapter {

	private GraphicPanel panel;

	
	public AnimationController(GraphicPanel p) {
		panel = p;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(Game.getInstance().gameOver() && e.getKeyCode() == KeyEvent.VK_R) {
			Game.restartGame();
			panel.repaint();
			return;
		}
		
		if(Game.getInstance().getDragon().life == 0 && e.getKeyCode() == KeyEvent.VK_R) {
			Game.restartGame();
			panel.repaint();
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P && !Game.getInstance().gameOver()) {
			if(Game.getInstance().isPause()) {
				Game.getInstance().setPause(false);
			}
			else {
				Game.getInstance().setPause(true);
			}
			return;
		}
		
		if(!Game.getInstance().gameOver() && !Game.getInstance().isPause()) {
			switch (e.getKeyCode()) {
			
			case KeyEvent.VK_LEFT:
					if(!Game.getInstance().isJumpLeft() && !Game.getInstance().isJumpRight() ) {
						Game.getInstance().setActionInProgress(true);
						Game.getInstance().moveLeft();
						Game.getInstance().getCharacter().right = false;
						GraphicPanel.updateAnimation(myCharacterAnimations.WALK_LEFT);
					}			
				break;

			case KeyEvent.VK_RIGHT:
				if(!Game.getInstance().isJumpLeft() && !Game.getInstance().isJumpRight()) {
					Game.getInstance().setActionInProgress(true);
					Game.getInstance().moveRight();
					Game.getInstance().getCharacter().right = true;
					GraphicPanel.updateAnimation(myCharacterAnimations.WALK_RIGHT);
				}		
				break;

			case KeyEvent.VK_UP:
				if(!Game.getInstance().isActionInProgress()) {
					Game.getInstance().setActionInProgress(true);
					Game.getInstance().setJumpUP(true);
					if(Game.getInstance().getCharacter().right) {
						GraphicPanel.updateAnimation(myCharacterAnimations.JUMP_RIGHT);
					}
					else {
						GraphicPanel.updateAnimation(myCharacterAnimations.JUMP_LEFT);
					}
				}		
				
			case KeyEvent.VK_D : 
				if(!Game.getInstance().isActionInProgress()) {
					Game.getInstance().setActionInProgress(true);
					Game.getInstance().setJumpRight(true);
					Game.getInstance().getCharacter().right = true;
					GraphicPanel.updateAnimation(myCharacterAnimations.JUMP_RIGHT);
				}
				break;
			
			case KeyEvent.VK_S :
				if(!Game.getInstance().isActionInProgress()) {
					Game.getInstance().setActionInProgress(true);
					Game.getInstance().setJumpLeft(true);
					Game.getInstance().getCharacter().right = false;
					GraphicPanel.updateAnimation(myCharacterAnimations.JUMP_LEFT);
				}
				break;
				
				
			case KeyEvent.VK_A:
				if(!Game.getInstance().isJumpLeft() && !Game.getInstance().isJumpRight() && !Game.getInstance().isActionInProgress()) {
					Game.getInstance().setActionInProgress(true);
					if(myCharacterAnimations.isRight) {
						GraphicPanel.updateAnimation(myCharacterAnimations.ATTACK_RIGHT);
						Game.getInstance().myCharAttackRight();
					}
					else {
						GraphicPanel.updateAnimation(myCharacterAnimations.ATTACK_LEFT);
						Game.getInstance().myCharAttackLeft();
					}
				}
				break;

			default:
				if(myCharacterAnimations.isRight) {
					GraphicPanel.updateAnimation(myCharacterAnimations.IDLE_RIGHT);
				}
				else {
					GraphicPanel.updateAnimation(myCharacterAnimations.IDLE_LEFT);
				}
				break;
			}
		}
	}
	
	public void update() {
		panel.update();
	}

}