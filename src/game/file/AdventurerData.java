package game.file;

import java.io.Serializable;

import game.object.Adventurer;
import game.resource.RenderEnum;

public class AdventurerData implements Serializable{

	private boolean isNull = true;
	
	private String name;

	private int strength;// 물리적인 공격력 관련, 무게가 있는 물체 드는데 관련
	private int vitality;// 생명력 관련
	private int intellect; // 마법적인 공격력 관련
	private int agility; // 회피 관련
	private int dexterity; // 활, 단검 등 무기와 관련

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
