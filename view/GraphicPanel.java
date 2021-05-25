package application.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.Settings;
import application.model.Game;
import application.model.MyCharacter;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private myCharacterAnimations animations = new myCharacterAnimations();
	private Image heart;
	private Image floor;
	

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
		
		try {
			floor = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/floor.jpg"));
			heart = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/heart.png"));
		} catch (IOException e) {
			System.out.println("Can't load map images");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		
		//  NELLA VIEW METTERE LE VARIE IMG DX E SX DEL WALK DI OGNI NEMICO E POI QUI VENGONO CHIAMATE CON FOR E IF.. 
		// oppure va bene anche usare lo stesso approccio per il personaggio principale con la differenza che bisonga iterare in un for
		// tutti i nemici presenti e prendere le loro x e y e vedere quale img disegnare
		
		super.paintComponent(g);
		for(int i = 0 ; i<20;++i) {
			g.drawImage(floor,i*Settings.BLOCK_DIM/2,583,Settings.BLOCK_DIM/2,Settings.BLOCK_DIM/2,null);
		}
		for(int i = 0 ; i<Game.getInstance().getCharacter().life;++i) {
			g.drawImage(heart,50+i*20 ,50 ,20 ,20,null);
		}
		g.drawImage(animations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
		g.setColor(Color.RED);
		g.setFont(new Font("Fiendish",Font.PLAIN,20));
		g.drawString("Kills" , 450, 40);
		g.drawString("Health", 50, 40);
		
	}
	
	public void updateAnimation(int type) {
		animations.changeAnimation(type);
	}
	
	public void update() {
		animations.update();
		repaint();
	}
}

