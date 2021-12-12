package Models;

import java.util.HashMap;

public class FontHashMap {
	HashMap<String, FontCharacter> map;

	public FontHashMap() {
		super();
		this.map = new HashMap<String, FontCharacter>();
	}

	public void add(FontCharacter character) {
		if (!map.containsKey(character.getCharacter())) {
			map.put(character.getCharacter(), character);
		}
	}

	public void remove(FontCharacter character) {
		map.remove(character.getCharacter());
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
