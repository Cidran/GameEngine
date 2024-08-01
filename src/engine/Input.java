package engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener {

	private final int NUM_BUTTONS = 5;
	private boolean[] buttonsCurrentState = new boolean[NUM_BUTTONS];
	private boolean[] buttonsPreviousState = new boolean[NUM_BUTTONS];

	public Input(GameContainer gc) {
		gc.getWindow().getCanvas().addMouseListener(this);
	}
	
	public void update() {
		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttonsPreviousState[i] = buttonsCurrentState[i];
		}
	}
	
	public boolean isButtonPressing(int button) {
		return buttonsCurrentState[button];
	}
	
	public boolean isButtonPressed(int button) {
		return buttonsCurrentState[button] && !buttonsPreviousState[button];
	}
	
	public boolean isButtonReleased(int button) {
		return !buttonsCurrentState[button] && buttonsPreviousState[button];
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonsCurrentState[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttonsCurrentState[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
