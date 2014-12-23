package jfws.cp;

import java.util.Scanner;
import jfws.cp.combat.Attack;
import jfws.cp.combat.AttackResult;
import jfws.cp.combat.Attribute;
import jfws.cp.combat.AttributeMgr;
import jfws.cp.combat.Character;
import jfws.cp.combat.CharacterMgr;
import jfws.cp.combat.Damage;
import jfws.cp.combat.Defense;
import jfws.cp.combat.Protection;
import jfws.cp.combat.Range;
import jfws.cp.combat.Skill;
import jfws.cp.combat.SkillMgr;
import jfws.cp.combat.TestMgr;
import jfws.cp.combat.health.WoundSystem;
import jfws.cp.combat.initiative.TurnBasedInitiative;
import jfws.cp.combat.map.Direction1d;
import jfws.cp.combat.map.Map1d;

public class CombatPrototype
{
	public static TestMgr test_mgr_ = new TestMgr(6);
	public static AttributeMgr attribute_mgr_ = new AttributeMgr();
	public static SkillMgr skill_mgr_ = new SkillMgr();
	public static WoundSystem wound_system_;
	public static CharacterMgr character_mgr_;
	public static TurnBasedInitiative initiative_ = new TurnBasedInitiative();
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
		
		character_mgr_ = new CharacterMgr(attribute_mgr_);
		
		Damage sword_damage = new Damage(strength, 2);
		Damage bow_damage = new Damage(null, 4);
		
		Range sword_range = new Range(1, null, 0);
		Range bow_range = new Range(200, strength, 10);
		
		Attack sword = new Attack("Sword", agility, fighting, 0, sword_damage, sword_range);
		Attack bow = new Attack("Bow", perception, shooting, 0, bow_damage, bow_range);
		
		Defense dodge = new Defense("Dodge", agility, athletics, 0);
		Defense parry = new Defense("Parry", agility, fighting, 0);
		
		Protection leather_armor = new Protection("Leather Armor", 2);
		Protection mail_armor = new Protection("Mail Armor", 2);
		Protection plate_armor = new Protection("Plate Armor", 4);
		
		Character a = character_mgr_.create("Knight");
		a.setAttributeLevel(agility, 2);
		a.setAttributeLevel(strength, 2);
		a.setSkillLevel(athletics, 2);
		a.setSkillLevel(fighting, 4);
		a.addProtection(plate_armor);
		a.addAttack(sword);
		a.addDefense(parry);
		
		Character b = character_mgr_.create("Bandit");
		b.setAttributeLevel(agility, 2);
		b.setAttributeLevel(strength, 2);
		b.setSkillLevel(athletics, 2);
		b.setSkillLevel(fighting, 2);
		b.addProtection(leather_armor);
		b.addAttack(sword);
		b.addDefense(dodge);
		
		Character c = character_mgr_.create("Archer");
		c.setAttributeLevel(agility, 2);
		c.setAttributeLevel(strength, 2);
		c.setAttributeLevel(perception, 2);
		c.setSkillLevel(athletics, 2);
		c.setSkillLevel(shooting, 2);
		c.addProtection(leather_armor);
		c.addAttack(bow);
		c.addDefense(dodge);
		
		map_ = new Map1d(10);
		map_.set(a, 1);
		map_.set(b, 2);
		map_.set(c, 8);
		map_.render();
		
		initiative_.add(a);
		initiative_.add(b);
		initiative_.add(c);
		
		while(true)
		{
			Character current = initiative_.getCurrent();
			
			if(current.getHealthComponent().isDead())
			{
				initiative_.remove(current);
				continue;
			}
			
			if(!act(current))
				continue;
			
			initiative_.update(current);
		}
	}
	
	public static boolean act(Character character)
	{
		System.out.println("");
		System.out.print(character.getName() + "'s action: ");
		
		String command = input_.nextLine();
		String[] parts = command.split(" ");
		
		if(parts[0].equals("attack"))
		{
			if(parts.length != 3)
			{
				System.err.println("Attack command is invalid!");
				return false;
			}
			
			String target_name = parts[1];
			
			if(character.getName().equals(target_name))
			{
				System.err.println("Character can't attack itself!");
				return false;
			}
			
			Character target = character_mgr_.get(target_name);
			
			if(target == null)
			{
				System.err.println("Character \"" + target_name + "\" does not exist!");
				return false;
			}
			
			String attack_name = parts[2];
			Attack attack = character.getAttack(attack_name);
			
			if(attack == null)
			{
				System.err.println(character.getName() +" does not have Attack \"" + attack_name + "\"!");
				return false;
			}
			
			if(!canAttack(character, attack, target))
				return false;
			
			Defense defense = null;
			
			while(defense == null)
			{
				System.out.print(target.getName() + "'s defense: ");
				String defense_name = input_.nextLine();
				defense = target.getDefense(defense_name);
			
				if(defense == null)
				{
					System.err.println(target.getName() +" does not have Defense \"" + defense_name + "\"!");
				}
			}
			
			attack(character, attack, target, defense);
			
			return true;
		}
		else if(parts[0].equals("move"))
		{
			if(parts.length != 2)
			{
				System.err.println("Move command is invalid!");
				return false;
			}
			
			String dir_name_ = parts[1];
			Direction1d dir;
			
			if(dir_name_.equals("left"))
			{
				dir = Direction1d.LEFT;
			}
			else if(dir_name_.equals("right"))
			{
				dir = Direction1d.RIGHT;
			}
			else
			{
				System.err.println("Invalid Direction!");
				return false;
			}
			
			if(!map_.move(character, dir))
			{
				System.err.println("Could not move!");
				return false;
			}
			
			map_.render();
			
			return true;
		}
		
		System.err.println("Unknown command!");
		
		return false;
	}
	
	public static boolean canAttack(Character attacker, Attack attack, Character defender)
	{
		int range = map_.getDistance(attacker, defender);
		int max_range = attack.getRange().getRange(attacker);
		
		if(range > max_range)
		{
			System.err.println(defender.getName()+ " is out of range!");
			
			return false;
		}
		
		return true;
	}
	
	public static void attack(Character attacker, Attack attack, Character defender, Defense defense)
	{
		System.out.println(attacker.getName()+ "'s " + attack.getName() + " VS "
				+ defender.getName()+ "'s " + defense.getName());
		
		AttackResult result = new AttackResult(attacker, attack, defender, defense);
		
		Attack.handle(test_mgr_, result);

		System.out.println("Attack: " + result.getMarginOfSuccess() + " -> " + (result.hasHit() ? "Hit" : "Miss"));

		if(!result.hasHit())
			return;

		Damage.handle(result);

		wound_system_.handle(result);

		System.out.print("Damage: " + result.getDamage());
		System.out.print(" -> " + result.getPenetratingDamage());
		System.out.println(" -> " + result.getWound().getLevel());

		System.out.println("Status: " + defender.getHealthComponent().getWoundLevel());
		
		if(defender.getHealthComponent().isDead())
		{
			onDeath(defender);
		}
	}
	
	public static void onDeath(Character character)
	{
		System.out.println(character.getName() + " dies!");
		initiative_.remove(character);
		map_.remove(character);
	}
}
