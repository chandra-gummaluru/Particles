package graphics;

import java.awt.Color;

import math.Point;
import math.Vector;

public abstract class Shape implements Drawable {

	private static int SCALE;

	public static int getScale() {
		return SCALE;
	}

	public static void setScale(int scale) {
		SCALE = scale;
	}

	// geometric properties
	private Point pos;

	public Point getPos() {
		return this.pos;
	}

	public abstract void setPos(Point pos);

	// visual properties
	private Color bgClr, fgClr;

	public Color getBgClr() {
		return this.bgClr;
	}

	public Color getFgClr() {
		return this.fgClr;
	}

	public Shape(Point pos, Color bgClr, Color fgClr) {
		this.pos = pos;

		this.bgClr = bgClr;
		this.fgClr = fgClr;
	}

	public boolean isIntersecting(Shape s) {
		return false;
	}

	public abstract boolean isIntersecting(Polygon p);

	public abstract boolean isIntersecting(Circle c);

	public abstract double getProjection(Vector axis);
}
