package game.object;

public interface Fightable {
	
	public abstract boolean attack(Fightable target, AttackInfo attackInfo);
	public abstract void hit(Fightable attacker, int damage);

}
