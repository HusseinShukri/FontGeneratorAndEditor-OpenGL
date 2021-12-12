package Models;

public class PaperSheet {
	private FontHashMap fontCharacters;
	private int linesNumber;
	private int lineToCharacterShift;
	private int characherToCharacterSpaceShift;
	private int lineToLineSpaceShift;
	private int characterSize;
	private int width;
	private int height;
	private int x;
	private int y;

	/**
	 * 
	 * @param fontCharacters
	 * @param linesNumber
	 * @param characterSize
	 * @param lineToCharacterShift
	 * @param characherToCharacterSpaceShift
	 * @param lineToLineSpaceShift
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public PaperSheet(FontHashMap fontCharacters, int linesNumber, int characterSize, int lineToCharacterShift,
			int characherToCharacterSpaceShift, int lineToLineSpaceShift, int width, int height, int x, int y) {
		super();
		this.fontCharacters = fontCharacters;
		this.linesNumber = linesNumber;
		this.characterSize = characterSize;
		this.lineToCharacterShift = lineToCharacterShift;
		this.characherToCharacterSpaceShift = characherToCharacterSpaceShift;
		this.lineToLineSpaceShift = lineToLineSpaceShift;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public int getLinesNumber() {
		return linesNumber;
	}

	public int getCharacterSize() {
		return characterSize;
	}

	public int getLineToCharacterShift() {
		return lineToCharacterShift;
	}

	public int getCharacherToCharacterSpaceShift() {
		return characherToCharacterSpaceShift;
	}

	public int getLineToLineSpaceShift() {
		return lineToLineSpaceShift;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public FontHashMap getFontCharacters() {
		return fontCharacters;
	}

	public void setLineToLineSpaceShift(int lineToLineSpaceShift) {
		if (lineToLineSpaceShift >= 0) {
			this.lineToLineSpaceShift = lineToLineSpaceShift;
		}
	}

}
