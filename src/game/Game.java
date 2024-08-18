package game;

import java.awt.event.MouseEvent;

import engine.AbstractGame;
import engine.GameContainer;
import engine.gfx.Image;

public class Game extends AbstractGame {
	
	private Image hero;
	private int x = 20;
	private int y = 20;
	
	public Game() {
		hero = new Image("/img/hero.png");
	}

	@Override
	public void update(GameContainer gc) {
		if(gc.getInput().isButtonPressing(MouseEvent.BUTTON1)){
			x += -1;
		}
		if(gc.getInput().isButtonPressing(MouseEvent.BUTTON3)){
			x += 1;
		}
	}

	@Override
	public void render(GameContainer gc) {
		gc.getRenderer().drawImage(hero, x, y);
		
	}
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Game());
		gc.start();
	}
}
