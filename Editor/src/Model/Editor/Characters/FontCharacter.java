package Model.Editor.Characters;

import java.util.LinkedList;

import Model.Basecore.Bitmap;
import Model.Basecore.Point;

public class FontCharacter {
	private String name;
	private Bitmap bitmap;

	/**
	 * 
	 * @param name
	 * @param points
	 */
	public FontCharacter(String name, LinkedList<Point> points) {
		super();
		this.name = name;
		this.bitmap = new Bitmap(points);
	}

	public String getName() {
		return name;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

}
