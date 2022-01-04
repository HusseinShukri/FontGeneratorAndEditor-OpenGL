package Utility;

import java.util.LinkedList;

import Model.Basecore.Point;

public class MatricesUtility {

	/**
	 * 
	 * @param points
	 * @param xShift
	 * @param yShift
	 */
	public static void pixelsShifte(LinkedList<Point> points, int xShift, int yShift) {
		for (Point point : points) {
			point.setX(point.getX() + xShift);
			point.setY(point.getY() + yShift);
		}
	}

}
