package application.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import application.Main;
import application.Settings;
import application.view.MenuPage;
import application.view.SettingsPage;

public class SettingsPageController implements MouseListener, KeyListener {

	private SettingsPage settings = new SettingsPage();
	
	public SettingsPageController(SettingsPage p) {
		settings = p;
		settings.getGoBack().addMouseListener(this);
		settings.addKeyListener(this);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == settings.getGoBack()) {
			if(Settings.audio) {
				MenuPageController.click.start();
			}
			Main.start.setVisible(true);
			MenuPageController.settingsFrame.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == settings.getGoBack()) {
			if(Settings.audio) {
				MenuPageController.mouseEntered.start();
			}
			settings.getGoBack().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/settings/backMouseEntered.jpg/").getPath()));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == settings.getGoBack()) {
			settings.getGoBack().setIcon(new ImageIcon(getClass().getResource("/application/resources/menu/settings/backDefault.jpg/").getPath()));
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
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
