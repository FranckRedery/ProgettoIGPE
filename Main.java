package application;



import javax.swing.JFrame;

import application.controller.MenuPageController;
import application.view.Audio;
import application.view.MenuPage;

public class Main {
	
	public static JFrame start = new JFrame();
	public static Audio menuAudio = new Audio("menuAudio.wav");

	public static void main(String[] args) {

			MenuPage menu = new MenuPage();
			MenuPageController menuController = new MenuPageController(menu);
			
			menu.addMouseListener(menuController);
			menu.getPlay().addMouseListener(menuController);
			menu.getSettings().addMouseListener(menuController);
			menu.getQuit().addMouseListener(menuController);
			menu.addKeyListener(menuController);
			menu.setFocusable(true);
			
			
			start.setSize(Settings.windowSize,Settings.windowSize);
			start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			start.setLocationRelativeTo(null);
			start.setUndecorated(true);
			start.add(menu);
			start.setVisible(true);
			menuAudio.reduceVolume();
			menuAudio.loop();
			
		}
	}