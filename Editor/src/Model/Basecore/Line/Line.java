package Model.Basecore.Line;

import Model.Basecore.Point;
import Utility.ColorsUtility;

public class Line {
	private Point point1;
	private Point point2;
	private float[] color;

	public Line(Point point1, Point point2, float[] color) {
		super();
		this.point1 = point1;
		this.point2 = point2;
		this.color = ColorsUtility.checkColor(color);
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		if (point1 != null) {
			this.point1 = point1;
		}
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		if (point2 != null) {
			this.point2 = point2;
		}
	}

	public float[] getColor() {
		return color;
	}

	public void setColor(float[] color) {
		this.color = ColorsUtility.checkColor(color);
	}

}
