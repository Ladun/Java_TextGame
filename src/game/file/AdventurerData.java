package game.file;

import java.io.Serializable;

import game.object.Adventurer;
import game.resource.RenderEnum;

public class AdventurerData implements Serializable{

	private boolean isNull = true;
	
	private String name;

	private int strength;// �������� ���ݷ� ����, ���԰� �ִ� ��ü ��µ� ����
	private int vitality;// ����� ����
	private int intellect; // �������� ���ݷ� ����
	private int agility; // ȸ�� ����
	private int dexterity; // Ȱ, �ܰ� �� ����� ����

	private RenderEnum type;

	private int health;
	private int stress;
	
	public void setting(Adventurer adv) {
		if(adv == null) return;
		
		isNull = false;
		name = adv.getStateInfo().getName();		
		strength = adv.getStateInfo().getStrength();
		vitality = adv.getStateInfo().getVitality();
		intellect = adv.getStateInfo().getIntellect();
		agility = adv.getStateInfo().getAgility();
		dexterity = adv.getStateInfo().getDexterity();
		type = adv.getType();
		
		health = adv.getHealth();
		stress = adv.getStress();
		
	}

	public boolean isNull() {
		return isNull;
	}

	public String getName() {
		return name;
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

	public RenderEnum getType() {
		return type;
	}

	public int getHealth() {
		return health;
	}

	public int getStress() {
		return stress;
	}
	
	
}
