package Model.Editor.Paper.Font;

import Model.Editor.Characters.FontCharacterHashMap;

public class FontInfo {
	private String name;
	private int characterHeight;
	private int characterWedth;
	private FontCharacterHashMap fontSet;

	/**
	 * 
	 * @param name
	 * @param characterHeight
	 * @param characterWedth
	 * @param fontSet
	 */
	public FontInfo(String name, int characterHeight, int characterWedth, FontCharacterHashMap fontSet) {
		super();
		this.name = name;
		this.characterHeight = characterHeight;
		this.characterWedth = characterWedth;
		this.fontSet = fontSet;
	}
	
	public void updateFont(String name, int characterHeight, int characterWedth, FontCharacterHashMap fontSet) {
		this.name = name;
		this.characterHeight = characterHeight;
		this.characterWedth = characterWedth;
		this.fontSet = fontSet;
	}

	public String getName() {
		return name;
	}

	public int getCharacterHeight() {
		return characterHeight;
	}

	public int getCharacterWedth() {
		return characterWedth;
	}

	public FontCharacterHashMap getFontSet() {
		return fontSet;
	}

	public void setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
		}
	}

	public void setCharacterHeight(int characterHeight) {
		if (characterHeight > 0) {
			this.characterHeight = characterHeight;
		}
	}

	public void setCharacterWedth(int characterWedth) {
		if (characterWedth > 0) {
			this.characterWedth = characterWedth;
		}
	}

	public void setFontSet(FontCharacterHashMap fontSet) {
		if (fontSet != null) {
			this.fontSet = fontSet;
		}
	}

}
