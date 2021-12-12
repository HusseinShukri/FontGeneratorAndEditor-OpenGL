package Models;

import java.util.LinkedList;

public class Cursor {

	private float[] color;
	private LinkedList<Point> corsor;

	/**
	 * 
	 * @param color
	 * @param CharacterSize
	 */
	public Cursor(float[] color, int CharacterSize) {
		super();
		this.color = color;
		this.corsor = initCursor(CharacterSize);
	}


	private LinkedList<Point> initCursor(int hight) {
		LinkedList<Point> temp = new LinkedList<Point>();
		for (int i = 0; i < hight; i++) {
			temp.add(new Point(0, i));
			temp.add(new Point(1, i));
		}
		return temp;
	}

	public void updateCursor(int x, int y,int charHight) {
		this.corsor = recalcualte(x, y, charHight);
	}
	
	private LinkedList<Point> recalcualte(int x, int y, int charHight) {
		LinkedList<Point> temp = new LinkedList<Point>();
		for (int i = 0, yValue = (y* charHight); i < charHight; i++, yValue++) {
			temp.add(new Point(0 + x, yValue));
			temp.add(new Point(1 + x, yValue));
		}
		return temp;
	}

	public float[] getColor() {
		return color;
	}

	public LinkedList<Point> getCorsor() {
		return corsor;
	}
}
