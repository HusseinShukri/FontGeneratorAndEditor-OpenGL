package Model.Editor.Paper.Text;

public class ParagraphInfo {
	private int lineSpacing;
	private int characterSpacing;
	private int lineCharacterSpacing;
	
	/**
	 * 
	 * @param lineSpacing
	 * @param characterSpacing
	 * @param lineCharacterSpacing
	 */
	public ParagraphInfo(int lineSpacing, int characterSpacing, int lineCharacterSpacing) {
		super();
		this.lineSpacing = lineSpacing;
		this.characterSpacing = characterSpacing;
		this.lineCharacterSpacing = lineCharacterSpacing;
	}

	public int getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(int lineSpacing) {
		if (lineSpacing >= 0) {
			this.lineSpacing = lineSpacing;
		}
	}

	public int getCharacterSpacing() {
		return characterSpacing;
	}

	public void setCharacterSpacing(int characterSpacing) {
		if (characterSpacing >= 0) {
			this.characterSpacing = characterSpacing;
		}
	}

	public int getLineCharacterSpacing() {
		return lineCharacterSpacing;
	}

	public void setLineCharacterSpacing(int lineCharacterSpacing) {
		if (lineCharacterSpacing >= 0) {
			this.lineCharacterSpacing = lineCharacterSpacing;
		}
	}

}
