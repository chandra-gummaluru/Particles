package input;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public enum KeyBinding {
		W(87), A(65), S(83), D(68);
		
		private int val;
		
		KeyBinding (int val) {
			this.val = val;
		}
		
		public int getVal() {
			return this.val;
		}
	}
	
	private HashMap<Integer, Boolean> keys;

	private boolean pointerDown;
	private Point pointerPos;

	private int wheelRotation;
	private int wheelClickCount;

	public InputHandler() {
		keys = new HashMap<Integer, Boolean>();
		
		for (KeyBinding k : KeyBinding.values()) {
			keys.put(k.getVal(), false);
		}
		

		pointerPos = new Point(0, 0);

		pointerDown = false;
		wheelRotation = 0;
		wheelClickCount = 0;
	}

	public boolean isKeyPressed(KeyBinding keyBinding) {
		return keys.get(keyBinding.getVal());
	}

	public boolean isKeyPressed(int keyNumber) {
		return keys.get(keyNumber);
	}

	public boolean isPointerDown() {
		return this.pointerDown;
	}

	public Point getPointerPosition() {
		return this.pointerPos;
	}

	public int getWheelRotation() {
		return this.wheelRotation;
	}

	public int getWheelClickCount() {
		return this.wheelClickCount;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing...
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// this key is being pressed.
		keys.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// this key is no longer being pressed.
		keys.put(e.getKeyCode(), false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// do nothing...
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// the mouse is down.
		this.pointerDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// the mouse is no longer down.
		this.pointerDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// do nothing...
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do nothing...
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// the mouse position has changed.
		this.pointerPos = new Point(e.getPoint().x / 2, e.getPoint().y / 2);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// the mouse position has changed.
		this.pointerPos = new Point(e.getPoint().x / 2, e.getPoint().y / 2);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		this.wheelRotation = e.getWheelRotation();
		this.wheelClickCount = e.getClickCount();
	}

}
