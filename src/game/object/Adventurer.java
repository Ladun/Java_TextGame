package game.object;

import game.GameManager;
import game.file.AdventurerData;
import game.resource.RenderEnum;

public class Adventurer extends Entity implements Fightable{
	
	public Adventurer(String name) {
		super();
		info.setName(name);
	}
	public Adventurer(AdventurerData data) {
		info.setName(data.getName());		
		info = new StateInfo(data.getName(), data.getStrength(), data.getVitality(), data.getIntellect(), data.getAgility(), data.getDexterity());
		confirm();
		type = data.getType();
		health = data.getHealth();
		stress = data.getStress();
		
	}
	
	public Adventurer(RenderEnum type, StateInfo info) {
		super(info);
		this.type = type;

	}
	@Override
	public boolean attack(Fightable target, AttackInfo attackInfo) {
		
		if(Math.random() < attackInfo.getAttackPercent()) {
			target.hit(this, attackInfo.getDamage());
			return true;
		}
		return false;
		
	}	
	
	@Override
	public void hit(Fightable attacker, int damage) {
		health -= damage;
		if(health < 0) {
			active = false;
		}
	}	
	
	
	@Override
	public void update(GameManager gm) {

		
	}	
	@Override
	public String getInfo() {
		
		return "Job: " + type.toString() +"\n"+ super.getInfo();
	}
	
	public AttackInfo getDamage(int skillIdx) {
		switch(type) {
		case WARRIOR:
			if(skillIdx == 0)
				return new AttackInfo(info.getStrength() / 4, info.getStrength() / 100.0f);
			else if( skillIdx == 1)
				return new AttackInfo(info.getStrength() / 2, (info.getStrength() / 100.0f) * .85f);
			
			break;
		case MAGICIAN:

			if(skillIdx == 0)
				return new AttackInfo(info.getStrength() / 4, info.getStrength() / 100.0f);
			else if( skillIdx == 1)
				return new AttackInfo(info.getIntellect() / 2, (info.getIntellect() / 100.0f) * .85f);
			break;
		case ARCHER:
			if(skillIdx == 0)
				return new AttackInfo(info.getAgility() / 4, info.getAgility() / 100.0f);
			else if( skillIdx == 1)
				return new AttackInfo(info.getAgility(), (info.getAgility() / 100.0f) * .5f);
				
			break;
		case TREASURE_HUNTER:

			if(skillIdx == 0)
				return new AttackInfo(info.getDexterity() / 4, info.getDexterity() / 100.0f);
			break;		
		}
		return null;
	}
	
	public String[] getSkillInfo() {
		String[] strs = new String[4];
		AttackInfo atInfo;
		switch(type) {
		case WARRIOR:
			atInfo = getDamage(0);
			strs[0] = "베기( " + atInfo.getDamage() + ", " +(int)(atInfo.getAttackPercent() * 100) +"% )";
			atInfo = getDamage(1);
			strs[1] = "강타( " + atInfo.getDamage() + ", " +(int)(atInfo.getAttackPercent() * 100) +"% )";
			
			break;
		case MAGICIAN:
			atInfo = getDamage(0);
			strs[0] = "몽둥이로 치기( "+ atInfo.getDamage() + ", " +(int)(atInfo.getAttackPercent() * 100) +"% )";;
			atInfo = getDamage(1);
			strs[1] = "파이어 볼( "+ atInfo.getDamage() + ", " +(int)(atInfo.getAttackPercent() * 100) +"% )";;
				
			break;
		case ARCHER:
			atInfo = getDamage(0);
			strs[0] = "활 쏘기( "+ atInfo.getDamage() + ", " + (int)(atInfo.getAttackPercent() * 100) +"% )";;
			atInfo = getDamage(1);
			strs[1] = "약점 사격( "+ atInfo.getDamage() + ", " +(int)(atInfo.getAttackPercent() * 100) +"% )";;
				
			break;
		case TREASURE_HUNTER:
			atInfo = getDamage(0);
			strs[0] = "단검 찌르기( "+ atInfo.getDamage() + ", " +(int)(atInfo.getAttackPercent() * 100) +"% )";;
				
			break;
		}
		return strs;
	}

	@Override
	protected void confirm(){
		super.confirm();
		
		if(info.isMax(info.getStrength())) 
			type = RenderEnum.WARRIOR;		
		else if(info.isMax(info.getIntellect())) 
			type = RenderEnum.MAGICIAN;		
		else if(info.isMax(info.getAgility())) 
			type = RenderEnum.ARCHER;
		else if(info.isMax(info.getDexterity())) 
			type = RenderEnum.TREASURE_HUNTER;
	}
	
}
