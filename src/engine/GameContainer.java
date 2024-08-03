package engine;

public class GameContainer{

	private Thread thread;
	private GameLoop gameLoop;
	private Window window;
	private Renderer renderer;
	private Input input;

	private final double FPS_TARGET = 60.0;
	private final double RENDER_TIME_LIMIT = 1.0 / FPS_TARGET;

	private String title = "GameEngine";
	private int width = 320;
	private int height = 240;
	private float scale = 2.4f;

	public GameContainer() {
	}

	public synchronized void start() {
		initializeComponents();
		thread.run();
	}

	public synchronized void stop() {
		gameLoop.setRunning(false);
	}

	private void initializeComponents() {
		gameLoop = new GameLoop(this);
		window = new Window(this);
		renderer = new Renderer(this);
		input = new Input(this);
		
		thread = new Thread(gameLoop);
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Window getWindow() {
		return window;
	}

	public double getRENDER_TIME_LIMIT() {
		return RENDER_TIME_LIMIT;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public Input getInput() {
		return input;
	}
}
