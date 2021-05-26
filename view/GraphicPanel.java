package application.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.Settings;
import application.model.Game;
import application.model.MyCharacter;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private myCharacterAnimations myCharacteranimations = new myCharacterAnimations();
	public static ArrayList<smallDragonAnimations> smallDragonanimations = new ArrayList<smallDragonAnimations>();
	private Image heart;
	private Image floor;
	private Image Background;
	private int cont = 0;

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
		// DISEGNO IL PAVIMENTO
		for(int i = 0 ; i<20;++i) {
			for(int j = 0; j<3;++j) {
				g.drawImage(floor,i*Settings.BLOCK_DIM/2,883+j*50,Settings.BLOCK_DIM/2,Settings.BLOCK_DIM/2,null);
			}
		}
		// DISEGNO I CUORI
		for(int i = 0 ; i<Game.getInstance().getCharacter().life;++i) {
			g.drawImage(heart,50+i*20 ,50 ,20 ,20,null);
		}
		// DISEGNO IL MIO PERSONAGGIO
		g.drawImage(myCharacteranimations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
		
		resize();
		for(int i = 0; i<Game.smalldragons.size();++i) {
			g.drawImage(smallDragonanimations.get(i).getCurrentImage(),Game.smalldragons.get(i).x,Game.smalldragons.get(i).y,Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
		}
		
		g.setColor(Color.RED);
		g.setFont(new Font("Fiendish",Font.PLAIN,20));
		g.drawString("Kills " + Game.getInstance().getKills() , 430, 40);
		g.drawString("Health", 50, 40);
		g.drawString("Round " + Game.getInstance().getRound(),840 , 40);
		
	}
	
	public void updateAnimation(int type) {
		
		myCharacteranimations.changeAnimation(type);
		
		resize();
		for(int i = 0 ; i<Game.smalldragons.size();++i){
			if(Game.smalldragons.get(i).isRight) {
				smallDragonanimations.get(i).setCurrentAnimation(true);
			}
			else {
				smallDragonanimations.get(i).setCurrentAnimation(false);
			}
		}
	}
	
	public void update() {
		
		resize();
		
		for(int i = 0 ; i<Game.smalldragons.size();++i) {
			smallDragonanimations.get(i).update();
		}
		myCharacteranimations.update();
		repaint();
	}
	
	
	public void resize() {
		while(Game.smalldragons.size() > smallDragonanimations.size()) {
			smallDragonAnimations animazione = new smallDragonAnimations();
			// nemico che spawna a sx
			if(cont == 0) {
				animazione.setCurrentAnimation(true);
			}
			// nemico che spawna a dx
			else {
				animazione.setCurrentAnimation(false);
			}
			cont++;
			if(cont == 2) {
				cont = 0;
			}
			smallDragonanimations.add(animazione);
		}
	}
}

