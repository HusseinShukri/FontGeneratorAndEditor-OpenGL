package Model.GLCanvas;

import Model.Dto.FontDto;

public interface IEditorGUI {
	void SetFontColor(float[] RGB);

	void setLineSpacing(int value);

	void setFont(FontDto fontDto);
}
