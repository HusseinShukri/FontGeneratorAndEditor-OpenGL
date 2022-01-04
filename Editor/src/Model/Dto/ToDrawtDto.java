package Model.Dto;

import java.util.LinkedList;

import Model.Basecore.Bitmap;
import Model.Basecore.Line.Line;
import Model.Basecore.Line.LineBitmap;

public class ToDrawtDto {
	public LinkedList<Bitmap> bitmaps;
	public LinkedList<LineBitmap> lineBitmaps;
	public LinkedList<Line> lines;

	public ToDrawtDto() {
		super();
	}

	public ToDrawtDto(LinkedList<Bitmap> bitmaps, LinkedList<LineBitmap> lineBitmaps, LinkedList<Line> lines) {
		super();
		this.bitmaps = bitmaps;
		this.lineBitmaps = lineBitmaps;
		this.lines = lines;
	}

}
