package Model.Editor.Characters;

import java.util.HashMap;

public class FontCharacterHashMap {
	HashMap<String, FontCharacter> map;

	public FontCharacterHashMap() {
		super();
		this.map = new HashMap<String, FontCharacter>();
	}

	public void add(FontCharacter character) {
		if (!map.containsKey(character.getName())) {
			map.put(character.getName(), character);
		}
	}

	public void remove(FontCharacter character) {
		map.remove(character.getName());
	}

	public boolean contains(String character) {
		if (map.containsKey(character)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param name
	 * @return FontCharacter if exist or null
	 */
	public FontCharacter get(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		return null;
	}

}
