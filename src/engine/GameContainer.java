package engine;

public class GameContainer implements Runnable {

	private Thread thread;

	private boolean isRunning = false;

	private final double FPS_TARGET = 60.0;
	private final double RENDER_TIME_LIMIT = 1.0 / FPS_TARGET;

	private double previousTime;
	private double currentTime;
	private double timeDiference;
	private double accumulatedRepaintTime;

	double accumulatedFrameTime = 0;
	int frames = 0;

	public GameContainer() {

	}

	public synchronized void start() {
		thread = new Thread(this);
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
		if(currentTime == 0) {
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
			e.printStackTrace();
		}
	}

	private void updateGameState() {
		if (accumulatedFrameTime >= 1.0) {
			System.out.println("FPS: " + frames);
			accumulatedFrameTime = 0;
			frames = 0;
		}
	}

	private void renderGameCanvas() {
		frames++;
	}


}
