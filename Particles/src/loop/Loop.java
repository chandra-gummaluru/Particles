package loop;

public class Loop implements Runnable {

	/**
	 * The {@code Tickable} object that {@code this Loop} controls.
	 */
	private Tickable tickableObject;

	/**
	 * The {@code Thread} that {@code this Loop} runs on.
	 */
	private Thread thread;

	/**
	 * Indicates whether {@code this Loop} is currently running or not.
	 */
	private boolean running;

	/**
	 * The number of updates per second.
	 */
	private double FPS;

	/**
	 * The set point for the number of updates per second.
	 */
	private final int setpointFPS;

	/**
	 * The current frame number.
	 */
	private int currentFrameNumber;

	/**
	 * The maximum delay between each update (calculated using the target frame
	 * rate).
	 */
	private final long maxDelay;

	/**
	 * The definition for a second in terms of nanoseconds.
	 */
	private final long NS = (long) Math.pow(10, 9);

	/**
	 * The definition of a millisecond in terms of nanoseconds.
	 */
	private final long MS = (long) Math.pow(10, 6);

	public Loop(int targetFPS, Tickable tickableObject) {
		this.tickableObject = tickableObject;

		this.setpointFPS = targetFPS;
		this.maxDelay = (long) (NS / (double) targetFPS);

		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Pauses {@code this Loop}.
	 */
	public void pause() {
		running = false;
	}

	@Override
	public void run() {
		running = true;

		long lastCycleTime = 0;

		while (running) {
			long preUpdateTime = System.nanoTime();

			// tick the object
			tickableObject.tick();

			long postUpdateTime = System.nanoTime();

			long updateDuration = postUpdateTime - preUpdateTime;

			long delay = maxDelay - updateDuration;

			if (delay > 0) {
				int nanos = (int) (delay % MS);
				long millis = (long) ((delay - nanos) / (double) MS);

				try {
					Thread.sleep(millis, nanos);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			currentFrameNumber++;

			if (currentFrameNumber >= setpointFPS) {
				currentFrameNumber = 0;
				FPS = setpointFPS / ((System.nanoTime() - lastCycleTime) / (double) NS);
				lastCycleTime = System.nanoTime();
			}
		}

	}

	/**
	 * Indicates whether {@code this Loop} is currently running or not.
	 * 
	 * @return {@code true} if {@code this Loop} is running, {@code false}
	 *         otherwise.
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Gets the current frame rate.
	 * 
	 * @return the current frame rate in frames per second.
	 */
	public double getFPS() {
		return this.FPS;
	}

}
