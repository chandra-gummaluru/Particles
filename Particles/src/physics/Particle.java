package physics;

import java.awt.Graphics2D;

import math.Point;
import math.Vector;

public class Particle extends Body {

	private double radius;

	public double getRadius() {
		return this.radius;
	}

	public Particle(Point pos, double mass, double elasticity, double radius) {
		super(pos, mass, elasticity);

		this.radius = radius;
	}

	@Override
	public void translate(double dx, double dy) {
		getPos().translate(dx, dy);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.c);
		g.fillOval((int) getPos().getX(), (int) getPos().getY(), (int) radius, (int) radius);
	}

	@Override
	public Vector getProjection(Vector v) {
		return new Vector(2 * radius, 2 * radius).multiply(v.getUnitVector());
	}

	public boolean isCollidingWith(Particle p) {
		return this.radius + p.radius > Vector.getPositionVector(p.getPos(), this.getPos()).getLength();
	}
}
