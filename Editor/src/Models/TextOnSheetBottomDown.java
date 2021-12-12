package Models;

import java.util.HashMap;
import java.util.LinkedList;

import com.jogamp.opengl.GL2;

import Utility.DrawingUtility;
import Utility.SheetUtility;

public class TextOnSheetBottomDown {
	private HashMap<Integer, LinkedList<FontCharacter>> text;
	private int totalLines;
	private int currentLine;
	private int displacment;
	private int totalWidth;
	private boolean printCursor;
	private Cursor cursor;
	private int currentDisplacement;

	public TextOnSheetBottomDown(int linesTotal, int totalWidth, int characterSize) {
		super();
		this.totalLines = linesTotal;
		this.totalWidth = totalWidth;
		this.currentLine = 0;
		this.displacment = 16;
		this.text = new HashMap<Integer, LinkedList<FontCharacter>>(totalLines);
		this.cursor = new Cursor(new float[] { 0, 0, 1 }, characterSize);
		this.printCursor = false;
		this.currentDisplacement = 0;
	}

	public void addCharacter(FontCharacter character, int charTocharSpace, int charHight) {
		if (getCurrentDisplacment() < totalWidth) {
			if (text.containsKey(currentLine)) {
				addToText(character, charTocharSpace, charHight);
			} else {
				text.put(currentLine, new LinkedList<FontCharacter>());
				addToText(character, charTocharSpace, charHight);
			}

		} else if (currentLine <= totalLines) {
			currentLine++;
			if (text.containsKey(currentLine)) {
				addToText(character, charTocharSpace, charHight);
			} else {
				text.put(currentLine, new LinkedList<FontCharacter>());
				addToText(character, charTocharSpace, charHight);
			}
		}
	}

	private void addToText(FontCharacter character, int charTocharSpace, int charHight) {
		text.get(currentLine).add(character);
		if (character.getCharacter() == "") {
			currentDisplacement = 0;
		} else {
			currentDisplacement = text.get(currentLine).size() * charTocharSpace;
		}
		updateCursor(currentDisplacement, currentLine, charHight);
	}

	private void updateCursor(int x, int y, int charHight) {
		cursor.updateCursor(x, y, charHight);
	}

	public void printSheet(GL2 gl, float[] fontColor, int CharacterSize, int CharacherToCharacterSpace,
			int lineToLineSpace, int lineToCharacterSpace) {
		if (printCursor) {
			printCursor = false;
			printCursor(gl);
		} else {
			printCursor = true;
		}

		printText(gl, fontColor, CharacterSize, CharacherToCharacterSpace, lineToLineSpace, lineToCharacterSpace);
	}

	private void printCursor(GL2 gl) {
		for (Point p : cursor.getCorsor()) {
			DrawingUtility.setPixelColor(gl, cursor.getColor(), p.getX(), p.getY());
		}
	}

	private void printText(GL2 gl, float[] fontColor, int CharacterSize, int CharacherToCharacterSpace,
			int lineToLineSpace, int lineToCharacterSpace) {
		int line = 0;
		int y;
		int displacment;
		for (LinkedList<FontCharacter> linkedList : text.values()) {
			y = SheetUtility.FindYValueAtLine(CharacterSize, lineToLineSpace, line);
			y += (lineToLineSpace + lineToCharacterSpace);
			displacment = 0;
			for (FontCharacter fontCharacter : linkedList) {
				if (fontCharacter.getCharacter().equals("")) {// Enter
					continue;
				} else {
					var position = getCharRelativePosition(displacment, y, fontCharacter.getPoints());
					for (Point p : position) {
						DrawingUtility.setPixelColor(gl, fontColor, p.getX(), p.getY());
					}
					displacment += CharacherToCharacterSpace;

				}
			}
			line++;
		}
	}

	private LinkedList<Point> getCharRelativePosition(int x, int y, LinkedList<Point> Points) {
		LinkedList<Point> Points2 = new LinkedList<Point>();
		for (Point point : Points) {
			Points2.add(new Point(point.getX() + x, point.getY() + y));
		}
		return Points2;
	}

	private int getCurrentDisplacment() {
		if (text.containsKey(currentLine)) {
			return text.get((currentLine)).size() * displacment;
		}
		return 0;
	}

	public void clear() {
		text.clear();
	}

	public void newLine(int charTocharSpace,int charHight) {
		if (currentLine < totalLines) {
			currentLine++;
			addCharacter(new FontCharacter("", new LinkedList<Point>()), charTocharSpace,charHight);
		}
	}

	public void backSpace(int charTocharSpace,int charHight) {
		LinkedList<FontCharacter> paragraph = text.get(currentLine);
		if (paragraph.size() != 0) {
			paragraph.removeLast();
			currentDisplacement = paragraph.size() * charTocharSpace;
		} else if (paragraph.size() == 0 && currentLine != 0) {
			currentLine--;
			paragraph = text.get(currentLine);
			currentDisplacement = paragraph.size() * charTocharSpace;
		}
//		else if (paragraph.size() == 0 && currentLine == 0) {
//			System.out.println("no more space");
//		}
		updateCursor(currentDisplacement, currentLine,charHight);
	}

}
