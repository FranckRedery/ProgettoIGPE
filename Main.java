package application;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.controller.AnimationController;
import application.controller.MenuPageController;
import application.model.Game;
import application.view.GraphicPanel;
import application.view.MenuPage;

public class Main {
	
	public static JFrame start = new JFrame();

	public static void main(String[] args) {

			MenuPage menu = new MenuPage();
			MenuPageController menucontroller = new MenuPageController(menu);
			menu.addMouseListener(menucontroller);
			menu.setFocusable(true);
			start.setSize(Settings.WINDOW_SIZE,Settings.WINDOW_SIZE);
			start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			start.setLocationRelativeTo(null);
			start.setUndecorated(true);
			start.add(menu);
			start.setVisible(true);
		}
		
	}