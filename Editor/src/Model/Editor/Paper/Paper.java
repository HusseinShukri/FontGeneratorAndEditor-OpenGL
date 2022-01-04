package Model.Editor.Paper;

import java.util.LinkedList;
import java.awt.event.KeyEvent;

import Model.Basecore.Bitmap;
import Model.Dto.FontDto;
import Model.Dto.ToDrawtDto;
import Model.Editor.Characters.FontCharacter;
import Model.Editor.Cursor.Cursor;
import Model.Editor.Paper.Font.FontInfo;
import Model.Editor.Paper.Lines.LinesInfo;
import Model.Editor.Paper.Page.PageInfo;
import Model.Editor.Paper.Text.PageText;
import Model.Editor.Paper.Text.ParagraphInfo;
import Model.GLCanvas.Test.EditorLisenner;
import Observable.IObserver;
import Utility.ColorsUtility;

public class Paper implements IObserver {
	private FontInfo fontInfo;
	private PageInfo pageInfo;
	private ParagraphInfo paragraphInfo;
	private LinesInfo linesInfo;
	private PageText pageText;
	private Cursor cursor;
	private float[] CurrentColor;

	private EditorLisenner editorLisenner;//

	/**
	 * 
	 * @param fontDto
	 * @param linesCount
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 */
	public Paper(FontDto fontDto, int linesCount, int width, int height, int x, int y) {
		super();
//		this.editorLisenner = editorLisenner;//
		this.fontInfo = new FontInfo(fontDto.FontName, fontDto.FontHeight, fontDto.FontWidth,
				fontDto.FontCharactersSet);
		this.pageInfo = new PageInfo(linesCount, width, height, x, y);
		this.paragraphInfo = new ParagraphInfo(0, fontDto.FontWidth, fontDto.lineCharacterSpacing);
		this.pageText = new PageText(0);
		this.cursor = new Cursor(CurrentColor, this.paragraphInfo.getLineSpacing(), fontDto.FontHeight,
				this.pageText.getCurrentLine(), 2);
		this.CurrentColor = new float[] { 0, 0, 0 };
		this.linesInfo = new LinesInfo(Utility.LineUtility.getLinesList(new float[] { 0, 0, 1 }, fontDto.FontHeight,
				paragraphInfo.getLineSpacing(), pageInfo.getLinesCount(), width));
	}
	
	public Paper(EditorLisenner editorLisenner, FontDto fontDto, int linesCount, int width, int height, int x, int y) {
		super();
		this.editorLisenner = editorLisenner;//
		this.fontInfo = new FontInfo(fontDto.FontName, fontDto.FontHeight, fontDto.FontWidth,
				fontDto.FontCharactersSet);
		this.pageInfo = new PageInfo(linesCount, width, height, x, y);
		this.paragraphInfo = new ParagraphInfo(0, fontDto.FontWidth, fontDto.lineCharacterSpacing);
		this.pageText = new PageText(0);
		this.cursor = new Cursor(CurrentColor, this.paragraphInfo.getLineSpacing(), fontDto.FontHeight,
				this.pageText.getCurrentLine(), 2);
		this.CurrentColor = new float[] { 0, 0, 0 };
		this.linesInfo = new LinesInfo(Utility.LineUtility.getLinesList(new float[] { 0, 0, 1 }, fontDto.FontHeight,
				paragraphInfo.getLineSpacing(), pageInfo.getLinesCount(), width));
	}

	// Updates
	private void updateCursor() {
		this.cursor.updateCoordinates(pageText.getCurrentDisplacment(fontInfo.getCharacterWedth()),
				this.paragraphInfo.getLineSpacing(), fontInfo.getCharacterWedth(), this.pageText.getCurrentLine());
	}

	private void updateCourserLineSpacing(int oldLineSpacing, int newlineSpacing) {
		this.cursor.updateLineSpacing(oldLineSpacing, newlineSpacing, this.fontInfo.getCharacterHeight(),
				this.pageText.getCurrentLine());
	}

	private void updateLines() {
		this.linesInfo.setLines(Utility.LineUtility.getLinesList(new float[] { 0, 0, 1 }, fontInfo.getCharacterHeight(),
				paragraphInfo.getLineSpacing(), pageInfo.getLinesCount(), pageInfo.getWidth()));
	}

	private void updateText(int oldLineSpacing, int newlineSpacing) {
		this.pageText.updateLineSpacingText(this.pageInfo.getLinesCount(), oldLineSpacing, newlineSpacing,
				paragraphInfo.getLineCharacterSpacing(), fontInfo.getCharacterHeight());
	}

	/// setters and modifiers

	public void addCharacterToText(FontCharacter character) {
		this.pageText.addCharacter(this.CurrentColor, this.pageInfo.getWidth(), this.pageInfo.getLinesCount(),
				this.paragraphInfo.getLineSpacing(), this.paragraphInfo.getLineCharacterSpacing(), character,
				this.fontInfo.getCharacterWedth(), this.fontInfo.getCharacterHeight());
		updateCursor();
	}

	public void addLineToText() {
		this.pageText.newLine(this.pageInfo.getLinesCount());
		updateCursor();
	}

	public void addBackSpaceToText() {
		this.pageText.backSpace();
		updateCursor();
	}

	public void addSpaceToText() {
		this.pageText.space(this.CurrentColor, this.pageInfo.getWidth(), this.pageInfo.getLinesCount(),
				this.paragraphInfo.getLineSpacing(), this.paragraphInfo.getLineCharacterSpacing(),
				this.fontInfo.getCharacterWedth(), this.fontInfo.getCharacterWedth(),
				this.fontInfo.getCharacterHeight());
		updateCursor();
	}

	public void addUpToText() {
		this.pageText.arrawUp();
		updateCursor();
	}

	public void addDownToText() {
		this.pageText.arrawDown(this.pageInfo.getLinesCount());
		updateCursor();
	}

	public void setLineSpacing(int value) {
		updateText(this.paragraphInfo.getLineSpacing(), value);
		updateCourserLineSpacing(this.paragraphInfo.getLineSpacing(), value);
		this.paragraphInfo.setLineSpacing(value);
		updateLines();
	}

	public void setCurrentColor(float[] color) {
		CurrentColor = ColorsUtility.checkColor(color);
	}

	public void setNewFont(FontDto fontDto) {
		this.fontInfo.updateFont(fontDto.FontName, fontDto.FontHeight, fontDto.FontWidth, fontDto.FontCharactersSet);
	}

	//// getters

	public ToDrawtDto getToDraw() {
		// bitmaps
		LinkedList<Bitmap> bitmaps = new LinkedList<Bitmap>();
		bitmaps.add(cursor.getBitmap());
		bitmaps.addAll(pageText.getTextBitmap());
		// bitmaps
		return new ToDrawtDto(bitmaps, null, linesInfo.getLines());
	}

	public FontInfo getFontInfo() {
		return fontInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public ParagraphInfo getParagraphInfo() {
		return paragraphInfo;
	}

	public LinesInfo getLinesInfo() {
		return linesInfo;
	}

	public PageText getPageText() {
		return pageText;
	}

	public Cursor getCursor() {
		return cursor;
	}

	public float[] getCurrentColor() {
		return CurrentColor;
	}

	@Override
	public void update() {
		System.out.println("here");
		int code = this.editorLisenner.keyEvent;
		if (code == KeyEvent.VK_ENTER) {
			this.addLineToText();
		} else if (code == KeyEvent.VK_BACK_SPACE) {
			this.addBackSpaceToText();
		} else if (code == KeyEvent.VK_SPACE) {
			this.addSpaceToText();
		}

	}

}
