package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private GameContainer gameContainer;
	
	private final int NUM_KEYS = 256;
	private boolean[] keysCurrentState = new boolean[NUM_KEYS];
	private boolean[] keysPreviousState = new boolean[NUM_KEYS];
	
	private final int NUM_BUTTONS = 5;
	private boolean[] buttonsCurrentState = new boolean[NUM_BUTTONS];
	private boolean[] buttonsPreviousState = new boolean[NUM_BUTTONS];
	
	private int mouseX;
	private int mouseY;
	private int wheelScrolling;

	public Input(GameContainer gc) {
		gameContainer = gc;
		
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
	}
	
	public void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			keysPreviousState[i] = keysCurrentState[i];
		}
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttonsPreviousState[i] = buttonsCurrentState[i];
		}
	}
	
	public boolean isKeyPressing(int key) {
		return keysCurrentState[key];
	}
	
	public boolean isKeyPressed(int key) {
		return keysCurrentState[key] && !keysPreviousState[key];
	}
	
	public boolean isKeyReleased(int key) {
		return !keysCurrentState[key] && keysPreviousState[key];
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheelScrolling = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX() / gameContainer.getScale());
		mouseY = (int) (e.getY() / gameContainer.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() / gameContainer.getScale());
		mouseY = (int) (e.getY() / gameContainer.getScale());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysCurrentState[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysCurrentState[e.getKeyCode()] = false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public int getWheelScrolling() {
		return wheelScrolling;
	}
}
