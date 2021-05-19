package application.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import application.view.myCharacterAnimations;
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
			myCharacterAnimations.isRight = false;
			panel.updateAnimation(myCharacterAnimations.WALK_LEFT);
			break;

		case KeyEvent.VK_RIGHT:
			myCharacterAnimations.isRight = true;
			panel.updateAnimation(myCharacterAnimations.WALK_RIGHT);
			break;

		/*case KeyEvent.VK_UP:
			panel.updateAnimation(myCharacterAnimations.JUMP);		// da implementare in futuro
			break;

		case KeyEvent.VK_DOWN:
			panel.updateAnimation(myCharacterAnimations.RUN);
			break;*/

		case KeyEvent.VK_SPACE:
			if(myCharacterAnimations.isRight) {
				panel.updateAnimation(myCharacterAnimations.ATTACK_RIGHT);
			}
			else {
				panel.updateAnimation(myCharacterAnimations.ATTACK_LEFT);
			}
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