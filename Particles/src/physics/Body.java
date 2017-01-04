package physics;

import java.awt.Color;
import java.util.ArrayList;

import graphics.Drawable;
import loop.Tickable;
import math.Point;
import math.Vector;

public abstract class Body implements Tickable, Drawable {

	Color c;

	// geometric properties
	private Point pos;

	public Point getPos() {
		return this.pos;
	}

	Vector loX;
	Vector loY;

	public abstract void translate(double dx, double dy);

	// kinematic properties
	private Vector vel, accl;

	public void setVel(Vector vel) {
		this.vel = vel;
	}

	public Vector getVel() {
		return this.vel;
	}

	public void setAccl(Vector accl) {
		this.accl = accl;
	}

	public Vector getAccl() {
		return this.accl;
	}

	// dynamic properties
	private ArrayList<Vector> forces;

	public void addForce(Vector f) {
		forces.add(f);
	}

	// physical properties
	private double mass;
	private double invMass;

	private double elasticity;

	public double getMass() {
		return this.mass;
	}

	public double getInvMass() {
		return this.invMass;
	}

	public double getElasticity() {
		return this.elasticity;
	}

	public Body(Point pos, double mass, double elasticity) {
		c = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));

		this.pos = pos;

		this.loX = getProjection(new Vector(0, 1));
		this.loY = getProjection(new Vector(1, 0));

		this.vel = new Vector(0, 0);
		this.accl = new Vector(0, 0);

		this.forces = new ArrayList<Vector>();

		this.mass = mass;
		this.invMass = 1 / mass;

		this.elasticity = elasticity;
	}

	public void tick() {

		// get resistance
		double airDensity = 0.01;

		Vector fDrag = vel.multiply(loX).scale(-airDensity * mass);
		forces.add(fDrag);

		// determine the net force.
		Vector fNet = new Vector(0, 0);

		for (Vector f : forces) {
			fNet.translate(f);
		}

		forces.clear();

		// calculate the acceleration. F = ma.
		accl = fNet.scale(1 / mass);

		// apply the acceleration to the velocity.
		vel.translate(accl);

		// apply the velocity to the position.
		pos.translate(vel.getX(), vel.getY());
		translate(vel.getX(), vel.getY());

		if (getPos().getY() > 830) {
			vel.set(vel.scale(-elasticity));
			getPos().set(new Point(getPos().getX(), 830));
		}
	}

	public abstract Vector getProjection(Vector v);

}
