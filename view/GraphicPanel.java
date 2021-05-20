package application.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import application.Settings;
import application.model.Game;
import application.model.MyCharacter;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private myCharacterAnimations animations = new myCharacterAnimations(); 
	

	public GraphicPanel() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font/Fiendish.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBackground(Color.black);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(animations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
		g.setColor(Color.RED);
		g.setFont(new Font("Fiendish",Font.PLAIN,20));
		g.drawString("Uccisi " , 200, 100);
		
	}
	
	public void updateAnimation(int type) {
		animations.changeAnimation(type);
	}
	
	public void update() {
		animations.update();
		repaint();
	}
}

