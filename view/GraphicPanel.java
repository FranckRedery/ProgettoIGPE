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
import javax.swing.JPanel;

import application.Settings;
import application.model.Game;

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 4467360867545965264L;
	private static MyCharacterAnimations myCharacterAnimations = new MyCharacterAnimations();
	public static ArrayList<SmallDragonAnimations> smallDragonAnimations = new ArrayList<SmallDragonAnimations>();
	public static ArrayList<LizardAnimations> lizardAnimations = new ArrayList<LizardAnimations>();
	public static ArrayList<DemonAnimations> demonAnimations = new ArrayList<DemonAnimations>();
	public static ArrayList<MedusaAnimations> medusaAnimations = new ArrayList<MedusaAnimations>();
	public static DragonAnimations dragonAnimations = new DragonAnimations(true); 
	private Image heart;
	private Image floor;
	private Image background;
	private Image leftFireAttack;
	private Image rightFireAttack;
	private Image tombstone;
	private Image dragonLife;
	
	// contatori che servono per creare l'effetto "lampeggiante" del boss e del personaggio quando sono invulnerabili
	private int myCharInvulnerabilityFrames = 0;
	private int drakeInvulnerabilityFrames = 0;

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
			background = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/gameBackground.jpg"));
			floor = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/floor.jpg"));
			heart = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/heart.png"));
			leftFireAttack = ImageIO.read(getClass().getResourceAsStream("/application/resources/enemy/smallDragon/leftAttack/attackLeft0.png"));
			rightFireAttack = ImageIO.read(getClass().getResourceAsStream("/application/resources/enemy/smallDragon/rightAttack/attackRight0.png"));
			tombstone = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/tombstone.png"));
			dragonLife = ImageIO.read(getClass().getResourceAsStream("/application/resources/map/dragonLife.png"));
		} catch (IOException e) {
			System.out.println("Can't load map images");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//DISEGNO IL BACKGROUND DEL "CIELO" 
		g.drawImage(background,0,0,1920,1080,null);
		
		// DISEGNO IL PAVIMENTO
		for(int i = 0 ; i<20;++i) {
			for(int j = 0; j<3;++j) {
				g.drawImage(floor,i*50,883+j*50,50,50,null);
			}
		}
		
		// DISEGNO I CUORI CHE RAPPRESENTANO LA VITA DEL PERSONAGGIO
		for(int i = 0 ; i<Game.getInstance().getCharacter().life;++i) {
			g.drawImage(heart,50+i*20 ,50 ,20 ,20,null);
		}
		
		//disegno i cuori che spawnano (bonus prendibili)
		for(int i = 0; i<Game.getInstance().getHearts().size();++i) {
			g.drawImage(heart, Game.getInstance().getHearts().get(i).x, Game.getInstance().getHearts().get(i).y, 20,20,null);
		}
		
		if(!Game.getInstance().gameOver()) {
			// DISEGNO IL MIO PERSONAGGIO, NEL CASO è INVULNERABILE VOGLIO CHE VENGA VISUALIZZATO 
			// IN MODO "LAMPEGGIANTE" COSI' CHE L'UTENTE POSSA RENDERSI MAGGIORMENTE CONTO DI QUANTO DURI L'INVULNERABILITA'
			if(Game.getInstance().getCharacter().invulnerability) {
				if(myCharInvulnerabilityFrames % 2  == 0) {
					g.drawImage(myCharacterAnimations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.blockDim, Settings.blockDim, null);
				}
				myCharInvulnerabilityFrames++;
			}
			else {
				g.drawImage(myCharacterAnimations.getCurrentImage(), Game.getInstance().getCharacter().x,Game.getInstance().getCharacter().y , Settings.blockDim, Settings.blockDim, null);
			}
		}
		
		//DISEGNO LA LAPIDE E LE SCRITTE DI GAMEOVER QUANDO SI PERDE
		else if(Game.getInstance().gameOver()){
			g.setFont(new Font("Fiendish",Font.PLAIN,30));
			g.setColor(Color.RED);
			g.drawString("You died" , 390, 450);
			g.setFont(new Font("Fiendish",Font.PLAIN,20));
			g.drawString("Press R to start a new game or Esc to quit", 180,530);
			g.drawImage(tombstone,Game.getInstance().getCharacter().x,791,Settings.blockDim,Settings.blockDim,null);
		}
		
		// DISEGNO LA SCRITTA DELLA PAUSA QUANDO IL GIOCO VIENE MESSO IN PAUSA
		if(Game.getInstance().isPause()) {
			g.setFont(new Font("Fiendish",Font.PLAIN,30));
			g.setColor(Color.RED);
			g.drawString("Game paused" , 360, 450);
		}
		
		// DISEGNO I NEMICI PICCOLI DRAGHI
		for(int i = 0; i<Game.getInstance().getSmalldragons().size();++i) {
			g.drawImage(smallDragonAnimations.get(i).getCurrentImage(),Game.getInstance().getSmalldragons().get(i).x,Game.getInstance().getSmalldragons().get(i).y,Settings.blockDim, Settings.blockDim, null);
		}
		
		// DISEGNO I NEMICI LIZARD
		for(int i = 0; i<Game.getInstance().getLizards().size();++i) {
				g.drawImage(lizardAnimations.get(i).getCurrentImage(),Game.getInstance().getLizards().get(i).x,Game.getInstance().getLizards().get(i).y,Settings.blockDim*2,Settings.blockDim*2,null);
		}
		
		// DISEGNO I NEMICI DEMON
		for(int i = 0; i<Game.getInstance().getDemons().size();++i) {
				g.drawImage(demonAnimations.get(i).getCurrentImage(),Game.getInstance().getDemons().get(i).x,Game.getInstance().getDemons().get(i).y,Settings.blockDim*2,Settings.blockDim*2,null);
		}
		
		// DISEGNO I NEMICI MEDUSA
		for(int i = 0; i<Game.getInstance().getMedusa().size();++i) {
				g.drawImage(medusaAnimations.get(i).getCurrentImage(),Game.getInstance().getMedusa().get(i).x,Game.getInstance().getMedusa().get(i).y,Settings.blockDim,Settings.blockDim,null);
		}
		
		// DISEGNO IL BOSS DRAGON
		if(Game.getInstance().getRound() == 9) {
			for(int i = 0; i<Game.getInstance().getDragon().life;++i) {
				g.setFont(new Font("Fiendish",Font.PLAIN,20));
				g.setColor(Color.RED);
				g.drawString("Dragon", 840, 100);
				g.drawImage(dragonLife,780+i*37 ,90 ,70 ,70,null);
			}
			
			// NEL CASO è INVULNERABILE VOGLIO CHE VENGA VISUALIZZATO IN MODO "LAMPEGGIANTE"
			// COSI' CHE L'UTENTE POSSA RENDERSI MAGGIORMENTE CONTO DI QUANTO DURI L'INVULNERABILITA'
			if(Game.getInstance().getDragon().invulnerability) {
				if(drakeInvulnerabilityFrames % 2  == 0) {
					g.drawImage(dragonAnimations.getCurrentImage(), Game.getInstance().getDragon().x,  Game.getInstance().getDragon().y, 250, 250 ,null);
				}
				drakeInvulnerabilityFrames++;
			}
			else{
				g.drawImage(dragonAnimations.getCurrentImage(), Game.getInstance().getDragon().x,  Game.getInstance().getDragon().y, 250, 250 ,null);
			}
			
			//IN CASO VIENE SCONFITTO IL DRAGO SI VINCE 
			if(Game.getInstance().getDragon().life == 0) {
				g.setFont(new Font("Fiendish",Font.PLAIN,30));
				g.setColor(Color.RED);
				g.drawString("You Won", 390, 450);
				g.setFont(new Font("Fiendish",Font.PLAIN,20));
				g.drawString("Press R to start a new game or Esc to quit", 180,530);
			}
		}
		
		//DISEGNO GLI ATTACCHI DEI PICCOLI DRAGHI
		for(int i =0 ; i<Game.getInstance().getFireAttack().size();++i) {
			if(Game.getInstance().getFireAttack().get(i).isRight) {
				g.drawImage(rightFireAttack,Game.getInstance().getFireAttack().get(i).x,Game.getInstance().getFireAttack().get(i).y,Settings.blockDim, Settings.blockDim, null);
			}
			else {
				g.drawImage(leftFireAttack,Game.getInstance().getFireAttack().get(i).x,Game.getInstance().getFireAttack().get(i).y,Settings.blockDim, Settings.blockDim, null);
			}
		}
		
		//SCRITTE CHE RAPPRESENTANO LE UCCISIONI , LA VITA ED IL ROUND ATTUALE
		g.setFont(new Font("Fiendish",Font.PLAIN,20));
		g.setColor(Color.RED);
		g.drawString( Game.getInstance().getKills() + " Kills", 430, 40);
		g.drawString("Health", 50, 40);
		g.drawString("Round " + Game.getInstance().getRound(),840 , 40);
		
	}
	
	public static void updateAnimation(int type) {
		
		myCharacterAnimations.changeAnimation(type);
		for(int i = 0 ; i<Game.getInstance().getSmalldragons().size();++i){
			if(Game.getInstance().getSmalldragons().get(i).isRight) {
				smallDragonAnimations.get(i).setCurrentAnimation(true);
			}
			else {
				smallDragonAnimations.get(i).setCurrentAnimation(false);
			}
		}
		for(int i = 0; i<Game.getInstance().getLizards().size();++i) {
			if(Game.getInstance().getLizards().get(i).isRight) {
				lizardAnimations.get(i).setCurrentAnimation(true);
			}
			else {
				lizardAnimations.get(i).setCurrentAnimation(false);
			}
		}
		for(int i = 0; i<Game.getInstance().getDemons().size();++i) {
			if(Game.getInstance().getDemons().get(i).isRight) {
				demonAnimations.get(i).setCurrentAnimation(true);
			}
			else {
				demonAnimations.get(i).setCurrentAnimation(false);
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
		if(Game.getInstance().getRound() == 9) {
			if(Game.getInstance().getDragon().isRight) {
				dragonAnimations.setCurrentAnimation(true);
			}
			else {
				dragonAnimations.setCurrentAnimation(false);
			}
		}
	}
	
	public void update() {
		for(int i = 0 ; i<smallDragonAnimations.size();++i) {
			smallDragonAnimations.get(i).update();
		}
		for(int i = 0; i<lizardAnimations.size();++i) {
			lizardAnimations.get(i).update();
		}
		for(int i = 0; i<demonAnimations.size();++i) {
			demonAnimations.get(i).update();
		}
		for(int i = 0; i<medusaAnimations.size();++i) {
			medusaAnimations.get(i).update();
		}
		myCharacterAnimations.update();
		if(Game.getInstance().getRound() == 9) {
			dragonAnimations.update();
		}
		repaint();
	}
}
