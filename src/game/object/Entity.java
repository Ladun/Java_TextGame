package game.object;

import game.resource.RenderEnum;

public abstract class Entity extends GameObject {

	protected StateInfo info;
	protected RenderEnum type;

	protected int maxHealth;
	protected int health;
	protected int maxStress;
	protected int stress;

	public Entity() {
		info = new StateInfo();
		setRandomly();
	}

	public Entity(StateInfo info) {
		this.info = info;
		confirm();
	}

	public void setRandomly() {
		info.setRandomly();
		confirm();
	}

	protected void confirm() {

		active =true;
		maxHealth = (int) (info.getVitality() * 1.5f) + 10;
		health = maxHealth;

		maxStress = info.getIntellect() * 2;
		stress = 0;
	}

	public String getInfo() {
		return 
				"Name: " + info.getName() + "\n" +
				"HP: " + health + "/" + maxHealth + 
				"\nStress: " + stress + "/" + maxStress + 
				"\n" + info.getStat();
	}
	
	public StateInfo getStateInfo() {
		return info;
	}
	
	public void heal() {
		health = maxHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxStress() {
		return maxStress;
	}

	public int getStress() {
		return stress;
	}
	public RenderEnum getType() {
		return type;
	}

}
