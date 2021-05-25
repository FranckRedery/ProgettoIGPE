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
	private Image Background;

	public GraphicPanel() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font/Fiendish.ttf")));
		} catch (FontFormatException e) {
			System.out.println("Can't load font");
		} catch (IOException e) {
			System.out.println("Can't load font");
		}
		this.setBackground(Color.black);
		
		try {
			Background = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/gameBackground.jpg"));
			floor = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/floor.jpg"));
			heart = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/heart.png"));
		} catch (IOException e) {
			System.out.println("Can't load map images");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		// PER OGNI NEMICO O PERSONAGGIO DA INSERIRE VANNO CREATE DUE CLASSI MOLTO SIMILI A CHARACTERANIMATIONS E MYCHARACTERANIMATIONS
		
		super.paintComponent(g);
		g.drawImage(Background,0,0,1920,1080,null);
		for(int i = 0 ; i<20;++i) {
			for(int j = 0; j<3;++j) {
				g.drawImage(floor,i*Settings.BLOCK_DIM/2,883+j*50,Settings.BLOCK_DIM/2,Settings.BLOCK_DIM/2,null);
			}
		}
		for(int i = 0 ; i<Game.getInstance().getCharacter().life;++i) {
			g.drawImage(heart,50+i*20 ,50 ,20 ,20,null);
		}
		g.drawImage(animations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
		g.setColor(Color.RED);
		g.setFont(new Font("Fiendish",Font.PLAIN,20));
		g.drawString("Kills " + Game.getInstance().getKills() , 430, 40);
		g.drawString("Health", 50, 40);
		g.drawString("Round " + Game.getInstance().getRound(),840 , 40);
		
	}
	
	public void updateAnimation(int type) {
		animations.changeAnimation(type);
	}
	
	public void update() {
		animations.update();
		repaint();
	}
}

