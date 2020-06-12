package game.object;

import game.GameManager;
import game.resource.RenderEnum;

public class Enemy extends Entity implements Fightable {
	
	public Enemy(StateInfo info) {
		super(info);
		
		if(info.getName().equals("Goblin")) {
			type = RenderEnum.GOBLIN;
		}
		
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
	public int getMaxSkill() {
		switch(type) {
		case GOBLIN:
			return 2;			
		}
		return 0;
	}
	public AttackInfo getDamage(int skillIdx) {
		switch(type) {
		case GOBLIN:
			if(skillIdx == 0)
				return new AttackInfo(info.getStrength() / 4, info.getStrength() / 100.0f);
			else if( skillIdx == 1)
				return new AttackInfo(info.getStrength() / 2, (info.getStrength() / 100.0f) * .85f);
			
			break;	
		}
		return null;
	}

}
