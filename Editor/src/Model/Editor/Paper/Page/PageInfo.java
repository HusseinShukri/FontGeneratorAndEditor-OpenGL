package Model.Editor.Paper.Page;

public class PageInfo {
	private int linesCount;
	private int width;
	private int height;
	private int x;
	private int y;

	/**
	 * 
	 * @param linesCount
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public PageInfo(int linesCount, int width, int height, int x, int y) {
		super();
		this.linesCount = linesCount;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public int getLinesCount() {
		return linesCount;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
