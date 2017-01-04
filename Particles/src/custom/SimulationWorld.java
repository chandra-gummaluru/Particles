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

	private Particle[][] yuying;

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

		int[] size = new int[] { 54, 40, 54, 40, 50, 54, 40, 40, 40, 54, 54 };
		yuying = new Particle[11][];

		for (int i = 0; i < yuying.length; i++) {

			yuying[i] = new Particle[size[i] + 1];
			for (int j = 0; j <= size[i]; j++) {

				Point p = getPoint(i, j, size[i]);
				double elasticity = Math.random() * 0.6 + 0.2;

				yuying[i][j] = new Particle(p, Math.PI * 4, elasticity, 2);
				getPhysicsHandler().addParticle(yuying[i][j]);
			}
		}

		// text
		Font dflt = new Font("Arial", Font.PLAIN, 14);

		particleCount = new Text("Particle Count: " + n, new Point(9, 45), dflt, Color.WHITE);
		fgStrength = new Text(
				"Gravitational Field Strength: " + "x: " + getPhysicsHandler().getGravitationalFieldStrength().getX()
						+ " y: " + getPhysicsHandler().getGravitationalFieldStrength().getY(),
				new Point(9, 65), dflt, Color.WHITE);
	}

	public Point getPoint(int j, int x, int k) {
		int p = -600;
		int q = 280;
		switch (j) {
		case 0:
			return new Point(x + 43 + q, -0.1 * Math.pow(x - k / 2, 2) + 140 + p);
		case 1:
			return new Point(x + 120 + q, -0.1 * Math.pow(x - k / 2, 2) + 140 + p);
		case 2:
			return new Point(x + 183 + q, -0.1 * Math.pow(x - k / 2, 2) + 140 + p);
		case 3:
			return new Point(280 + q, -x + 140 + p);
		case 4:
			return new Point(x + 320 + q, 0.0001 * Math.pow(x - k / 2, 4) + 100 + p);
		case 5:
			return new Point(x + 400 + q, Math.sqrt((30 * 30) - Math.pow(x - k / 2, 2)) + 150 + p);
		case 6:
			return new Point(70 + q, x + 140 + p);
		case 7:
			return new Point(210 + q, x + 140 + p);
		case 8:
			return new Point(455 + q, -x + 160 + p);
		case 9:
			return new Point(x + 400 + q, -Math.sqrt((30 * 30) - Math.pow(x - k / 2, 2)) + 130 + p);
		case 10:
			return new Point(x + 400 + q, Math.sqrt((30 * 30) - Math.pow(x - k / 2, 2)) + 110 + p);
		default:
			return new Point(0, 0);
		}
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

		for (int i = 0; i < yuying.length; i++) {
			for (Particle q : yuying[i]) {
				q.draw(g);
			}
		}

		particleCount.draw(g);
		fgStrength.draw(g);
	}
}
