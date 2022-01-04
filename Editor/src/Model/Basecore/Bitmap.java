package Model.Basecore;

import java.util.LinkedList;

import Utility.ColorsUtility;

public class Bitmap {
	private float[] color;
	private LinkedList<Point> points;

	/**
	 * 
	 * @param points
	 */
	public Bitmap(LinkedList<Point> points) {
		super();
		this.color = new float[] { 0, 0, 1 };
		this.points = points;
	}

	public Bitmap(LinkedList<Point> points, float[] color) {
		super();
		this.color = ColorsUtility.checkColor(color);
		this.points = points;
	}

	/**
	 * Default value is 0,0,0 for black
	 * 
	 * @return RGB color
	 */
	public float[] getColor() {
		return color;
	}

	/**
	 * 
	 * @param r float value from 0 to 1
	 * @param g float value from 0 to 1
	 * @param b float value from 0 to 1
	 */
	public void setColor(float r, float g, float b) {
		this.color = ColorsUtility.checkColor(r, g, b);
	}

	public void setColor(float[] color) {
		this.color = ColorsUtility.checkColor(color);
	}

	public LinkedList<Point> getPoints() {
		return points;
	}

	public void setPoints(LinkedList<Point> points) {
		if (points != null) {
			this.points = points;
		}
	}

}
