package Model.Editor.Cursor;

import java.util.LinkedList;

import Model.Basecore.Bitmap;
import Model.Basecore.Point;
import Utility.MatricesUtility;
import Utility.SheetUtility;

public class Cursor {
	private int thickness;
	private int height;
	private Bitmap bitmap;

	/**
	 * 
	 * @param color
	 * @param lineSpacing
	 * @param lineCharacterSpacing
	 * @param height
	 * @param currentLine
	 * @param thickness
	 */
	public Cursor(float[] color, int lineSpacing, int height, int currentLine, int thickness) {
		super();
		this.thickness = thickness;
		this.height = height;
		this.bitmap = new Bitmap(findCoordinate(0, lineSpacing, height, currentLine, thickness), color);
	}

	/**
	 * 
	 * @param x
	 * @param lineSpacing
	 * @param charHight
	 * @param currentLine
	 * @param thickness
	 * @return
	 */
	private LinkedList<Point> findCoordinate(int x, int lineSpacing, int charHight, int currentLine, int thickness) {
		LinkedList<Point> temp = new LinkedList<Point>();
		for (int thick = 0; thick < thickness; thick++) {
			for (int i = 0, yValue = findYValue(lineSpacing, charHight, currentLine); i < charHight; i++, yValue++) {
				temp.add(new Point(thick + x, yValue));
			}
		}
		return temp;
	}

	/**
	 * 
	 * @param lineSpacing
	 * @param lineCharacterSpacing
	 * @param characterHeight
	 * @param currentLine
	 * @return
	 */
	private int findYValue(int lineSpacing, int characterHeight, int currentLine) {
		return SheetUtility.FindYValueAtLine(characterHeight, lineSpacing, currentLine, 0);
	}

	/**00
	 * 
	 * @param displacement
	 * @param lineSpacing
	 * @param lineCharacterSpacing
	 * @param charHight
	 * @param currentLine
	 * @param thickness
	 */
	public void updateCoordinates(int displacement, int lineSpacing, int charHight, int currentLine) {
		this.bitmap.setPoints(findCoordinate(displacement, lineSpacing, charHight, currentLine, thickness));
	}

	///
	public void updateLineSpacing(int oldLineSpacing, int newlineSpacing, int charHight, int currentLine) {
		updateCoordinate(oldLineSpacing, newlineSpacing, charHight, currentLine);
	}

	private void updateCoordinate(int oldLineSpacing, int newlineSpacing, int charHight, int currentLine) {
		int y1 = findYValue(oldLineSpacing, charHight, currentLine);
		int y2 = findYValue(newlineSpacing, charHight, currentLine);
		MatricesUtility.pixelsShifte(this.bitmap.getPoints(), 0, y2 - y1);

	}
	///

	public int getThickness() {
		return thickness;
	}

	public int getHeight() {
		return height;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setThickness(int thickness) {
		if (thickness > 0) {
			this.thickness = thickness;
		}
	}

	public void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		}
	}

	public void setBitmap(Bitmap bitmap) {
		if (bitmap != null) {
			this.bitmap = bitmap;
		}
	}

}
