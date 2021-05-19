package application.model;

public class Game {

	
	private static Game game = null;// CON QUESTA TECNICA SI DICE CHE LA CLASSE GAME è SINGLETON CIOè UNICA !
	
	private Game() {}
	
	public static Game getInstance() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}
	
	public void creamappa() {
		
	}
}
