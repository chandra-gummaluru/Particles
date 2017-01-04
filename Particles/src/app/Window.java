package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel {

	private static final long serialVersionUID = 813013971973045054L;

	public static enum Scalability
	{
		FIXED, MAXIMIZED;
	}

	private JFrame frame;

	private Graphics2D bufferedGraphics;

	private BufferedImage bufferedImage;

	private int SCALE = 1;

	public Window(String title, Dimension size, Scalability scalability) {
		super();

		this.setFocusable(true);
		this.setPreferredSize(size);

		this.frame = new JFrame();

		frame.setContentPane(this);
		frame.setResizable(false);

		frame.pack();

		if (scalability == Scalability.MAXIMIZED) {
			this.SCALE = getPreferredScale();
			this.setPreferredSize(new Dimension((int) (size.getWidth() * SCALE), (int) (size.getHeight() * SCALE)));

			frame.pack();
		}

		bufferedImage = new BufferedImage((int) (size.getWidth() * SCALE), (int) (size.getHeight() * SCALE),
				BufferedImage.TYPE_4BYTE_ABGR);
		bufferedGraphics = (Graphics2D) bufferedImage.getGraphics();
		bufferedGraphics.setBackground(Color.BLACK);

		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Window(String title, Dimension size, Scalability scaleType, Color backgroundColor) {
		this(title, size, scaleType);
		this.setBackground(backgroundColor);
		bufferedGraphics.setBackground(backgroundColor);
	}

	public Window(String title, Dimension size, Scalability scaleType, Color backgroundColor, BufferedImage icon) {
		this(title, size, scaleType, backgroundColor);
		frame.setIconImage(icon);
	}

	public void draw() {
		Graphics g = this.getGraphics();

		// draw the back buffer to the panel.
		g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);

		// clear the back buffer.
		bufferedGraphics.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
	}

	private int getPreferredScale() {

		// Get the width of the frame border by taking the difference of
		// the frame width and panel width.
		int borderWidth = frame.getWidth() - this.getWidth();

		// Get the height of the frame border by taking the difference of
		// the frame height and the panel height.
		int borderHeight = frame.getHeight() - this.getHeight();

		// Get the maximum width of the screen that can be used for display.
		int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

		// Get the maximum height of the screen that can be used for display.
		int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

		// The preferred scale for the window.
		int preferredScale = 1;

		// This loop continues until the next possible scale factor becomes too
		// large.
		while (this.getWidth() * (preferredScale + 1) < screenWidth - borderWidth
				&& this.getHeight() * (preferredScale + 1) < screenHeight - borderHeight) {
			// Increment the scale.
			preferredScale++;
		}

		// Return the maximum scale factor.
		return preferredScale;
	}

	public int getScale() {
		return this.SCALE;
	}

	public JFrame getFrame() {
		return this.frame;
	}
	
	@Override
	public void setVisible(boolean visibility) {
		super.setVisible(visibility);
		frame.setVisible(visibility);
		
	}

	public Graphics2D getBufferedGraphics() {
		return this.bufferedGraphics;
	}

}
