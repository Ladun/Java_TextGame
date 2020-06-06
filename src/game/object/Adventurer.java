package game.object;

import game.GameManager;

public class Adventurer extends Entity{
	

	private String name;
	
	public Adventurer(String name,int strength, int vitality, int intellect, int agility, int dexterity) {
		super(strength, vitality, intellect, agility, dexterity);
		this.name = name;

	}

	@Override
	public void update(GameManager gm) {

		
	}

	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return 
				"Name: " + name +
				"\nHP: " + health + "/" + maxHealth +
				"\nStress: " + stress + "/" + maxStress +
				"\nStrength: " + strength +
				"\nVitality: " + vitality +
				"\nIntellect: " + intellect + 
				"\nAgility: " + agility+ 
				"\nDexterity: " + dexterity;
	}
}
