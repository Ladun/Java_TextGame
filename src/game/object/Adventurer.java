package game.object;

import game.GameManager;
import game.resource.RenderEnum;

public class Adventurer extends Entity{
	
	private RenderEnum type;
	private String name;
	
	public Adventurer(String name) {
		super();
		this.name = name;
		type = RenderEnum.MAGICIAN;
	}
	
	public Adventurer(String name,int strength, int vitality, int intellect, int agility, int dexterity) {
		super(strength, vitality, intellect, agility, dexterity);
		this.name = name;

	}

	@Override
	public void update(GameManager gm) {

		
	}
	
	public static String makeName() {
		return "No Name";
	}
	
	public String getName() {
		return name;
	}
	
	public RenderEnum getType() {
		return type;
	}	
	
	
	@Override
	public String getInfo() {
		return 
				"Name: " + name +
				super.getInfo();
	}
}
