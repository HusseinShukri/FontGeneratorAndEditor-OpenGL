package Utility;

import java.nio.FloatBuffer;
import java.util.LinkedList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;

import Model.Basecore.Bitmap;
import Model.Basecore.Point;
import Model.Basecore.Line.Line;
import Model.Basecore.Line.LineBitmap;
import Model.Dto.ToDrawtDto;

public class DrawingUtility {

	public static void setPixelColor(GL2 gl, float[] rgb, int x, int y) {
		gl.glBegin(GL.GL_POINTS);
		gl.glColor3f(rgb[0], rgb[1], rgb[2]);
		gl.glVertex2d(x, y);
		gl.glEnd();
	}

	public static float[] getPixelColor(GL2 gl, int x, int y) {
		FloatBuffer buffer = FloatBuffer.allocate(4);
		gl.glReadBuffer(GL3.GL_FRONT);
		gl.glReadPixels(x, y, 1, 1, GL3.GL_RGBA, GL3.GL_FLOAT, buffer);
		float[] pixels = new float[3];
		pixels = buffer.array();
		return new float[] { pixels[0], pixels[1], pixels[2] };
	}

	public static void drawToDraw(GL2 gl, ToDrawtDto drawtDto) {
		if (drawtDto == null) {
			return;
		}
		if (drawtDto.bitmaps != null) {
			for (Bitmap bitmap : drawtDto.bitmaps) {
				drawBitmap(gl, bitmap);
			}
		}

		if (drawtDto.lineBitmaps != null) {
			for (LineBitmap lineBitmap : drawtDto.lineBitmaps) {
				drawBitmap(gl, lineBitmap.getBitmap());
			}
		}

		if (drawtDto.lines != null) {
			drawLines(gl, drawtDto.lines);
		}
	}

	public static void drawBitmap(GL2 gl, Bitmap bitmap) {
		for (Point points : bitmap.getPoints()) {
			setPixelColor(gl, bitmap.getColor(), points.getX(), points.getY());
		}
	}

	public static void drawLines(GL2 gl, LinkedList<Line> lines) {
		for (Line line : lines) {
			gl.glColor3f(line.getColor()[0], line.getColor()[1], line.getColor()[2]);
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(line.getPoint1().getX(), line.getPoint1().getY());
			gl.glVertex2f(line.getPoint2().getX(), line.getPoint2().getY());
			gl.glEnd();

		}
	}

}
