package game.object;

public class StateInfo {
	
	private String name;

	private int strength;// 물리적인 공격력 관련, 무게가 있는 물체 드는데 관련
	private int vitality;// 생명력 관련
	private int intellect; // 마법적인 공격력 관련
	private int agility; // 회피 관련
	private int dexterity; // 활, 단검 등 무기와 관련
	
	public StateInfo() {
	}
	
	public StateInfo(String name, int strength, int vitality, int intellect, int agility, int dexterity) {
		this.name = name;
		this.strength = strength;
		this.vitality = vitality;
		this.intellect = intellect;
		this.agility = agility;
		this.dexterity = dexterity;
	}
	
	public void setRandomly() {
		strength 	= (int)(Math.random() * 100);
		vitality 	= (int)(Math.random() * 100);
		intellect 	= (int)(Math.random() * 100);
		agility 	= (int)(Math.random() * 100);
		dexterity 	= (int)(Math.random() * 100);
	}

	public String getStat() {
		return 
				"Strength: " + strength +
				"\nVitality: " + vitality +
				"\nIntellect: " + intellect + 
				"\nAgility: " + agility+ 
				"\nDexterity: " + dexterity;
	}
	
	public boolean isMax(int value) {
		if( strength <= value &&
			intellect <= value &&
			agility <= value &&
			dexterity <= value)
			return true;
		return false;	
	}

	public void  setName(String str) {
		name = str;
	}

	public static String makeName() {
		String[] randomNames = new String[] {"Eadbod","Helmund","Hilew","Ined",	"Cyne",	"Maere","Hilda","Ceolswiu",
											 "Aered", "Andes", "Sige", "Gery"};
		return randomNames[(int)(Math.random() * randomNames.length)];
	}	
		
	public String getName() {
		return name;
	}
	
	public int getStrength() {
		return strength;
	}


	public void setStrength(int strength) {
		this.strength = strength;
	}


	public int getVitality() {
		return vitality;
	}


	public void setVitality(int vitality) {
		this.vitality = vitality;
	}


	public int getIntellect() {
		return intellect;
	}


	public void setIntellect(int intellect) {
		this.intellect = intellect;
	}


	public int getAgility() {
		return agility;
	}


	public void setAgility(int agility) {
		this.agility = agility;
	}


	public int getDexterity() {
		return dexterity;
	}


	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	
	
}
