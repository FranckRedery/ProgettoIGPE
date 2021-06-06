package application.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import application.Main;
import application.view.MenuPage;
import application.view.SettingsPage;

public class SettingsPageController implements MouseListener {

	private SettingsPage settings = new SettingsPage();
	
	public SettingsPageController(SettingsPage p) {
		settings = p;
		settings.getGoBack().addMouseListener(this);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == settings.getGoBack()) {
			MenuPageController.click.start();
			Main.start.setVisible(true);
			MenuPageController.settingsFrame.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == settings.getGoBack()) {
			MenuPageController.mouseEntered.start();
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
