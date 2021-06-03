package application.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.Settings;
import application.model.Game;
import application.model.MyCharacter;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private static myCharacterAnimations myCharacteranimations = new myCharacterAnimations();
	public static ArrayList<smallDragonAnimations> smallDragonanimations = new ArrayList<smallDragonAnimations>();
	public static ArrayList<lizardAnimations> lizardanimations = new ArrayList<lizardAnimations>();
	public static ArrayList<DemonAnimations> demonanimations = new ArrayList<DemonAnimations>();
	public static ArrayList<MedusaAnimations> medusaAnimations = new ArrayList<MedusaAnimations>();
	private Image heart;
	private Image floor;
	private Image Background;
	private Image leftFireAttack;
	private Image rightFireAttack;
	private Image tombstone;
	private int contInvulnerabilityFrames = 0;

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
			leftFireAttack = ImageIO.read(getClass().getResourceAsStream("/application/resources/enemy/smallDragon/leftAttack/attackLeft0.png"));
			rightFireAttack = ImageIO.read(getClass().getResourceAsStream("/application/resources/enemy/smallDragon/rightAttack/attackRight0.png"));
			tombstone = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/tombstone.png"));
		} catch (IOException e) {
			System.out.println("Can't load map images");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		// PER OGNI NEMICO O PERSONAGGIO DA INSERIRE VANNO CREATE DUE CLASSI MOLTO SIMILI A CHARACTERANIMATIONS E MYCHARACTERANIMATIONS
		
		super.paintComponent(g);
		//DISEGNO IL BACKGROUND DEL "CIELO" 
		g.drawImage(Background,0,0,1920,1080,null);
		
		// DISEGNO IL PAVIMENTO
		for(int i = 0 ; i<20;++i) {
			for(int j = 0; j<3;++j) {
				g.drawImage(floor,i*50,883+j*50,50,50,null);
			}
		}
		// DISEGNO I CUORI
		for(int i = 0 ; i<Game.getInstance().getCharacter().life;++i) {
			g.drawImage(heart,50+i*20 ,50 ,20 ,20,null);
		}
		
		//disegno i cuori che spawnano (prendibili)
		for(int i = 0; i<Game.getInstance().getHearts().size();++i) {
			g.drawImage(heart, Game.getInstance().getHearts().get(i).x, Game.getInstance().getHearts().get(i).y, 20,20,null);
			//g.drawRect(Game.getInstance().getHearts().get(i).x, Game.getInstance().getHearts().get(i).y, 20,20);
		}
		
		if(!Game.getInstance().gameOver()) {
			// DISEGNO IL MIO PERSONAGGIO, NEL CASO è INVULNERABILE VOGLIO CHE VENGA VISUALIZZATO 
			// IN MODO "LAMPEGGIANTE" COSì CHE L'UTENTE POSSA RENDERSI MAGGIORMENTE CONTO DI QUANTO DURI QUESTO EFFETTO
			if(Game.getInstance().getCharacter().invulnerability) {
				if(contInvulnerabilityFrames % 2  == 0) {
					g.drawImage(myCharacteranimations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
					//g.drawRect(Game.getInstance().getCharacter().x+37,Game.getInstance().getCharacter().y+23, 20, 55);
					//g.drawRect(Game.getInstance().getCharacter().x+40,Game.getInstance().getCharacter().y+25, 65, 60);
					//g.drawRect(Game.getInstance().getCharacter().x-5,Game.getInstance().getCharacter().y+25, 65, 60);
				}
				contInvulnerabilityFrames++;
			}
			else {
				g.drawImage(myCharacteranimations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
				//g.drawRect(Game.getInstance().getCharacter().x+37,Game.getInstance().getCharacter().y+23, 20, 55);
				//g.drawRect(Game.getInstance().getCharacter().x-5,Game.getInstance().getCharacter().y+25, 65, 60);
				//g.drawRect(Game.getInstance().getCharacter().x+40,Game.getInstance().getCharacter().y+25, 65, 60);
			}
		}
		
		//DISEGNO LA LAPIDE E LE SCRITTE DI GAMEOVER
		else if(Game.getInstance().gameOver()){
			g.setFont(new Font("Fiendish",Font.PLAIN,30));
			g.setColor(Color.RED);
			g.drawString("You died" , 390, 450);
			g.setFont(new Font("Fiendish",Font.PLAIN,20));
			g.drawString("Press R to start a new game or Esc to quit", 180,530);
			g.drawImage(tombstone,Game.getInstance().getCharacter().x,791,Settings.BLOCK_DIM,Settings.BLOCK_DIM,null);
		}
		
		if(Game.getInstance().isPause()) {
			g.setFont(new Font("Fiendish",Font.PLAIN,30));
			g.setColor(Color.RED);
			g.drawString("Game paused" , 360, 450);
		}
		
		// DISEGNO I PRIMI NEMICI (PICCOLI DRAGHI)
		for(int i = 0; i<Game.getInstance().getSmalldragons().size();++i) {
			g.drawImage(smallDragonanimations.get(i).getCurrentImage(),Game.getInstance().getSmalldragons().get(i).x,Game.getInstance().getSmalldragons().get(i).y,Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
		}
		
		// DISEGNO I NEMICI LIZARD
		for(int i = 0; i<Game.getInstance().getLizards().size();++i) {
				g.drawImage(lizardanimations.get(i).getCurrentImage(),Game.getInstance().getLizards().get(i).x,Game.getInstance().getLizards().get(i).y,Settings.BLOCK_DIM*2,Settings.BLOCK_DIM*2,null);
		}
		
		// DISEGNO I NEMICI DEMON
		for(int i = 0; i<Game.getInstance().getDemons().size();++i) {
				g.drawImage(demonanimations.get(i).getCurrentImage(),Game.getInstance().getDemons().get(i).x,Game.getInstance().getDemons().get(i).y,Settings.BLOCK_DIM*2,Settings.BLOCK_DIM*2,null);
		}
		
		// DISEGNO I NEMICI MEDUSA
		for(int i = 0; i<Game.getInstance().getMedusa().size();++i) {
				g.drawImage(medusaAnimations.get(i).getCurrentImage(),Game.getInstance().getMedusa().get(i).x,Game.getInstance().getMedusa().get(i).y,Settings.BLOCK_DIM,Settings.BLOCK_DIM,null);
		}
		
		//DISEGNO GLI ATTACCHI DEI PICCOLI DRAGHI
		for(int i =0 ; i<Game.getInstance().getFireAttack().size();++i) {
			if(Game.getInstance().getFireAttack().get(i).isRight) {
				g.drawImage(rightFireAttack,Game.getInstance().getFireAttack().get(i).x,Game.getInstance().getFireAttack().get(i).y,Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
				//g.drawRect(Game.getInstance().getFireAttack().get(i).x+45,Game.getInstance().getFireAttack().get(i).y+50, 30, 20);
			}
			else {
				g.drawImage(leftFireAttack,Game.getInstance().getFireAttack().get(i).x,Game.getInstance().getFireAttack().get(i).y,Settings.BLOCK_DIM, Settings.BLOCK_DIM, null);
				//g.drawRect(Game.getInstance().getFireAttack().get(i).x+30,Game.getInstance().getFireAttack().get(i).y+50, 30, 20);
			}
		}
		
		g.setFont(new Font("Fiendish",Font.PLAIN,20));
		g.setColor(Color.RED);
		g.drawString("Kills " + Game.getInstance().getKills() , 430, 40);
		g.drawString("Health", 50, 40);
		g.drawString("Round " + Game.getInstance().getRound(),840 , 40);
		
	}
	
	public static void updateAnimation(int type) {
		if(type != -1) {
			myCharacteranimations.changeAnimation(type);
		}
		for(int i = 0 ; i<Game.getInstance().getSmalldragons().size();++i){
			if(Game.getInstance().getSmalldragons().get(i).isRight) {
				smallDragonanimations.get(i).setCurrentAnimation(true);
			}
			else {
				smallDragonanimations.get(i).setCurrentAnimation(false);
			}
		}
		for(int i = 0; i<Game.getInstance().getLizards().size();++i) {
			if(Game.getInstance().getLizards().get(i).isRight) {
				lizardanimations.get(i).setCurrentAnimation(true);
			}
			else {
				lizardanimations.get(i).setCurrentAnimation(false);
			}
		}
		for(int i = 0; i<Game.getInstance().getDemons().size();++i) {
			if(Game.getInstance().getDemons().get(i).isRight) {
				demonanimations.get(i).setCurrentAnimation(true);
			}
			else {
				demonanimations.get(i).setCurrentAnimation(false);
			}
		}
		
		for(int i = 0; i<Game.getInstance().getMedusa().size();++i) {
			if(Game.getInstance().getMedusa().get(i).isRight) {
				medusaAnimations.get(i).setCurrentAnimation(true);
			}
			else {
				medusaAnimations.get(i).setCurrentAnimation(false);
			}
		}
	}
	
	public void update() {
		for(int i = 0 ; i<smallDragonanimations.size();++i) {
			smallDragonanimations.get(i).update();
		}
		for(int i = 0; i<lizardanimations.size();++i) {
			lizardanimations.get(i).update();
		}
		for(int i = 0; i<demonanimations.size();++i) {
			demonanimations.get(i).update();
		}
		for(int i = 0; i<medusaAnimations.size();++i) {
			medusaAnimations.get(i).update();
		}
		myCharacteranimations.update();
		repaint();
	}
}
