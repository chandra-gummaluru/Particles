package states;

import java.awt.Graphics2D;

import input.InputHandler;

public class ErrorState extends State {
	
	@SuppressWarnings("unused")
	private Exception e;
	
	public ErrorState(Exception e) {
		super(null);
	}

	@Override
	public void tick() {
		// do nothing...
	}
	
	@Override
	public void handleInput(InputHandler m) {
		
	}

	@Override
	public void draw(Graphics2D g) {
	}

}
