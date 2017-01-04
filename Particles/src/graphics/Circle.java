package graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Point;
import math.Vector;

public class Circle extends Shape {

	// geometric properties
	private int radius;

	public int getRadius() {
		return this.radius;
	}

	@Override
	public void setPos(Point pos) {
		getPos().set(pos);
	}

	public Circle(Point pos, int radius, Color bgColor, Color fgColor) {
		super(pos, bgColor, fgColor);

		this.radius = radius;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(getBgClr());
		g.fillOval((int) getPos().getX() * getScale(), (int) getPos().getY() * getScale(), radius, radius);
	}

	public boolean isIntersecting(Circle c) {
		return this.radius + c.radius <= Vector.getPositionVector(c.getPos(), this.getPos()).getLength();
	}

	@Override
	public boolean isIntersecting(Polygon p) {
		return false;
	}

	@Override
	public double getProjection(Vector axis) {
		return 2 * this.radius;
	}
}
