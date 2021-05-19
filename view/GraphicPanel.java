package application.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import application.model.MyCharacter;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private myCharacterAnimations animations = new myCharacterAnimations(); 

	public GraphicPanel() {}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		g.drawImage(animations.getCurrentImage(), MyCharacter.x, MyCharacter.y, 200, 200, null);
	}
	
	public void updateAnimation(int type) {
		animations.changeAnimation(type);
	}
	
	public void update() {
		System.out.println("update di animations");
		animations.update();
		repaint();
	}
}