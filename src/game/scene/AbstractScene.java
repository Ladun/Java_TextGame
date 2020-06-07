package game.scene;

import game.GameManager;

public abstract class AbstractScene {
	
	protected int currentPos; // �������� � ȭ���� ǥ���ϰ� �ִ���

	public abstract void update();
	public abstract void show(GameManager gm);
	
}
