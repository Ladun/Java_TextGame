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
	
	public Entity() {
		setRandomly();
		confirm();
	}

	public Entity(int strength, int vitality, int intellect, int agility, int dexterity) {
		this.strength = strength;
		this.vitality = vitality;
		this.intellect = intellect;
		this.agility = agility;
		this.dexterity = dexterity;
		confirm();
	}
	
	private void confirm() {

		maxHealth = (int)(strength * 1.5f);
		health = maxHealth;
		
		maxStress = intellect;
		stress = 0;	
	}
	
	public void setRandomly() {
		strength 	= (int)(Math.random() * 100);
		vitality 	= (int)(Math.random() * 100);
		intellect 	= (int)(Math.random() * 100);
		agility 	= (int)(Math.random() * 100);
		dexterity 	= (int)(Math.random() * 100);
	}
	
	public String getInfo() {
		return 				
				"\nHP: " + health + "/" + maxHealth +
				"\nStress: " + stress + "/" + maxStress +
				getStat();
	}

	public String getStat() {
		return 
				"\nStrength: " + strength +
				"\nVitality: " + vitality +
				"\nIntellect: " + intellect + 
				"\nAgility: " + agility+ 
				"\nDexterity: " + dexterity;
	}

	public int getStrength() {
		return strength;
	}

	public int getVitality() {
		return vitality;
	}

	public int getIntellect() {
		return intellect;
	}

	public int getAgility() {
		return agility;
	}

	public int getDexterity() {
		return dexterity;
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
	
	
}
