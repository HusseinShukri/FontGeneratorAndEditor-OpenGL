package Model.JOGL;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import JOGL.EditorJOGLBottomDown;
import Models.PaperSheet;
import Models.DTO.FontDto;
import Utility.SheetUtility;

public class CanvasEditorJOGL {

	private GLCanvas sheetCanvas;
	private FPSAnimator animator;
	private EditorClassJOGL lisenner;
	private PaperSheet sheet;

	/**
	 * 
	 * @param capabilities
	 * @param fontDto
	 * @param lineSpacing
	 * @param width
	 * @param height
	 * @throws Exception
	 */
	public CanvasEditorJOGL(GLCapabilities capabilities, FontDto fontDto, int lineSpacing, int width, int height)
			throws Exception {
		this.sheetCanvas = new GLCanvas(capabilities);
		this.animator = new FPSAnimator(this.sheetCanvas, 10, true);
		this.sheet = initSheet(fontDto, lineSpacing, width, height);
		init(sheetCanvas, width, height);
	}

	private PaperSheet initSheet(FontDto fontDto, int lineSpacing, int width, int height) {
		return new PaperSheet(fontDto.fontCharacters, SheetUtility.FindLineNumber(height, 1, fontDto.characterSize),
				fontDto.characterSize, fontDto.lineToCharacterSpaceShift, fontDto.characterSize, lineSpacing, width,
				height, 0, 0);
	}

	private void init(GLCanvas sheetCanvas, int width, int height) throws Exception {
		lisenner = new EditorJOGLBottomDown(sheet);
		lisenner.setAnimator(animator);
		sheetCanvas.addGLEventListener(lisenner);
		sheetCanvas.addMouseListener(lisenner);
		sheetCanvas.addMouseMotionListener(lisenner);
		sheetCanvas.addKeyListener(lisenner);
		sheetCanvas.setSize(width, height);
	}

	public GLCanvas getSheetCanvas() {
		return this.sheetCanvas;
	}

	public FPSAnimator getAnimator() {
		return animator;
	}

	public EditorClassJOGL getLisenner() {
		return lisenner;
	}

	public void setFontColor(float r, float g, float b) {
		lisenner.SetFontColor(new float[] { r, g, b });
	}

	public void setPaperSheet(FontDto fontDto, int lineSpacing, int width, int height) throws Exception {
		int line = SheetUtility.FindLineNumber(height, 1, fontDto.characterSize);
		sheet = new PaperSheet(fontDto.fontCharacters, line, fontDto.characterSize, fontDto.lineToCharacterSpaceShift,
				fontDto.characterSize, lineSpacing, width, height, 0, 0);
		this.lisenner.setSheet(sheet);
	}

	public void setLineSpacing(int lineSpacing) {
		this.sheet.setLineToLineSpaceShift(lineSpacing);
	}
}
