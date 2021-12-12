package Model.JOGL;

import Models.PaperSheet;

public abstract class EditorClassJOGL extends ClassJOGL implements IEditorGUI {
	@Override
	public void SetFontColor(float[] rgb) {
	}

	public abstract void setSheet(PaperSheet sheet);
}
