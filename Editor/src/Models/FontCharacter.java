package Models;

import java.util.LinkedList;

public class FontCharacter {
	private String Character;
	private LinkedList<Point> Points;
	
	/**
	 * 
	 * @param character
	 * @param points
	 */
	public FontCharacter(String character, LinkedList<Point> points) {
		super();
		Character = character;
		Points = points;
	}
	public String getCharacter() {
		return Character;
	}
	public LinkedList<Point> getPoints() {
		return Points;
	}
	
}
