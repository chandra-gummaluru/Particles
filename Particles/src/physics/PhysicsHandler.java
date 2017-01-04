package physics;

import java.util.ArrayList;

import loop.Tickable;
import math.Vector;

public class PhysicsHandler implements Tickable {

	private ArrayList<Particle> particles;

	public Vector acclGravity;

	public Vector getGravitationalFieldStrength() {
		return this.acclGravity;
	}

	public void setGravitationalFieldStrength(Vector fGravity) {
		this.acclGravity = fGravity;
	}

	public PhysicsHandler(ArrayList<Particle> particles) {
		this.particles = particles;
		acclGravity = new Vector(0, 0);
	}

	public PhysicsHandler() {
		this.particles = new ArrayList<Particle>();
		acclGravity = new Vector(0, 0);
	}

	public void addParticle(Particle p) {
		this.particles.add(p);
	}

	@Override
	public void tick() {
		for (Particle p : particles) {
			p.addForce(acclGravity.scale(p.getMass()));
			p.tick();
		}
	}
}
