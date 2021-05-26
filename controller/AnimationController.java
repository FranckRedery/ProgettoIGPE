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
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
				if(!Game.getInstance().isJumpLeft() && !Game.getInstance().isJumpRight() ) {
					Game.getInstance().setActionInProgress(true);
					Game.getInstance().moveLeft();
					panel.updateAnimation(myCharacterAnimations.WALK_LEFT);
				}			
			break;

		case KeyEvent.VK_RIGHT:
			if(!Game.getInstance().isJumpLeft() && !Game.getInstance().isJumpRight()) {
				Game.getInstance().setActionInProgress(true);
				Game.getInstance().moveRight();
				panel.updateAnimation(myCharacterAnimations.WALK_RIGHT);
			}		
			break;

		case KeyEvent.VK_D : 
			if(!Game.getInstance().isActionInProgress()) {
				Game.getInstance().setActionInProgress(true);
				Game.getInstance().setJumpRight(true);
				panel.updateAnimation(myCharacterAnimations.JUMP_RIGHT);
			}
			break;
		
		case KeyEvent.VK_S :
			if(!Game.getInstance().isActionInProgress()) {
				Game.getInstance().setActionInProgress(true);
				Game.getInstance().setJumpLeft(true);
				panel.updateAnimation(myCharacterAnimations.JUMP_LEFT);
			}
			break;
			
			
		case KeyEvent.VK_A:
			if(!Game.getInstance().isJumpLeft() && !Game.getInstance().isJumpRight() && !Game.getInstance().isActionInProgress()) {
				Game.getInstance().setActionInProgress(true);
				if(myCharacterAnimations.isRight) {
					panel.updateAnimation(myCharacterAnimations.ATTACK_RIGHT);
					Game.getInstance().myCharAttackRight();
				}
				else {
					panel.updateAnimation(myCharacterAnimations.ATTACK_LEFT);
					Game.getInstance().myCharAttackLeft();
				}
			}
			break;
			
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;

		default:
			if(myCharacterAnimations.isRight) {
				panel.updateAnimation(myCharacterAnimations.IDLE_RIGHT);
			}
			else {
				panel.updateAnimation(myCharacterAnimations.IDLE_LEFT);
			}
			break;
		}
	}
	
	public void update() {
		panel.update();
	}

}