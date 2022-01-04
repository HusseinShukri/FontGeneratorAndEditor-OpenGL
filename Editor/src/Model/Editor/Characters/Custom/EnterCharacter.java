package Model.Editor.Characters.Custom;

import java.util.LinkedList;

import Model.Basecore.Point;
import Model.Editor.Characters.FontCharacter;

public class EnterCharacter extends FontCharacter implements ICustomCharcter {

	public EnterCharacter() {
		super("", new LinkedList<Point>());
	}

	@Override
	public int getCharacterWeidth() {
		return 0;
	}

	@Override
	public void setCharacterWeidth(int characterWeigth) {
	}

}
