package game.object;

public class AttackInfo {
	private int damage;
	private float attackPercent;
		
	public AttackInfo(int damage, float attackPercent) {
		this.damage = damage;
		this.attackPercent = attackPercent;
	}
	
	public int getDamage() {
		return damage;
	}
	public float getAttackPercent() {
		return attackPercent;
	}
	
	
}
