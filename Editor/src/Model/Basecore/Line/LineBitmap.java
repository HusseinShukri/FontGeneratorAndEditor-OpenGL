package Model.Basecore.Line;

import Model.Basecore.Bitmap;

public class LineBitmap {
	private Bitmap bitmap;

	public LineBitmap(Bitmap bitmap) {
		super();
		this.bitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		if (bitmap != null) {
			this.bitmap = bitmap;
		}
	}

}
