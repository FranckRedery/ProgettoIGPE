package application.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import application.Settings;
import application.model.MyCharacter;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private myCharacterAnimations animations = new myCharacterAnimations(); 
	

	public GraphicPanel() {}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		g.drawImage(animations.getCurrentImage(), MyCharacter.x, MyCharacter.y, Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
	}
	
	public void updateAnimation(int type) {
		animations.changeAnimation(type);
	}
	
	public void update() {
		animations.update();
		repaint();
	}
}