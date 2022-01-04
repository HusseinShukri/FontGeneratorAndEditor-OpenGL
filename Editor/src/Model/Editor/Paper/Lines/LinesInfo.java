package Model.Editor.Paper.Lines;

import java.util.LinkedList;

import Model.Basecore.Line.Line;

public class LinesInfo {
	private LinkedList<Line> lines;

	public LinesInfo() {
		super();
		this.lines = new LinkedList<Line>();
	}

	public LinesInfo(LinkedList<Line> lines) {
		super();
		this.lines = lines;
	}

	public LinkedList<Line> getLines() {
		return lines;
	}

	public void setLines(LinkedList<Line> lines) {
		if (lines != null) {
			this.lines = lines;
		}
	}

}
