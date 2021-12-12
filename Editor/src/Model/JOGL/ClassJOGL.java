package Model.JOGL;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

import java.awt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.FPSAnimator;

public abstract class ClassJOGL implements GLEventListener, MouseListener, MouseInputListener,KeyListener {
	private GL2 gl;
	private FPSAnimator animator;
	private float[] backgroundColor = new float[] { 0, 0, 0 };

	public ClassJOGL() {
		super();
	}

	public GL2 getGl() {
		return gl;
	}

	public void setGl(GL2 gl) {
		this.gl = gl;
	}

	public FPSAnimator getAnimator() {
		return animator;
	}

	public void setAnimator(FPSAnimator animator) {
		this.animator = animator;
	}

	public float[] getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(float[] backgroundColor) throws Exception {
		if (backgroundColor.length != 3) {
			throw new Exception("backgroundColor must be 3 element float array ");
		} else if (!isValedRGB(backgroundColor)) {
			throw new Exception("backgroundColor element value must be equal or between 0 and 1 ");
		}
		this.backgroundColor = backgroundColor;
	}

	private boolean isValedRGB(float[] rgb) {
		for (float f : rgb) {
			if (!isValedRGBValue(f)) {
				return false;
			}
		}
		return true;
	}

	private boolean isValedRGBValue(float value) {
		if (value >= 0 && value <= 1) {
			return true;
		}
		return false;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {

	}

	@Override
	public void init(GLAutoDrawable drawable) {
	}

	@Override
	public void display(GLAutoDrawable drawable) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		
	}
	
}
