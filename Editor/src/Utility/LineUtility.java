package Utility;

import java.util.LinkedList;

import Model.Basecore.Point;
import Model.Basecore.Line.Line;

public class LineUtility {
	/**
	 * 
	 * @param RGB
	 * @param characterHeight
	 * @param lineSpacing
	 * @param lineCount
	 * @param width
	 * @return
	 */
	public static LinkedList<Line> getLinesList(float[] RGB, int characterHeight, int lineSpacing, int lineCount,
			int width) {
		LinkedList<Line> temp = new LinkedList<Line>();
		int yinit = (characterHeight + lineSpacing);
		temp.add(new Line(new Point(0, yinit), new Point(width, yinit), new float[] { 1, 0, 0 }));
		int yGap = characterHeight + lineSpacing;
		for (int i = 0, yValue = (yGap * 2); i < lineCount; yValue += yGap, i++) {
			temp.add(new Line(new Point(0, yValue), new Point(width, yValue), RGB));
		}
		return temp;
	}
}
