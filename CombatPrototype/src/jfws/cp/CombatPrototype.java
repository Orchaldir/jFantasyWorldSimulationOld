package jfws.cp;

import java.util.Scanner;
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
import jfws.cp.combat.map.Direction1d;
import jfws.cp.combat.map.Map1d;

public class CombatPrototype
{
	public static TestMgr test_mgr_ = new TestMgr(6);
	public static AttributeMgr attribute_mgr_ = new AttributeMgr();
	public static SkillMgr skill_mgr_ = new SkillMgr();
	public static WoundSystem wound_system_;
	public static Map1d map_;
	
	public static Scanner input_ = new Scanner(System.in);
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Combat Prototype");
		System.out.println("----------------\n");
		
		Attribute agility = attribute_mgr_.createAttribute("Agility");
		Attribute perception = attribute_mgr_.createAttribute("Perception");
		Attribute strength = attribute_mgr_.createAttribute("Strength");
		
		Skill athletics = skill_mgr_.createSkill("Athletics", -2);
		Skill fighting = skill_mgr_.createSkill("Fighting", -2);
		Skill shooting = skill_mgr_.createSkill("Shooting", -2);
		
		wound_system_ = new WoundSystem(strength, 2);
		
		Damage swing_damage = new Damage(strength, 2);
		Damage shoot_damage = new Damage(null, 8);
		
		Attack swing = new Attack("Swing", agility, fighting, 0, swing_damage);
		Attack shoot = new Attack("Shoot", perception, shooting, 0, shoot_damage);
		
		Defense dodge = new Defense("Dodge", agility, athletics, 0);
		Defense parry = new Defense("Parry", agility, fighting, 0);
		
		Protection leather_armor = new Protection("Leather Armor", 2);
		Protection mail_armor = new Protection("Mail Armor", 2);
		Protection plate_armor = new Protection("Plate Armor", 4);
		
		Character a = new Character("Knight", attribute_mgr_);
		a.setAttributeLevel(agility, 2);
		a.setAttributeLevel(strength, 2);
		a.setSkillLevel(athletics, 2);
		a.setSkillLevel(fighting, 4);
		a.addProtection(plate_armor);
		a.addAttack(swing);
		
		Character b = new Character("Bandit", attribute_mgr_);
		b.setAttributeLevel(agility, 2);
		b.setAttributeLevel(strength, 2);
		b.setSkillLevel(athletics, 2);
		b.setSkillLevel(fighting, 2);
		b.addProtection(leather_armor);
		b.addAttack(swing);
		
		map_ = new Map1d(10);
		map_.setCharacter(a, 1);
		map_.setCharacter(b, 8);
		map_.render();
		
		while(true)
		{
			if(act(a, b, dodge))
				return;
			
			if(act(b, a, parry))
				return;
		}
	}
	
	public static boolean act(Character attacker, Character defender, Defense defense)
	{
		System.out.println("");
		System.out.print(attacker.getName() + "'s action: ");
		
		String command = input_.nextLine();
		String[] parts = command.split(" ");
		
		if(parts[0].equals("attack"))
		{
			if(parts.length != 2)
			{
				System.err.println("Attack command is invalid!");
				return false;
			}
				
			Attack attack = attacker.getAttack(parts[1]);
			
			if(attack == null)
			{
				System.err.println(attacker.getName() +" does not have Attack \"" + parts[1] + "\"!");
				return false;
			}
			
			return attack(attacker, attack, defender, defense);
		}
		else if(parts[0].equals("move"))
		{
			if(parts.length != 2)
			{
				System.err.println("Move command is invalid!");
				return false;
			}
			
			Direction1d dir;
			
			if(parts[1].equals("left"))
			{
				dir = Direction1d.LEFT;
			}
			else if(parts[1].equals("right"))
			{
				dir = Direction1d.RIGHT;
			}
			else
			{
				System.err.println("Invalid Direction!");
				return false;
			}
			
			if(!map_.moveCharacter(attacker, dir))
			{
				System.err.println("Could not move!");
			}
			
			map_.render();
			
			return false;
		}
		
		System.err.println("Unknown command!");
		
		return false;
	}
	
	public static boolean attack(Character attacker, Attack attack, Character defender, Defense defense)
	{
		System.out.println(attacker.getName()+ "'s " + attack.getName() + " VS "
				+  defender.getName()+ "'s " + defense.getName());
		
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
