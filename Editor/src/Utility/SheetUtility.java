package Utility;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class SheetUtility {

	/**
	 * This method draws lines like notebook lines considering the charSize = height
	 * of character
	 * 
	 */
	public static void drawSheetLines(GL2 gl, float[] RGB, int charSize, int lineTolineSpace, int width, int hight,
			int x, int y) {
		gl.glColor3f(RGB[0], RGB[1], RGB[2]);
		gl.glBegin(GL.GL_LINES);
		int yGap = charSize + lineTolineSpace;
		for (int yValue = 0; yValue < hight; yValue += yGap) {
			gl.glVertex2f(0, yValue);
			gl.glVertex2f(width, yValue);
		}
		gl.glEnd();
	}

	/**
	 * 
	 * @param gl
	 * @param RGB
	 * @param charSize
	 * @param lineTolineSpace
	 * @param lineNumber
	 * @param width
	 * @param x
	 * @param y
	 */
	public static void drawSheetLines2(GL2 gl, float[] RGB, int charSize, int lineTolineSpace, int lineNumber,
			int width, int x, int y) {
		gl.glColor3f(RGB[0], RGB[1], RGB[2]);
		gl.glBegin(GL.GL_LINES);
		int yGap = charSize + lineTolineSpace;
		int i = 0;
		for (int yValue = 0; i < lineNumber; yValue += yGap, i++) {
			gl.glVertex2f(0, yValue);
			gl.glVertex2f(width, yValue);
		}
		gl.glEnd();
		
		gl.glColor3f(1, 0, 0);
		gl.glBegin(GL.GL_LINES);
		int yValue = (i*(charSize + lineTolineSpace)+2);
		gl.glVertex2f(0, yValue);
		gl.glVertex2f(width, yValue);
		gl.glEnd();
		
	}
	
	public static void drawSheetLinestEST(GL2 gl, float[] RGB, int charSize, int lineTolineSpace, int lineNumber,
			int width, int x, int y) {
		gl.glColor3f(1, 0, 0);
		gl.glBegin(GL.GL_LINES);
		int yValue = (charSize + lineTolineSpace);
		gl.glVertex2f(0, yValue);
		gl.glVertex2f(width, yValue);
		gl.glEnd();
		
		gl.glColor3f(RGB[0], RGB[1], RGB[2]);
		gl.glBegin(GL.GL_LINES);
		int yGap = charSize + lineTolineSpace;
		int i = 0;
		for ( yValue = (yGap*2); i < lineNumber; yValue += yGap, i++) {
			gl.glVertex2f(0, yValue);
			gl.glVertex2f(width, yValue);
		}
		gl.glEnd();
	}

	public static int FindYValueAtLine(int charHight, int lineToLineSpace, int line) {
		return (charHight + lineToLineSpace) * line;
	}

	/**
	 * 
	 * @param paberHeight
	 * @param lineToLineSpace
	 * @param characterSize
	 * @return integer line numbers for this height
	 */
	public static int FindLineNumber(int paberHeight, int lineToLineSpace, int characterSize) {
		if (lineToLineSpace == 0) {
			lineToLineSpace++;
		}
		return (int) (paberHeight / (characterSize * lineToLineSpace));
	}

}
