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

public class CombatPrototype
{
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Combat Prototype");
		System.out.println("----------------\n");
		
		TestMgr test_mgr = new TestMgr(6);
		
		AttributeMgr attribute_mgr = new AttributeMgr();
		Attribute agility = attribute_mgr.createAttribute("Agility");
		Attribute perception = attribute_mgr.createAttribute("Perception");
		Attribute strength = attribute_mgr.createAttribute("Strength");
		
		SkillMgr skill_mgr = new SkillMgr();
		Skill athletics = skill_mgr.createSkill("Athletics", -2);
		Skill fighting = skill_mgr.createSkill("Fighting", -2);
		Skill shooting = skill_mgr.createSkill("Shooting", -2);
		
		Damage swing_damage = new Damage(strength, 2);
		Damage shoot_damage = new Damage(null, 8);
		
		Attack swing = new Attack("Swing", agility, fighting, 0, swing_damage);
		Attack shoot = new Attack("Shoot", perception, shooting, 0, shoot_damage);
		
		Defense dodge = new Defense("Dodge", agility, athletics, 0);
		Defense parry = new Defense("Parry", agility, fighting, 0);
		
		Protection mail_armor = new Protection("Mail Armor", 4);
		
		Character character = new Character(attribute_mgr);
		character.setAttributeLevel(agility, 1);
		character.setAttributeLevel(strength, 3);
		character.setSkillLevel(athletics, 2);
		character.setSkillLevel(fighting, 3);
		character.addProtection(mail_armor);
		
		System.out.println(swing.getName() + ": " + swing.getAttackValue(character));
		System.out.println(shoot.getName() + ": " + shoot.getAttackValue(character));
		System.out.println("");
		System.out.println(dodge.getName() + ": " + dodge.getDefenseValue(character));
		System.out.println(parry.getName() + ": " + parry.getDefenseValue(character));
		System.out.println("");
		
		AttackResult result = new AttackResult(character, swing, character, dodge);
		
		for(int i = 0; i < 10; i++)
		{
			Attack.handle(test_mgr, result);
			
			System.out.println("Attack: " + result.getMarginOfSuccess() + " -> " + (result.hasHit() ? "Hit" : "Miss"));
			
			if(!result.hasHit())
				continue;
			
			Damage.handle(result);
			
			System.out.println("Damage: " + result.getDamage());
		}
	}
	
}
