package custom;

import java.awt.Dimension;

import app.Application;
import app.Window.Scalability;

public class Main {

	public static void main(String[] args) {
		Application app = new Application("Particle Simulator - Alpha", new Dimension(540, 420), Scalability.MAXIMIZED,
				30);
		app.getWindow().setVisible(true);

		app.getStateManager().setState(new SimulationWorld());
	}
}
