package Model.Editor.Characters.Custom;

import java.util.LinkedList;

import Model.Basecore.Point;
import Model.Editor.Characters.FontCharacter;

public class SpaceCharacter extends FontCharacter implements ICustomCharcter {
	private int characterWeidth;

	public SpaceCharacter() {
		super(" ", new LinkedList<Point>());
		this.characterWeidth = 0;
	}

	public SpaceCharacter(int characterWeidth) {
		super(" ", new LinkedList<Point>());
		this.characterWeidth = characterWeidth;
	}

	@Override
	public int getCharacterWeidth() {
		return characterWeidth;
	}

	@Override
	public void setCharacterWeidth(int characterWeigth) {
		if (characterWeigth >= 0) {
			this.characterWeidth = characterWeigth;
		}
	}

}
