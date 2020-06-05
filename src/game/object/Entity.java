package game.object;

public abstract class Entity extends GameObject{
	

	// ��, ����, ��ø �� ��
	protected int strength;// �������� ���ݷ� ����, ���԰� �ִ� ��ü ��µ� ����
	protected int vitality;// ����� ����
	protected int intellect; // �������� ���ݷ� ����
	protected int agility; // ȸ�� ����
	protected int dexterity; // Ȱ, �ܰ� �� ����� ����
	
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
