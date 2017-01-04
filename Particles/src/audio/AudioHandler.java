package audio;

import javax.sound.sampled.Clip;

public class AudioHandler {

	public void loop(Clip c, int n) {
		c.loop(n);
	}

	public void loop(Clip c) {
		c.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play(Clip c) {
		c.start();
	}

	public void pause(Clip c) {
		int f = c.getFramePosition();
		c.stop();
		c.setFramePosition(f);
	}

	public void stop(Clip c) {
		if (c.isRunning()) {
			c.stop();
			c.setFramePosition(0);
		}
	}
}
