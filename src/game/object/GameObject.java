package game.object;

import game.GameManager;

public abstract class GameObject {

	protected String tag;
	protected float posX,posY;
	protected int currentPlace;
	protected boolean active = true;
	
	public abstract void update(GameManager gm);
	
	public String getTag() {
		return tag;
	}
	public float getPosX() {
		return posX;
	}
	public float getPosY() {
		return posY;
	}
	public boolean isActive() {
		return active;
	}
	
	
}
