package Model.Editor.Paper.Text;

import java.util.HashMap;
import java.util.LinkedList;

import Model.Basecore.Bitmap;
import Model.Basecore.Point;
import Model.Editor.Characters.FontCharacter;
import Model.Editor.Characters.Custom.EnterCharacter;
import Model.Editor.Characters.Custom.ICustomCharcter;
import Model.Editor.Characters.Custom.SpaceCharacter;
import Utility.MatricesUtility;
import Utility.SheetUtility;

public class PageText {
	private HashMap<Integer, LinkedList<FontCharacter>> text;
	private int currentLine;

	/**
	 * 
	 * @param startingLine
	 */
	public PageText(int startingLine) {
		super();
		this.text = new HashMap<Integer, LinkedList<FontCharacter>>();
		this.currentLine = startingLine;
	}

	public int getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(int currentLine) {
		if (currentLine > 0) {
			this.currentLine = currentLine;
		}
	}

	public LinkedList<Bitmap> getTextBitmap() {
		LinkedList<Bitmap> temp = new LinkedList<Bitmap>();
		for (LinkedList<FontCharacter> lines : text.values()) {
			for (FontCharacter character : lines) {
				temp.add(character.getBitmap());
			}
		}
		return temp;
	}

	public HashMap<Integer, LinkedList<FontCharacter>> getText() {
		return text;
	}

	public void clearText() {
		text.clear();
	}

	/**
	 * 
	 * @param color
	 * @param pageWidth
	 * @param pageLinesCount
	 * @param lineSpacing
	 * @param lineCharacterSpacing
	 * @param characterSpacing
	 * @param characterHeight
	 * @param characterWeidth
	 */
	public void space(float[] color, int pageWidth, int pageLinesCount, int lineSpacing, int lineCharacterSpacing,
			int characterSpacing, int characterHeight, int characterWeidth) {
		if (currentLine < pageLinesCount + 1) {
			addCharacter(color, pageWidth, pageLinesCount, lineSpacing, lineCharacterSpacing,
					new SpaceCharacter(characterWeidth), characterHeight, characterWeidth);
		}
	}

	/**
	 * 
	 * @param pageLinesCount
	 */
	public void newLine(int pageLinesCount) {
		if (currentLine < pageLinesCount) {
			currentLine++;
		}
	}

	public void backSpace() {
		LinkedList<FontCharacter> textParagraph = text.get(currentLine);
		if (textParagraph == null && currentLine != 0) {
			currentLine--;
		} else if (textParagraph == null && currentLine == 0) {
			return;
		} else if (textParagraph.size() != 0) {
			textParagraph.removeLast();
		} else if (textParagraph.size() == 0 && currentLine != 0) {
			currentLine--;
		}
	}

	public void arrawUp() {
		if (currentLine > 0) {
			currentLine--;
		}
	}

	public void arrawDown(int pageLinesCount) {
		if (currentLine < pageLinesCount) {
			currentLine--;
		}
	}

	/**
	 * 
	 * @param color
	 * @param pageWidth
	 * @param pageLinesCount
	 * @param lineSpacing
	 * @param lineCharacterSpacing</br>
	 *                                  Pass character from FontSet and new
	 *                                  character with relative position will be
	 *                                  generated
	 * @param character
	 * @param characterHeight
	 * @param characterWeidth
	 */
	public void addCharacter(float[] color, int pageWidth, int pageLinesCount, int lineSpacing,
			int lineCharacterSpacing, FontCharacter character, int characterWeidth, int characterHeight) {
		if ((getCurrentDisplacment(characterWeidth) + characterWeidth) < pageWidth) {
			if (text.containsKey(currentLine)) {
				addToText(color, lineSpacing, lineCharacterSpacing, character, characterHeight, characterWeidth);
			} else {
				text.put(currentLine, new LinkedList<FontCharacter>());
				addToText(color, lineSpacing, lineCharacterSpacing, character, characterHeight, characterWeidth);
			}
		} else if (currentLine + 1 <= pageLinesCount) {
			currentLine++;
			if (text.containsKey(currentLine)) {
				addToText(color, lineSpacing, lineCharacterSpacing, character, characterHeight, characterWeidth);
			} else {
				text.put(currentLine, new LinkedList<FontCharacter>()); // EMPTY TO INIT THE LIST
				addToText(color, lineSpacing, lineCharacterSpacing, character, characterHeight, characterWeidth);
			}
		}
	}

	public int getCurrentDisplacment(int characterWeidth) {
		if (text.containsKey(currentLine)) {
			return text.get((currentLine)).size() * characterWeidth;
		}
		return 0;
	}

	private void addToText(float[] color, int lineSpacing, int lineCharacterSpacing, FontCharacter character,
			int characterHeight, int characterWeidth) {
		int y = SheetUtility.FindYValueAtLine(characterHeight, lineSpacing, currentLine, lineCharacterSpacing);
		if (ICustomCharcter.class.isAssignableFrom(character.getClass())) {
			if (character instanceof EnterCharacter) {
				text.get(currentLine).add(new EnterCharacter());
			} else if (character instanceof SpaceCharacter) {
				text.get(currentLine).add(new SpaceCharacter());
			}
		} else {
			int currentDisplacment = getCurrentDisplacment(characterWeidth);
			FontCharacter temp = new FontCharacter(character.getName(),
					getCharacterRelativePosition(currentDisplacment, y, character.getBitmap().getPoints()));
			temp.getBitmap().setColor(color[0], color[1], color[2]);
			text.get(currentLine).add(temp);
		}
	}

	private LinkedList<Point> getCharacterRelativePosition(int x, int y, LinkedList<Point> Points) {
		LinkedList<Point> Points2 = new LinkedList<Point>();
		for (Point point : Points) {
			Points2.add(new Point(point.getX() + x, point.getY() + y));
		}
		return Points2;
	}

	/**
	 * 
	 * @param oldLineSpacing
	 * @param newlineSpacing
	 * @param lineCharacterSpacing
	 * @param characterHeight
	 */
	public void updateLineSpacingText(int linesCount, int oldLineSpacing, int newlineSpacing, int lineCharacterSpacing,
			int characterHeight) {
		int y1 = 0;
		int y2 = 0;
		for (int i = 0; i < linesCount + 1; i++) {// all lines
			LinkedList<FontCharacter> characters = text.get(i);// line
			if (characters != null) {
				y1 = SheetUtility.FindYValueAtLine(characterHeight, oldLineSpacing, i, lineCharacterSpacing);
				y2 = SheetUtility.FindYValueAtLine(characterHeight, newlineSpacing, i, lineCharacterSpacing);
				for (FontCharacter fontCharacter : characters) {// characters
					translation(fontCharacter.getBitmap().getPoints(), y2 - y1, 0);
				}
			}
		}
	}

	private void translation(LinkedList<Point> points, int y, int x) {
		MatricesUtility.pixelsShifte(points, x, y);
	}

}
