package engine;

public class GameLoop implements Runnable {

	private boolean isRunning;

	private double previousTime;
	private double currentTime;
	private double timeDiference;
	private double accumulatedRenderTime;

	private double accumulatedFrameTime;
	private int frames;

	GameContainer gameContainer;

	public GameLoop(GameContainer gc) {
		gameContainer = gc;
	}

	@Override
	public void run() {
		isRunning = true;

		while (isRunning) {
			calculatePassedTime();
			updateGameState();

			if (isTimeToRender()) {
				renderGameCanvas();
			} else {
				waitOneMillisecond();
			}
		}
	}

	private void calculatePassedTime() {
		if (previousTime == 0) {
			previousTime = getTimeInSecWithNanoPrecision();
		}

		currentTime = getTimeInSecWithNanoPrecision();
		timeDiference = currentTime - previousTime;
		previousTime = currentTime;

		accumulatedRenderTime += timeDiference;
		accumulatedFrameTime += timeDiference;
	}

	private double getTimeInSecWithNanoPrecision() {
		return System.nanoTime() / 1_000_000_000.0;
	}

	private boolean isTimeToRender() {
		if (accumulatedRenderTime >= gameContainer.getDeltaTime()) {
			accumulatedRenderTime -= gameContainer.getDeltaTime();
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
		gameContainer.getGame().update(gameContainer);
		gameContainer.getInput().update();
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
		gameContainer.getRenderer().clear();
		gameContainer.getGame().render(gameContainer);
		gameContainer.getWindow().update();
		frames++;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
