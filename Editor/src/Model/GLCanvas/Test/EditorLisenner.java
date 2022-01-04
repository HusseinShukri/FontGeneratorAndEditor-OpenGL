package Model.GLCanvas.Test;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import Observable.IObservable;
import Observable.IObserver;

public class EditorLisenner extends Lisenners implements IObservable {

	private LinkedList<IObserver> observers;
	private boolean newChar = false;
	public int keyEvent;//

	public EditorLisenner() {
		super();
		this.observers = new LinkedList<IObserver>();
		this.keyEvent = -1;
	}

	@Override
	public void add(IObserver observer) {
		if (observer != null) {
			this.observers.add(observer);
		}
	}

	@Override
	public void remove(IObserver observer) {
		if (observer != null) {
			this.observers.remove(observer);
		}

	}

	@Override
	public void notifys() {
		for (IObserver observer : observers) {
			observer.update();
		}
		newChar = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			keyEvent = KeyEvent.VK_ENTER;
			newChar = true;
		} else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			keyEvent = KeyEvent.VK_BACK_SPACE;
			newChar = true;
		} else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			keyEvent = KeyEvent.VK_SPACE;
			newChar = true;
		}
		if (newChar) {
			this.notifys();
		}
	}

}
