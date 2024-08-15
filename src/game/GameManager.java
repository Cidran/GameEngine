package game;

import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;

public class GameManager extends AbstractGame {
	
	
	public GameManager() {
		
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isButtonPressed(MouseEvent.BUTTON1)){
			System.out.println("Teste");
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
		
	}
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}
}
