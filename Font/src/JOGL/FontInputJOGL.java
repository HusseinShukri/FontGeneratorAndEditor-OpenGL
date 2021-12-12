package JOGL;

import java.awt.event.MouseEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import JOGL.models.FontClassJOGL;
import Models.CellsBox;
import Utility.CellUtility;

public class FontInputJOGL extends FontClassJOGL {
	private GL2 gl;
	private float[] backgroundColor;
	private int[] panelSize = new int[2];
	private float[] lineColor = new float[] { 0, 0, 1 };
	private CellsBox box;
	private boolean isErase = false;

	public FontInputJOGL(CellsBox box) throws Exception {
		super();
		this.box = box;
		setBackgroundColor(new float[] { 1, 1, 1 });
		this.backgroundColor = getBackgroundColor();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		setGl(drawable.getGL().getGL2());
		gl = getGl();
		panelSize[0] = drawable.getSurfaceWidth();
		panelSize[1] = drawable.getSurfaceHeight();
		gl.glClearColor(backgroundColor[0], backgroundColor[1], backgroundColor[2], 0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		gl.glClearColor(backgroundColor[0], backgroundColor[1], backgroundColor[2], 0f);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, panelSize[0], 0, panelSize[1], -1, 1);
//		gl.glOrtho(-100, panelSize[0]+100, -100, panelSize[1]+100, -1, 1);//left,right,bottom,top
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, panelSize[0] + 100, panelSize[1] + 100); // (x,y,width,height)
//		gl.glViewport(0, 0, panelSize[0], panelSize[1]); // (x,y,width,height)

		CellUtility.drawGrid_Box_Line(gl, lineColor, box.getLineCellNumber(), 3f, 16, box.getX(), box.getY(), box.getWidth(), box.getHight());
		CellUtility.drawSelectedCells(gl, new float[] { 0, 0, 0 }, box.getValues());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// An OpenGL context can only be active in a single thread at a time
		// this mouseClicked method is running on other thread than the OpenGL
		// runs on
		int x = e.getX();
		int y = panelSize[1] - e.getY();
		int column = CellUtility.getCellIndex(x, box.getX(), box.getWidth(), 16);
		int row = CellUtility.getCellIndex(y, box.getY(), box.getHight(), 16);
		if (!isErase) {
			addToCellsBox(y, x);
		} else {
			box.remove(column, row);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = panelSize[1] - e.getY();
		int column = CellUtility.getCellIndex(x, box.getX(), box.getWidth(), 16);
		int row = CellUtility.getCellIndex(y, box.getY(), box.getHight(), 16);
		if (x > 0 && x < panelSize[0] && y > 0 && y < panelSize[1]) {
			if (!isErase) {
				addToCellsBox(y, x);
			} else {
				box.remove(column, row);
			}
		}
	}

	private void addToCellsBox(int x, int y) {
		int column = CellUtility.getCellIndex(x, box.getX(), box.getWidth(), 16);
		int row = CellUtility.getCellIndex(y, box.getY(), box.getHight(), 16);
		box.add(row, column);
	}

	@Override
	public void setIsEraseFlase() {
		isErase = false;
	}

	@Override
	public void setIsEraseTrue() {
		isErase = true;
	}

	@Override
	public void stepBack() {
		box.delatelast();
	}

	@Override
	public void clear() {
		box.clear();
	}

	@Override
	public void setBox(CellsBox box) {
		this.box = box;
	}
}
