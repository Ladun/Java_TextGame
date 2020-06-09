package game.object;

import game.GameManager;
import game.resource.RenderEnum;

public class Adventurer extends Entity{
	
	private RenderEnum type;
	private String name;
	
	public Adventurer(String name) {
		super();
		this.name = name;
	}
	
	public Adventurer(String name, RenderEnum type,int strength, int vitality, int intellect, int agility, int dexterity) {
		super(strength, vitality, intellect, agility, dexterity);
		this.type = type;
		this.name = name;

	}

	@Override
	public void update(GameManager gm) {

		
	}	

	@Override
	protected void confirm(){
		super.confirm();
		
		// 밑에 오버라이드 된거 지우고 능력치에 따라 직업 설정
	}
	@Override
	public void setRandomly() {
		super.setRandomly();
		RenderEnum[] values = RenderEnum.values();
		type = values[(int)(Math.random() * 4)];
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
				"Name: " + name + "\n" +
				super.getInfo();
	}
}
