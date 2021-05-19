package application;

import javax.swing.JFrame;

import application.controller.AnimationController;
import application.view.GraphicPanel;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Prova");
		f.setSize(Settings.WINDOW_SIZE,Settings.WINDOW_SIZE);		
		GraphicPanel gp = new GraphicPanel();
		AnimationController controller = new AnimationController(gp);
		gp.addKeyListener(controller);
		gp.setFocusable(true);
		f.add(gp);
		GameLoop gameLoop = new GameLoop(controller);
		gameLoop.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);		
	}
}