package application.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import application.GameLoop;
import application.Main;
import application.Settings;
import application.resources.audio.Audio;
import application.view.GraphicPanel;
import application.view.MenuPage;
import application.view.SettingsPage;

public class MenuPageController implements MouseListener {

	private MenuPage menu = new MenuPage();
	public static JFrame settingsFrame;
	private Audio gameBackgroundAudio = new Audio("gameBackgroundAudio.wav");
	public static Audio click = new Audio("click.wav");
	public static Audio mouseEntered = new Audio("mouseEntered.wav");
	
	public MenuPageController(MenuPage p) {
		menu = p;
		menu.getPlay().addMouseListener(this);
		menu.getSettings().addMouseListener(this);
		menu.getQuit().addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == menu.getPlay()) {
			click.start();
			Main.menuAudio.stop();
			Main.start.dispose();
			JFrame f = new JFrame("Gioco");
			f.setSize(Settings.WINDOW_SIZE,Settings.WINDOW_SIZE);		
			GraphicPanel gp = new GraphicPanel();
			AnimationController controller = new AnimationController(gp);
			gp.addKeyListener(controller);
			gp.setFocusable(true);
			f.add(gp);
			GameLoop gameLoop = new GameLoop(controller);
			gameLoop.start();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			f.setUndecorated(true);
			f.setVisible(true);
			gameBackgroundAudio.reduceVolume();
			gameBackgroundAudio.reduceVolume();
			gameBackgroundAudio.loop();
		}
		else if(e.getSource() == menu.getSettings()) {
			click.start();
			Main.start.setVisible(false);
			settingsFrame = new JFrame("Settings");
			
			SettingsPage settingsPage = new SettingsPage();
			SettingsPageController control = new SettingsPageController(settingsPage);
			settingsPage.addMouseListener(control);
			settingsPage.setFocusable(true);
			
			settingsFrame.add(settingsPage);
			settingsFrame.setSize(Settings.WINDOW_SIZE,Settings.WINDOW_SIZE);
			settingsFrame.setLocationRelativeTo(null);
			settingsFrame.setUndecorated(true);
			settingsFrame.setVisible(true);
			
			
		}
		else if(e.getSource() == menu.getQuit()) {
			click.start();
			System.exit(0);
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == menu.getPlay()) {
			mouseEntered.restart();
			menu.getPlay().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/playMouseEntered.jpg/").getPath()));
		}
		else if(e.getSource() == menu.getSettings()) {
			mouseEntered.restart();
			menu.getSettings().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/settingsMouseEntered.jpg/").getPath()));
		}
		else if(e.getSource() == menu.getQuit()) {
			mouseEntered.restart();
			menu.getQuit().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/quitMouseEntered.jpg/").getPath()));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == menu.getPlay()) {
			menu.getPlay().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/playDefault.jpg/").getPath()));
		}
		else if(e.getSource() == menu.getSettings()) {
			menu.getSettings().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/settingsDefault.jpg/").getPath()));
		}
		else if(e.getSource() == menu.getQuit()) {
			menu.getQuit().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/quitDefault.jpg/").getPath()));
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
