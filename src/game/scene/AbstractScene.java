package game.screen;

import game.GameManager;

public abstract class AbstractScene {
	
	protected int currentPos; // �������� � ȭ���� ǥ���ϰ� �ִ���

	public abstract void show(GameManager gm);
	
}
