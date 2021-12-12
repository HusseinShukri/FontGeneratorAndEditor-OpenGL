package Utility;

import java.util.Collection;
import java.util.LinkedList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import Models.Point;

public class CellUtility {

	public static void drawGrid_Box_Line(GL2 gl, float[] RGB, int lineAt, float lineThickness, int cellsNumber, int x,
			int y, int width, int hight) {
		gl.glColor3f(RGB[0], RGB[1], RGB[2]);
		gl.glLineWidth(lineThickness);
		gl.glBegin(GL.GL_LINES);
		float YGap = hight / cellsNumber;
		float XGap = width / cellsNumber;
		for (float i = 0, xValue = 0, yValue = 0; i <= cellsNumber; i++, yValue += YGap, xValue += XGap) {
			// line
			if (i == lineAt) {
				gl.glVertex2f(x, yValue + 2);
				gl.glVertex2f(width, yValue + 2);
			}
			// row
			gl.glVertex2f(x, yValue);
			gl.glVertex2f(width, yValue);
			// column
			gl.glVertex2f(xValue, y);
			gl.glVertex2f(xValue, hight);
		}
		gl.glEnd();
	}

	public static void drawGrid_Box(GL2 gl, float[] RGB, float lineThickness, int cellsNumber, int x, int y, int width,
			int hight) {
		gl.glColor3f(RGB[0], RGB[1], RGB[2]);
		gl.glLineWidth(lineThickness);
		gl.glBegin(GL.GL_LINES);
		float YGap = hight / cellsNumber;
		float XGap = width / cellsNumber;
		for (float i = 0, xValue = 0, yValue = 0; i <= cellsNumber; i++, yValue += YGap, xValue += XGap) {
			// row
			gl.glVertex2f(x, yValue);
			gl.glVertex2f(width, yValue);
			// column
			gl.glVertex2f(xValue, y);
			gl.glVertex2f(xValue, hight);
		}
		gl.glEnd();
	}

	public static void drawSelectedCells(GL2 gl, float[] rgb, Collection<LinkedList<Point>> collection) {
		if (collection.size() != 0) {
			for (LinkedList<Point> linkedList : collection) {
				for (Point point : linkedList) {
					DrawingUtility.setPixelColor(gl, rgb, point.getX(), point.getY());
				}
			}
		}
	}

	public static int getCellIndex(int value, int minValue, int maxValue, int cellsNumber) {
		int index = 0;
		int shiftGap = maxValue / cellsNumber;
		if (value < minValue || value > maxValue) {
			return -1;
		}
		for (int i = minValue + shiftGap; i <= maxValue; i += shiftGap) {
			if (value < i) {
				return index;
			} else {
				index++;
			}
		}
		// or throw an error
		return -2;
	}

}
