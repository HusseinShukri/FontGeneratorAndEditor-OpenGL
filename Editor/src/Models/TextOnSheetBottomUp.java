package Models;

import java.util.HashMap;
import java.util.LinkedList;

import com.jogamp.opengl.GL2;

import Utility.DrawingUtility;
import Utility.SheetUtility;

public class TextOnSheetBottomUp {
	private HashMap<Integer, LinkedList<FontCharacter>> text;
	private int totalLines;
	private int currentLine;
	private int displacment;
	private int totalWidth;
	
	public TextOnSheetBottomUp(int linesTotal,int totalWidth) {
		super();
		this.totalLines = linesTotal;
		this.totalWidth=totalWidth;
		this.currentLine = 0;
		this.displacment = 16;
		this.text = new HashMap<Integer, LinkedList<FontCharacter>>(totalLines);
	}
	
	
	public void addCharacter(FontCharacter character) {
		if(getCurrentDisplacment()<totalWidth) {
			if(text.containsKey(currentLine)) {
				text.get(currentLine).add(character);
			}else {
				text.put(currentLine, new LinkedList<FontCharacter>());
				text.get(currentLine).add(character);
			}
			
		}else if(currentLine>=0) {
			currentLine++;
			if(text.containsKey(currentLine)) {
				text.get(currentLine).add(character);
			}else {
				text.put(currentLine, new LinkedList<FontCharacter>());
				text.get(currentLine).add(character);
			}
		}
	}

	public void printText(GL2 gl, float[] fontColor, int CharacterSize, int CharacherToCharacterSpace,int lineToLineSpace) {
		int line = totalLines;
		int y;
		int displacment;
		for (LinkedList<FontCharacter> linkedList : text.values()) {
			y = SheetUtility.FindYValueAtLine(CharacterSize, lineToLineSpace, line);
			displacment = 0;
			for (FontCharacter fontCharacter : linkedList) {
				if(fontCharacter.getCharacter().equals("")) {
					continue;
				}
				var A = getCharAtline(displacment, y, fontCharacter.getPoints());
				for (Point p : A) {
					DrawingUtility.setPixelColor(gl, fontColor, p.getX(), p.getY());
				}
				displacment += CharacherToCharacterSpace;
			}
			line--;
		}
	}

	private LinkedList<Point> getCharAtline(int x, int y, LinkedList<Point> Points) {
		LinkedList<Point> Points2 = new LinkedList<Point>();
		for (Point point : Points) {
			Points2.add(new Point(point.getX() + x, point.getY() + y));
		}
		return Points2;
	}
	
	private int getCurrentDisplacment() {
		if(text.containsKey(currentLine)) {
			return text.get((currentLine)).size()*displacment;	
		}
		return 0;
	}
	
	public void clear() {
		text.clear();
	}
	
	public void newLine() {
		if(currentLine<totalLines) {
			currentLine++;
			addCharacter(new FontCharacter("", new LinkedList<Point>()));
			System.out.println(currentLine);
		}
	}

}
