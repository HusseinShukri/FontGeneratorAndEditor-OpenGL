package Model.GLCanvas;

import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.FPSAnimator;

import Model.Dto.FontDto;
import Model.Dto.ToDrawtDto;
import Model.Editor.Characters.FontCharacter;
import Model.Editor.Paper.Paper;
import Utility.DrawingUtility;

public class EditorJOGL extends EditorClassJOGL {
	private int[] panelSize = new int[2];
	private boolean command = false;
	private int YViewport;
	private Paper paper;

	public EditorJOGL(Paper paper) throws Exception {
		super();
		setBackgroundColor(new float[] { 1, 1, 1 });
		this.paper = paper;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		setGl(drawable.getGL().getGL2());
		panelSize[0] = drawable.getSurfaceWidth();
		panelSize[1] = drawable.getSurfaceHeight();
		getGl().glClearColor(getBackgroundColor()[0], getBackgroundColor()[1], getBackgroundColor()[2], 0f);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT);

		if (paper != null) {
			this.setAnimator(new FPSAnimator(drawable, 10, true));
			this.getAnimator().start();
		}
		YViewport = -50;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		getGl().glClearColor(getBackgroundColor()[0], getBackgroundColor()[1], getBackgroundColor()[2], 0f);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		getGl().glLoadIdentity();
		getGl().glMatrixMode(GL2.GL_PROJECTION);
		getGl().glLoadIdentity();
		getGl().glOrtho(-0, panelSize[0] + 50,
				(panelSize[1] + 50 * (this.paper.getParagraphInfo().getLineSpacing())) + 50, 0, -1, 1);// left,right,bottom,top
//		getGl().glOrtho(-0, panelSize[0] + 50,
//				(panelSize[1] + 50 * (0)) + 50, 0, -1, 1);// left,right,bottom,top
		getGl().glMatrixMode(GL2.GL_MODELVIEW);
		getGl().glLoadIdentity();
		int height = panelSize[1] + 50 * (this.paper.getParagraphInfo().getLineSpacing());
//		int height = panelSize[1] + 50 * (0);
		getGl().glViewport(50, panelSize[0] + 50 - height + YViewport, panelSize[0] + 50, height); // (x,y,width,height)
		if (paper != null) {
			drawPage(getGl(), getToDraw());
		}
	}

	private void drawPage(GL2 gl, ToDrawtDto drawtDto) {
		DrawingUtility.drawToDraw(gl, drawtDto);
	}

	private ToDrawtDto getToDraw() {
		return paper.getToDraw();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String inputChar = e.getKeyChar() + "";
		if (this.paper.getFontInfo().getFontSet() != null) {
			if (inputChar.equals("^")) {
				command = true;
			} else if (command && this.paper.getFontInfo().getFontSet().contains("^" + inputChar)) {
				command = false;
				FontCharacter c = paper.getFontInfo().getFontSet().get("^" + inputChar);
				paper.addCharacterToText(c);
			} else if (paper.getFontInfo().getFontSet().contains(inputChar)) {
				FontCharacter c = paper.getFontInfo().getFontSet().get(inputChar);
				paper.addCharacterToText(c);
			} else {
				command = false;
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			paper.addLineToText();
		} else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			paper.addBackSpaceToText();
		} else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			paper.addSpaceToText();
		}
	}

	@Override
	public void SetFontColor(float[] rgb) {
		this.paper.setCurrentColor(rgb);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		YViewport += e.getUnitsToScroll() * 15;
	}

	@Override
	public void setLineSpacing(int value) {
		this.paper.setLineSpacing(value);
	}

	@Override
	public void setFont(FontDto fontDto) {
		this.paper.setNewFont(fontDto);
	}

}
