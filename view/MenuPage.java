package application.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.Settings;

public class MenuPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton play;
	private JButton settings;
	private JButton quit;
	private BufferedImage background;
	private Icon playButton;
	private Icon settingsButton;
	private Icon quitButton;

	
	public MenuPage(){

		
		try {
			background = ImageIO.read(new File(getClass().getResource("/application/resources/menu/Menubackground.jpg/").getPath()));
		} catch (IOException e) {
			System.out.println("Can't load menu images");
		}
		
		playButton = new ImageIcon(getClass().getResource("/application/resources/menu/playDefault.jpg/").getPath());
		settingsButton = new ImageIcon(getClass().getResource("/application/resources/menu/settingsDefault.jpg/").getPath());
		quitButton = new ImageIcon(getClass().getResource("/application/resources/menu/quitDefault.jpg/").getPath());
		play = new JButton(playButton);
		play.setBounds(550,200,400,100);
		settings = new JButton(settingsButton);
		settings.setBounds(550,400,400,100);
		quit = new JButton(quitButton);
		quit.setBounds(550,600,400,100);
		
		this.setLayout(null);
		this.add(play);
		this.add(settings);
		this.add(quit);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH),0,0,this);
	}

	
	public JButton getPlay() {
		return play;
	}

	public void setPlay(JButton play) {
		this.play = play;
	}

	public JButton getSettings() {
		return settings;
	}

	public void setSettings(JButton settings) {
		this.settings = settings;
	}

	public JButton getQuit() {
		return quit;
	}

	public void setQuit(JButton quit) {
		this.quit = quit;
	}
	
}
