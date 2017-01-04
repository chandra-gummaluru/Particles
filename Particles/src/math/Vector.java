package math;

public class Vector extends Point {

	private double length;

	public double getLength() {
		return this.length;
	}

	public Vector(double x, double y) {
		super(x, y);
		this.length = Math.sqrt(x * x + y * y);
	}

	public Vector add(double dx, double dy) {
		return new Vector(this.getX() + dx, this.getY() + dy);
	}

	public Vector add(Vector v) {
		return add(v.getX(), v.getY());
	}

	public Vector scale(double k) {
		return new Vector(k * this.getX(), k * this.getY());
	}

	public Vector invert() {
		return new Vector(1 / this.getX(), 1 / this.getY());
	}

	public Vector multiply(Vector v) {
		return new Vector(this.getX() * v.getX(), this.getY() * v.getY());
	}

	public Vector getUnitVector() {
		return new Vector(this.getX() / length, this.getY() / length);
	}

	public double project(Vector v) {
		return this.multiply(v).getLength();
	}

	public static Vector getPositionVector(Point p1, Point p2) {
		return new Vector(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

}
