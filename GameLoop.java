package application;

import application.controller.AnimationController;

public class GameLoop extends Thread{

	public AnimationController controller;
	public GameLoop(AnimationController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				controller.update();
				Thread.sleep(60);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
