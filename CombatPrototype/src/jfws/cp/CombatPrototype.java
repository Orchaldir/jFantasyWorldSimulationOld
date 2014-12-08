package jfws.cp;

import jfws.cp.combat.Attack;
import jfws.cp.combat.AttackResult;
import jfws.cp.combat.Attribute;
import jfws.cp.combat.AttributeMgr;
import jfws.cp.combat.Character;
import jfws.cp.combat.Damage;
import jfws.cp.combat.Defense;
import jfws.cp.combat.Protection;
import jfws.cp.combat.Skill;
import jfws.cp.combat.SkillMgr;
import jfws.cp.combat.TestMgr;
import jfws.cp.combat.health.WoundSystem;

public class CombatPrototype
{
	public static TestMgr test_mgr_ = new TestMgr(6);
	public static WoundSystem wound_system_;
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Combat Prototype");
		System.out.println("----------------\n");
		
		AttributeMgr attribute_mgr = new AttributeMgr();
		Attribute agility = attribute_mgr.createAttribute("Agility");
		Attribute perception = attribute_mgr.createAttribute("Perception");
		Attribute strength = attribute_mgr.createAttribute("Strength");
		
		SkillMgr skill_mgr = new SkillMgr();
		Skill athletics = skill_mgr.createSkill("Athletics", -2);
		Skill fighting = skill_mgr.createSkill("Fighting", -2);
		Skill shooting = skill_mgr.createSkill("Shooting", -2);
		
		wound_system_ = new WoundSystem(strength);
		
		Damage swing_damage = new Damage(strength, 2);
		Damage shoot_damage = new Damage(null, 8);
		
		Attack swing = new Attack("Swing", agility, fighting, 0, swing_damage);
		Attack shoot = new Attack("Shoot", perception, shooting, 0, shoot_damage);
		
		Defense dodge = new Defense("Dodge", agility, athletics, 0);
		Defense parry = new Defense("Parry", agility, fighting, 0);
		
		Protection leather_armor = new Protection("Leather Armor", 2);
		Protection mail_armor = new Protection("Mail Armor", 2);
		Protection plate_armor = new Protection("Plate Armor", 4);
		
		Character a = new Character("Knight", attribute_mgr);
		a.setAttributeLevel(agility, 2);
		a.setAttributeLevel(strength, 2);
		a.setSkillLevel(athletics, 2);
		a.setSkillLevel(fighting, 4);
		a.addProtection(plate_armor);
		
		Character b = new Character("Bandit", attribute_mgr);
		b.setAttributeLevel(agility, 2);
		b.setAttributeLevel(strength, 2);
		b.setSkillLevel(athletics, 2);
		b.setSkillLevel(fighting, 2);
		b.addProtection(leather_armor);
		
		while(true)
		{
			if(attack(a, swing, b, dodge))
				return;
			
			if(attack(b, swing, a, parry))
				return;
		}
	}
	
	public static boolean attack(Character attacker, Attack attack, Character defender, Defense defense)
	{
		System.out.println("");
		System.out.println(attacker.getName() + " attacks " +  defender.getName());
		
		AttackResult result = new AttackResult(attacker, attack, defender, defense);
		
		Attack.handle(test_mgr_, result);

		System.out.println("Attack: " + result.getMarginOfSuccess() + " -> " + (result.hasHit() ? "Hit" : "Miss"));

		if(!result.hasHit())
			return false;

		Damage.handle(result);

		wound_system_.handle(result);

		System.out.print("Damage: " + result.getDamage());
		System.out.print(" -> " + result.getPenetratingDamage());
		System.out.println(" -> " + result.getWound().getLevel());

		System.out.println("Status: " + defender.getWoundComponent().getHighestWoundLevel());
		
		if(defender.getWoundComponent().isDead())
		{
			System.out.println(defender.getName() + " dies!");
			return true;
		}
		
		return false;
	}
}
