package application.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.GameLoop;
import application.Main;
import application.Settings;
import application.view.Audio;
import application.view.GraphicPanel;
import application.view.MenuPage;
import application.view.SettingsPage;

public class MenuPageController implements MouseListener, KeyListener {

	private MenuPage menu = new MenuPage();
	private JFrame gioco;
	public static JFrame settingsFrame;
	public static Audio gameBackgroundAudio = new Audio("gameBackgroundAudio.wav");
	public static Audio click = new Audio("click.wav");
	public static Audio mouseEntered = new Audio("mouseEntered.wav");
	
	public MenuPageController(MenuPage p) {
		menu = p;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == menu.getPlay()) {
			if(Settings.audio) {
				click.start();
			}
			Main.menuAudio.stop();
			Main.start.dispose();
			gioco = new JFrame("Gioco");
			gioco.setSize(Settings.windowSize,Settings.windowSize);		
			GraphicPanel gp = new GraphicPanel();
			AnimationController controller = new AnimationController(gp);
			gp.addKeyListener(controller);
			gp.setFocusable(true);
			gioco.add(gp);
			GameLoop gameLoop = new GameLoop(controller);
			gameLoop.start();
			gioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gioco.setLocationRelativeTo(null);
			gioco.setUndecorated(true);
			gioco.setVisible(true);
			gameBackgroundAudio.reduceVolume();
			gameBackgroundAudio.reduceVolume();
			gameBackgroundAudio.loop();
		}
		else if(e.getSource() == menu.getSettings()) {
			if(Settings.audio) {
				click.start();
			}
			Main.start.setVisible(false);
			settingsFrame = new JFrame("Settings");
			
			SettingsPage settingsPage = new SettingsPage();
			SettingsPageController control = new SettingsPageController(settingsPage);
			settingsPage.getGoBack().addMouseListener(control);
			settingsPage.addKeyListener(control);
			settingsPage.setFocusable(true);
			
			settingsFrame.add(settingsPage);
			settingsFrame.setSize(Settings.windowSize,Settings.windowSize);
			settingsFrame.setLocationRelativeTo(null);
			settingsFrame.setUndecorated(true);
			settingsFrame.setVisible(true);
			settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		}
		else if(e.getSource() == menu.getQuit()) {
			if(Settings.audio) {
				click.start();
			}
			
			int confirm = JOptionPane.showConfirmDialog(gioco, "Are you sure you want to quit ?", "",JOptionPane.YES_NO_OPTION);
			
			if(confirm == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == menu.getPlay()) {
			if(Settings.audio) {
				mouseEntered.restart();
			}
			menu.getPlay().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/playMouseEntered.jpg/").getPath()));
		}
		else if(e.getSource() == menu.getSettings()) {
			if(Settings.audio) {
				mouseEntered.restart();
			}
			menu.getSettings().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/settingsMouseEntered.jpg/").getPath()));
		}
		else if(e.getSource() == menu.getQuit()) {
			if(Settings.audio) {
				mouseEntered.restart();
			}
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
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_O) {
			if(Settings.audio) {
				Settings.audio = false;
				Main.menuAudio.stop();
			}
			else {
				Settings.audio = true;
				Main.menuAudio.loop();
			}
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
