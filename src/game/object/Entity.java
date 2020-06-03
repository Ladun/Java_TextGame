package game.object;

public abstract class Entity extends GameObject{
	

	// 힘, 지능, 민첩 등 등
	protected int strength;// 물리적인 공격력 관련, 무게가 있는 물체 드는데 관련
	protected int vitality;// 생명력 관련
	protected int intellect; // 마법적인 공격력 관련
	protected int agility; // 회피 관련
	protected int dexterity; // 활, 단검 등 무기와 관련
	
	protected int maxHealth;
	protected int health;
	protected int maxStress;
	protected int stress;

	public Entity(int strength, int vitality, int intellect, int agility, int dexterity) {
		this.strength = strength;
		this.vitality = vitality;
		this.intellect = intellect;
		this.agility = agility;
		this.dexterity = dexterity;
		
		maxHealth = (int)(strength * 1.5f);
		health = maxHealth;
		
		maxStress = intellect;
		stress = 0;		
	}
	
}
