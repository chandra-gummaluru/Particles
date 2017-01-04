package states;

import java.awt.Graphics2D;

import graphics.Drawable;
import input.InputHandling;
import input.InputHandler;
import loop.Tickable;

public class StateHandler implements Tickable, InputHandling, Drawable {

	private State currentState;

	public StateHandler() {
		currentState = new NullState();
	}

	public StateHandler(State defaultState) {
		currentState = defaultState;
	}

	public void setState(State newState) {
		currentState = newState;
	}

	@Override
	public void tick() {
		currentState.tick();
	}

	@Override
	public void handleInput(InputHandler m) {
		currentState.handleInput(m);
	}

	@Override
	public void draw(Graphics2D g) {
		currentState.draw(g);
	}

}
