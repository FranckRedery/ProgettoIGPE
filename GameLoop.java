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
		//long avvio = System.currentTimeMillis(); controllo del tempo , non dovrebbe servire se creo un altro thread
		while(true) {
			try {
				controller.update();
				//System.out.println(System.currentTimeMillis()-avvio);
				Thread.sleep(60);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}
