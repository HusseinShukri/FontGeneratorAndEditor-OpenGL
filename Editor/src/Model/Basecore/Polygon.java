package Model.Basecore;

import java.util.LinkedList;

public class Polygon {
	private String name;
	private float[] color;
	private LinkedList<Point> points;
	private int type; // GL2.GL_PolygonType

	public Polygon(String name, float[] color, LinkedList<Point> points, int type) {
		super();
		this.name = name;
		this.color = color;
		this.points = points;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public float[] getColor() {
		return color;
	}

	public LinkedList<Point> getPoints() {
		return points;
	}

	public int getType() {
		return type;
	}

}
