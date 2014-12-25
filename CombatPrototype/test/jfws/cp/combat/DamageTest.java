
package jfws.cp.combat;

import jfws.cp.combat.value.ConstantValue;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class DamageTest
{
	static private Character character_ = new Character("Character0", 1);
	static private int base_damage_ = 3;
	static private int margin_of_success_ = 4;
	static private int toal_damage_ = base_damage_ + margin_of_success_;
	static private Damage damage_;
	
	@Before
	public void setUp()
	{
		damage_ = new Damage(new ConstantValue(base_damage_));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithNull()
	{
		new Damage(null);
	}
	
	@Test
	public void testGetBaseDamage()
	{
		assertEquals(base_damage_, damage_.getBaseDamage(character_));
	}

	@Test
	public void testGetDamage()
	{
		assertEquals(toal_damage_, damage_.getDamage(character_, margin_of_success_));
	}

	@Test
	public void testHandle()
	{
		AttackResult result = new AttackResult(character_, null, null, null);
		result.setMarginOfSuccess(margin_of_success_);
		
		damage_.handle(result);
		
		assertEquals(toal_damage_, result.getDamage());
	}
}