package custom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import graphics.Text;
import input.InputHandler;
import math.Point;
import math.Vector;
import physics.Particle;
import states.State;

public class SimulationWorld extends State {

	private Particle[] particles;

	private Text particleCount;
	private Text fgStrength;

	public SimulationWorld() {
		super(null);

		// set the gravitational field strength
		getPhysicsHandler().setGravitationalFieldStrength(new Vector(0, 0.98));

		int n = 4000;
		particles = new Particle[n];

		for (int i = 0; i < n; i++) {
			Point pos = new Point((int) (Math.random() * 1100), (int) (-Math.random() * 1200) - 50);
			int r = (int) (Math.random() * 2) + 1;

			double elasticity = Math.random() * 0.6 + 0.2;

			Particle p = new Particle(pos, Math.PI * r * r, elasticity, r);
			particles[i] = p;
			getPhysicsHandler().addParticle(p);
		}

		// text
		Font dflt = new Font("Arial", Font.PLAIN, 14);

		particleCount = new Text("Particle Count: " + n, new Point(9, 45), dflt, Color.WHITE);
		fgStrength = new Text(
				"Gravitational Field Strength: " + "x: " + getPhysicsHandler().getGravitationalFieldStrength().getX()
						+ " y: " + getPhysicsHandler().getGravitationalFieldStrength().getY(),
				new Point(9, 65), dflt, Color.WHITE);
	}

	@Override
	public void tick() {
		getPhysicsHandler().tick();
		fgStrength.setText("Gravitational Field Strength: " + "x: "
				+ getPhysicsHandler().getGravitationalFieldStrength().getX() * 30 + " y: "
				+ getPhysicsHandler().getGravitationalFieldStrength().getY() * 30);
	}

	@Override
	public void handleInput(InputHandler m) {
		if (m.isPointerDown()) {
			getPhysicsHandler().setGravitationalFieldStrength(new Vector(0, -0.98));
		} else {
			getPhysicsHandler().setGravitationalFieldStrength(new Vector(0, 0.98));
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (Particle p : particles) {
			p.draw(g);
		}

		particleCount.draw(g);
		fgStrength.draw(g);
	}
}
