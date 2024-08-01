package engine;

public class GameContainer implements Runnable {

	private Thread thread;
	private Window window;
	private Renderer renderer;

	private boolean isRunning = false;

	private final double FPS_TARGET = 60.0;
	private final double RENDER_TIME_LIMIT = 1.0 / FPS_TARGET;

	private double previousTime;
	private double currentTime;
	private double timeDiference;
	private double accumulatedRepaintTime;

	private double accumulatedFrameTime;
	private int frames;

	private String title = "GameEngine";
	private int width = 320;
	private int height = 240;
	private float scale = 2.4f;

	public GameContainer() {

	}

	public synchronized void start() {
		thread = new Thread(this);
		window = new Window(this);
		renderer = new Renderer(this);

		thread.run();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		isRunning = true;

		while (isRunning) {
			calculateTimePass();
			updateGameState();

			if (isTimeToRender()) {
				renderGameCanvas();
			} else {
				waitOneMillisecond();
			}
		}
	}

	private void calculateTimePass() {
		if (currentTime == 0) {
			previousTime = getCurrentTimeInSecondsWithNanoPrecision();
		}

		currentTime = getCurrentTimeInSecondsWithNanoPrecision();
		timeDiference = currentTime - previousTime;
		previousTime = currentTime;

		accumulatedRepaintTime += timeDiference;
		accumulatedFrameTime += timeDiference;
	}

	private double getCurrentTimeInSecondsWithNanoPrecision() {
		return System.nanoTime() / 1_000_000_000.0;
	}

	private boolean isTimeToRender() {
		if (accumulatedRepaintTime >= RENDER_TIME_LIMIT) {
			accumulatedRepaintTime -= RENDER_TIME_LIMIT;
			return true;
		} else {
			return false;
		}
	}

	private void waitOneMillisecond() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private void updateGameState() {
		printFpsOnConsole();
	}

	private void printFpsOnConsole() {
		if (accumulatedFrameTime >= 1.0) {
			System.out.println("FPS: " + frames);
			accumulatedFrameTime = 0;
			frames = 0;
		}
	}

	private void renderGameCanvas() {
		renderer.clear();
		window.update();
		frames++;
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
}
