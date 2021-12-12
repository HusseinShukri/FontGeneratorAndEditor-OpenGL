package JOGL;

import java.awt.event.MouseEvent;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import Model.JOGL.EditorClassJOGL;
import Models.FontCharacter;
import Models.PaperSheet;
import Models.TextOnSheetBottomUp;
import Utility.SheetUtility;

public class EditorJOGLBottomUp extends EditorClassJOGL {
	private int[] panelSize = new int[2];
	private float[] lineColor;
	private float[] fontColor;
	private PaperSheet sheet;
	private boolean command = false;
	private TextOnSheetBottomUp sheetText;

	public EditorJOGLBottomUp(PaperSheet sheet) throws Exception {
		super();
		this.sheet = sheet;
		this.lineColor = new float[] { 0, 0, 1 };
		this.fontColor = new float[] { 0, 0, 0 };
		this.sheetText = new TextOnSheetBottomUp(sheet.getLinesNumber(), sheet.getWidth());
		setBackgroundColor(new float[] { 1, 1, 1 });
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		setGl(drawable.getGL().getGL2());
		panelSize[0] = drawable.getSurfaceWidth();
		panelSize[1] = drawable.getSurfaceHeight();
		getGl().glClearColor(getBackgroundColor()[0], getBackgroundColor()[1], getBackgroundColor()[2], 0f);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT);

		if (sheet != null) {
			System.out.println(panelSize[0] + "," + panelSize[1]);
			this.getAnimator().setFPS(10);
			this.getAnimator().start();
		}
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
		getGl().glOrtho(-0, panelSize[0] + 50, 0, panelSize[1] + 50, -1, 1);// left,right,bottom,top
		getGl().glMatrixMode(GL2.GL_MODELVIEW);
		getGl().glLoadIdentity();
		getGl().glViewport(50, 50, panelSize[0] + 50, panelSize[1] + 50); // (x,y,width,height)

		if (sheet != null) {
			SheetUtility.drawSheetLines2(getGl(), lineColor, sheet.getCharacterSize(), sheet.getLineToLineSpaceShift(),
					sheet.getLinesNumber(), sheet.getWidth(), sheet.getX(), sheet.getY());
			sheetText.printText(getGl(), fontColor, sheet.getCharacterSize(), sheet.getCharacherToCharacterSpaceShift(),
					sheet.getLineToLineSpaceShift());
		}

	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		String eChar = e.getKeyChar() + "";
		if (sheet.getFontCharacters() != null) {
			if (eChar.equals("^")) {
				System.out.println("here : ^");
				command = true;
			} else if (command && sheet.getFontCharacters().contains("^" + eChar)) {
				command = false;
				FontCharacter c = sheet.getFontCharacters().get("^" + eChar);
				sheetText.addCharacter(c);
			} else if (sheet.getFontCharacters().contains(eChar)) {
				FontCharacter c = sheet.getFontCharacters().get(eChar);
				sheetText.addCharacter(c);
			} else {
				command = false;
			}
		}
		if (e.getKeyChar() == 10) {
			sheetText.newLine();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		System.out.println(e.getX());
	}

	@Override
	public void SetFontColor(float[] rgb) {
		this.fontColor = rgb;
	}

	@Override
	public void setSheet(PaperSheet sheet) {
		this.sheet = sheet;
		this.sheetText = new TextOnSheetBottomUp(sheet.getLinesNumber(), sheet.getWidth());
	}
}