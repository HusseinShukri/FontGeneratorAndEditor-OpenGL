package Model.Basecore;

public class Pixel {

	private int x;
	private int y;
	private float[] color;

	public Pixel(int x, int y, float[] color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float[] getColor() {
		return color;
	}

	public String toString() {
		return (x + "," + y + "," + color[0] + "," + color[1] + "," + color[2]);
	}

}
