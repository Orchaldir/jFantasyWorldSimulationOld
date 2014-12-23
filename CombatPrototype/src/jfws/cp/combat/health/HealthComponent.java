package jfws.cp.combat.health;

import jfws.cp.combat.AttackResult;

public interface HealthComponent
{
	void applyDamage(AttackResult result);

	WoundLevel getWoundLevel();

	boolean isAlive();
	boolean isDead();
}
