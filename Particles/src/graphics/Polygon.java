package graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Point;
import math.Vector;

public class Polygon extends Shape {

	// geometric properties
	private Point[] points;

	public Point[] getPoints() {
		return this.points;
	}

	@Override
	public void setPos(Point pos) {
		Vector shift = Vector.getPositionVector(pos, getPos());

		getPos().translate(shift);
		for (Point p : points) {
			p.translate(shift);
		}
	}

	public Polygon(Point pos, Point[] points, Color bgClr, Color fgClr) {
		super(pos, bgClr, fgClr);

		this.points = points;
	}

	@Override
	public void draw(Graphics2D g) {
		int[] xpts = new int[points.length];
		int[] ypts = new int[points.length];

		for (int i = 0; i < points.length; i++) {
			xpts[i] = (int) points[i].getX();
			ypts[i] = (int) points[i].getY();
		}

		g.fillPolygon(xpts, ypts, points.length);
	}

	@Override
	public boolean isIntersecting(Polygon p) {
		return false;
	}

	@Override
	public boolean isIntersecting(Circle c) {
		return false;
	}

	public double getProjection(Vector v) {
		double[] projections = new double[points.length];

		double p1 = 0;
		double p2 = 0;

		for (int i = 0; i < points.length; i++) {
			double p = ((Vector) points[i]).project(v);

			if (p < p1) {
				p1 = p;
			}

			if (p > p2) {
				p2 = p;
			}
		}

		return p2 - p1;
	}
}
