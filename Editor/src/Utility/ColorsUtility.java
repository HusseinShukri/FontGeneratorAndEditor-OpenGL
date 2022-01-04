package Utility;

public class ColorsUtility {
	public static float[] e60000 = RGPToFloat(230, 0, 0);
	public static float[] ff6666 = RGPToFloat(255, 102, 102);
	public static float[] e6e600 = RGPToFloat(230, 230, 0);
	public static float[] BLACK = RGPToFloat(0, 0, 0);
	public static float[] BLue_3366ff = RGPToFloat(51, 102, 255);
	public static float[] White = RGPToFloat(255, 255, 255);

	public static float[] YELLOW = RGPToFloat(255, 255, 0);
	public static float[] MAGENTA = RGPToFloat(255, 0, 255);
	public static float[] CYAN = RGPToFloat(0, 255, 255);
	public static float[] RED = RGPToFloat(255, 0, 0);
	public static float[] GREEN = RGPToFloat(0, 255, 0);
	public static float[] BLUE = RGPToFloat(0, 0, 255);

	private static float[][] colors = new float[][] { RED, GREEN, BLUE, CYAN, MAGENTA, YELLOW };

	public static float[] RGPToFloat(int r, int g, int b) {
		return new float[] { r / 255.0f, g / 255.0f, b / 255.0f };
	}

	public static float[] RGPToFloat(int[] rgb) {
		return new float[] { rgb[0] / 255.0f, rgb[1] / 255.0f, rgb[2] / 255.0f };
	}

	public static float[] RandonColor() {
		return RGPToFloat(getRandomNumber(0, 255), getRandomNumber(0, 255), getRandomNumber(0, 255));
	}

	public static float[] RandonColorFromBasickColors() {
		return colors[getRandomNumber(0, 5)];
	}

	private static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static float[] checkColor(float r, float g, float b) {
		if (0 <= r && r <= 1 && 0 <= g && g <= 1 && 0 <= b && b <= 1) {
			return new float[] { r, g, b };
		} else {
			return new float[] { 0, 0, 1 };
		}
	}

	public static float[] checkColor(float[] color) {
		if (color != null && color.length == 3) {
			return checkColor(color[0], color[1], color[2]);
		} else {
			return new float[] { 0, 0, 1 };
		}
	}

}
