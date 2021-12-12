package JOGL.models;

import java.util.HashMap;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import JOGL.FontInputJOGL;
import JOGL.FontOutputJOGL;
import Models.CellsBox;
import Models.CellsBoxList;

public class CanvasFontJOGL {
	private CellsBox box;
	private CellsBoxList list;

	private GLCanvas outputCanvas;
	private FPSAnimator outAnimator;
	private FontClassJOGL outputLisenner;

	private GLCanvas inputCanvas;
	private FPSAnimator inAnimator;
	private FontClassJOGL inputLisenner;

	public CanvasFontJOGL(GLCapabilities capabilities, CellsBox box, int outWidth, int outHeight, int inWidth,
			int inHeight) throws Exception {
		this.box = box;
		this.list = new CellsBoxList();

		this.outputCanvas = new GLCanvas(capabilities);
		this.outAnimator = new FPSAnimator(this.outputCanvas, 10, true);

		this.inputCanvas = new GLCanvas(capabilities);
		this.inAnimator = new FPSAnimator(this.inputCanvas, 10, true);

		initOut(outWidth, outHeight);
		initIn(inWidth, inHeight);
	}

	private void initOut(int width, int height) throws Exception {
		outputLisenner = new FontOutputJOGL(box);
		outputLisenner.setAnimator(outAnimator);
		outputCanvas.addGLEventListener(outputLisenner);
		outputCanvas.addMouseListener(outputLisenner);
		outputCanvas.addMouseMotionListener(outputLisenner);
		outputCanvas.setSize(width, height);
	}

	private void initIn(int width, int height) throws Exception {
		inputLisenner = new FontInputJOGL(box);
		inputLisenner.setAnimator(inAnimator);
		inputCanvas.addGLEventListener(inputLisenner);
		inputCanvas.addMouseListener(inputLisenner);
		inputCanvas.addMouseMotionListener(inputLisenner);
		inputCanvas.setSize(width, height);
	}

	public GLCanvas getOutputCanvas() {
		return outputCanvas;
	}

	public FPSAnimator getOutAnimator() {
		return outAnimator;
	}

	public GLCanvas getInputCanvas() {
		return inputCanvas;
	}

	public FPSAnimator getInAnimator() {
		return inAnimator;
	}

	public FontClassJOGL getOutputLisenner() {
		return outputLisenner;
	}

	public FontClassJOGL getInputLisenner() {
		return inputLisenner;
	}

	public int getMaxLineCellNumber() {
		return this.box.getCellsNumber();
	}
	
	public void setLineCellNumber(int i) {
		this.box.setLineCellNumber(i);
		this.list.updateAllLineCellnumber(i);
	}

	public void addToList(String str,int lineCellNumber) {
		this.list.add(str, box);
		this.box = new CellsBox(box.getCellsNumber(), box.getX(), box.getY(), box.getWidth(), box.getHight());
		this.box.setLineCellNumber(lineCellNumber);
		updateLisennerBox(box);
	}

	public void removeFromList(String str) {
		this.list.remove(str);
	}

	public boolean isInList(String str) {
		if (this.list.contatin(str)) {
			return true;
		}
		return false;
	}

	public void newBox() {
		this.box = new CellsBox(box.getCellsNumber(), box.getX(), box.getY(), box.getWidth(), box.getHight());
		updateLisennerBox(box);
	}

	public void setCurrent(String str) {
		if (this.list.contatin(str)) {
			this.box = this.list.get(str);
			updateLisennerBox(box);
		}
	}

	private void updateLisennerBox(CellsBox box) {
		this.outputLisenner.setBox(box);
		this.inputLisenner.setBox(box);
	}

	public void resetAll() {
		newBox();
		this.list.clearList();
	}

	public HashMap<String, CellsBox> getMap() {
		return this.list.getMap();
	}

	// box cells # \n
	// box line cell #
	// string,(x,y),(x,y)... \n
	// string,(x,y),(x,y)... \n
	// ....
	@Override
	public String toString() {
		return (box.getCellsNumber() + "\n" + box.getLineCellNumber() + "\n" + list.toString());
	}
}
