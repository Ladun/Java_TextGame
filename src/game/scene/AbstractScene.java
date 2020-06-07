package game.scene;

import game.GameManager;

public abstract class AbstractScene {
	
	protected int currentPos; // 씬내에서 어떤 화면을 표시하고 있는지

	public abstract void update();
	public abstract void show(GameManager gm);
	
}
