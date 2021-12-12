package Models;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CellsBox {
	private int cellsNumber;
	private int lineCellNumber;
	private int x;
	private int y;
	private int width;
	private int hight;
	private HashMap<CellKey, LinkedList<Point>> map;
	private Stack<CellKey> stack;

	public CellsBox(int cellsNumber, int x, int y, int width, int hight) {
		this.cellsNumber = cellsNumber;
		this.lineCellNumber=0;
		this.x = x;
		this.y = y;
		this.width = width;
		this.hight = hight;
		map = new HashMap<CellKey, LinkedList<Point>>();
		stack = new Stack<>();
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

	public int getHight() {
		return hight;
	}

	public int getCellsNumber() {
		return cellsNumber;
	}
	
	public int getLineCellNumber() {
		return lineCellNumber;
	}
	
	public void setLineCellNumber(int lineCellNumber) {
		this.lineCellNumber = lineCellNumber;
	}

	public boolean contains(int row, int column) {
		return map.containsKey(new CellKey(row, column));
	}

	public void remove(int row, int column) {
		map.remove(new CellKey(row, column));
		stack.removeElement(new CellKey(row, column));
	}

	public void add(int row, int column) {
		if (!map.containsKey(new CellKey(row, column))) {
			map.put(new CellKey(row, column), findPixels(row, column));
			stack.add(new CellKey(row, column));
		}
	}

	private LinkedList<Point> findPixels(int row, int column) {
		int YGap = hight / cellsNumber;
		int XGap = width / cellsNumber;
		LinkedList<Point> points = new LinkedList<Point>();
		for (int y = (YGap * column); y < (YGap * (column + 1)); y++) {
			for (int x = (XGap * row); x < (XGap * (row + 1)); x++) {
				points.add(new Point(x, y));
			}
		}
		return points;
	}

	public Collection<LinkedList<Point>> getValues() {
		return map.values();
	}

	public int getSize() {
		return map.size();
	}

	public void delatelast() {
		if (stack.size() != 0) {
			map.remove(stack.pop());
		}
	}

	public void clear() {
		map.clear();
		stack.clear();
	}

	public Collection<CellKey> getKeys() {
		return map.keySet();
	}

	public List<CellKey> getKeys2() {
		List<CellKey> a = new LinkedList<CellKey>();
		a.addAll(stack);
		return a;
	}
	
	@Override
	public String toString() {
		String str ="";
		for (CellKey cellKey : stack) {
			str+=","+cellKey.getRow()+"-"+cellKey.getColumn();
			}
		return str+="\n";
	}
}
