package Model.GLCanvas;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;

import Model.Dto.FontDto;
import Model.Editor.Paper.Paper;
import Utility.ColorsUtility;
import Utility.SheetUtility;

public class CanvasEditorJOGL {

	private GLCanvas glCanvas;
	private EditorClassJOGL glLisenner;
//	private EditorLisenner editorLisenner;

	/**
	 * 
	 * @param capabilities
	 * @param fontDto
	 * @param lineSpacing
	 * @param width
	 * @param height
	 * @throws Exception
	 */
	public CanvasEditorJOGL(GLCapabilities capabilities, FontDto fontDto, int width, int height) throws Exception {
		this.glCanvas = new GLCanvas(capabilities);
//		this.editorLisenner = new EditorLisenner();
		initCanvas(glCanvas, fontDto, width, height);
	}

	private void initCanvas(GLCanvas glCanvas, FontDto fontDto, int width, int height) throws Exception {
		int lineCount = SheetUtility.FindLineNumber(height, 0, fontDto.FontHeight);
//		Paper paper = new Paper(editorLisenner,fontDto, lineCount, width, height, 0, 0);//
//		editorLisenner.add(paper);//
		Paper paper = new Paper(fontDto, lineCount, width, height, 0, 0);//
		glLisenner = new EditorJOGL(paper);//
		glCanvas.addGLEventListener(glLisenner);
		glCanvas.addMouseListener(glLisenner);
		glCanvas.addMouseMotionListener(glLisenner);
		glCanvas.addKeyListener(glLisenner);//
		glCanvas.addMouseWheelListener(glLisenner);
		glCanvas.setSize(width, height);
	}

	public GLCanvas getCanvas() {
		return this.glCanvas;
	}

	public EditorClassJOGL getGlLisenner() {
		return glLisenner;
	}

	public void setLineSpacing(int value) {
		this.glLisenner.setLineSpacing(value);
	}

	public void setFontColor(int r, int g, int b) {
		this.glLisenner.SetFontColor(ColorsUtility.RGPToFloat(r, g, b));
	}

	public void setFont(FontDto fontDto) {
		this.glLisenner.setFont(fontDto);
	}
}
