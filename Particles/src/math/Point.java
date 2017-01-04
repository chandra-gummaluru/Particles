package math;

public class Point {

	private double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point add(double dx, double dy) {
		return new Point(this.x + dx, this.y + dy);
	}

	public void set(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	public void translate(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	public void translate(Point p) {
		translate(p.x, p.y);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public String toString() {
		return "(" + this.getX() + "," + this.getY() + ")";
	}

}
