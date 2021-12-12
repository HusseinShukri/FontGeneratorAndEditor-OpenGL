package Models;

import java.util.HashMap;

public class CellsBoxList {
	private HashMap<String, CellsBox> map;

	public CellsBoxList() {
		super();
		map = new HashMap<String, CellsBox>();
	}

	public boolean contatin(String name) {
		if (map.containsKey(name)) {
			return true;
		}
		return false;
	}

	public boolean add(String name, CellsBox box) {
		if (map.containsKey(name)) {
			return false;
		} else {
			map.put(name, box);
			return true;
		}
	}

	public boolean remove(String name) {
		if (map.remove(name) != null) {
			return true;
		}
		return false;
	}

	public CellsBox get(String name) {
		return map.get(name);
	}

	public void update(String name, CellsBox box) {
		map.replace(name, box);
	}

	public void updateName(String oldName, String newName) {
		var temp = map.remove(oldName);
		if (temp != null) {
			map.put(newName, temp);
		}
	}

	public void clearList() {
		this.map.clear();
	}

	public HashMap<String, CellsBox> getMap() {
		return this.map;
	}

	public void updateAllLineCellnumber(int i) {
		for (CellsBox box : this.map.values()) {
			box.setLineCellNumber(i);
		}
	}

	@Override
	public String toString() {
		String str = "";
		for (String character : map.keySet()) {
			str += character + map.get(character).toString();
		}
		return str;
	}
}
