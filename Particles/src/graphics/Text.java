package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import math.Point;

public class Text implements Drawable {

	// geometric properties
	private Point pos;

	public Point getPos() {
		return this.pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	private String text;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// style properties
	private Font font;
	@SuppressWarnings("unused")
	private double size;

	private Color color;

	public void setFont(Font font) {
		this.font = font;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Text(String text, Point pos, Font font, Color color) {
		this.text = text;

		this.pos = pos;

		this.font = font;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(color);

		g.drawString(text, (int) pos.getX(), (int) pos.getY());
	}

}
