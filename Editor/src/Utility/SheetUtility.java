package Utility;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class SheetUtility {
	/**
	 * 
	 * @param gl
	 * @param RGB
	 * @param characterHeight
	 * @param lineSpacing
	 * @param lineNumber
	 * @param width
	 */
	public static void drawSheetLinestEST(GL2 gl, float[] RGB, int characterHeight, int lineSpacing, int lineNumber,
			int width) {
		gl.glColor3f(1, 0, 0);
		gl.glBegin(GL.GL_LINES);
		int yValue = (characterHeight + lineSpacing);
		gl.glVertex2f(0, yValue);
		gl.glVertex2f(width, yValue);
		gl.glEnd();

		gl.glColor3f(RGB[0], RGB[1], RGB[2]);
		gl.glBegin(GL.GL_LINES);
		int yGap = characterHeight + lineSpacing;
		int i = 0;
		for (yValue = (yGap * 2); i < lineNumber; yValue += yGap, i++) {
			gl.glVertex2f(0, yValue);
			gl.glVertex2f(width, yValue);
		}
		gl.glEnd();
	}

	/**
	 * 
	 * @param charachterHeight
	 * @param lineSpacing
	 * @param line
	 * @return y value at specific line
	 */
	public static int FindYValueAtLine(int charachterHeight, int lineSpacing, int line, int lineCharacterSpacing) {
		return ((charachterHeight + lineSpacing) * line) + (lineSpacing + lineCharacterSpacing);
	}

	/**
	 * 
	 * @param paberHeight
	 * @param lineToLineSpace
	 * @param characterHeight
	 * @return integer line numbers for this height
	 */
	public static int FindLineNumber(int paberHeight, int lineToLineSpace, int characterHeight) {
		if (lineToLineSpace == 0) {
			lineToLineSpace++;
		}
		return (int) (paberHeight / (characterHeight * lineToLineSpace));
	}

}
