package JOGL;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import JOGL.models.FontClassJOGL;
import Models.CellKey;
import Models.CellsBox;
import Utility.DrawingUtility;

public class FontOutputJOGL extends FontClassJOGL {
	private GL2 gl;
	private float[] backgroundColor;
	private int[] panelSize = new int[2];
	private float[] lineColor = new float[] { 0, 0, 1 };
	private CellsBox box;

	public FontOutputJOGL(CellsBox box) throws Exception {
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
		gl = drawable.getGL().getGL2();
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
		gl.glOrtho(0, panelSize[0] / 2, 0, panelSize[1] / 2, -1, 1);
//			gl.glOrtho(-100, panelSize[0]+100, -100, panelSize[1]+100, -1, 1);//left,right,bottom,top
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glViewport(10, 15, panelSize[0], panelSize[1]); // (x,y,width,height)

		drawtPoints();
	}

	private void drawtPoints() {
		if (box.getSize() != 0) {
			for (CellKey key : box.getKeys()) {
				DrawingUtility.setPixelColor(gl, lineColor, key.getRow(), key.getColumn());
			}
		}
	}

	@Override
	public void setBox(CellsBox box) {
		this.box=box;
	}

}
