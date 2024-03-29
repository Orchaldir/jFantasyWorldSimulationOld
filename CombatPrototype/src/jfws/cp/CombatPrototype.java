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
import jfws.cp.combat.action.Move;
import jfws.cp.combat.health.WoundSystem;
import jfws.cp.combat.initiative.TurnBasedInitiative;
import jfws.cp.combat.map.Direction;
import jfws.cp.combat.map.GameMap1d;
import jfws.cp.combat.value.*;

public class CombatPrototype
{
	public static TestMgr test_mgr_ = new TestMgr(6);
	public static AttributeMgr attribute_mgr_ = new AttributeMgr();
	public static SkillMgr skill_mgr_ = new SkillMgr();
	public static WoundSystem wound_system_;
	public static CharacterMgr character_mgr_;
	public static TurnBasedInitiative initiative_ = new TurnBasedInitiative();
	public static GameMap1d map_;
	public static Move move_left_ = new Move("Move Left", Direction.WEST);
	public static Move move_right_ = new Move("Move Right", Direction.EAST);
	
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
		
		Damage sword_damage = new Damage(new AttributeValue(strength, 2));
		Damage bow_damage = new Damage(new ConstantValue(4));
		
		Range sword_range = new Range(1);
		Range bow_range = new Range(200, new AttributeValue(strength), 10);
		
		Value sword_value = new AttributeSkillCombo(agility, fighting);
		Value bow_value = new AttributeSkillCombo(perception, shooting);
		
		Attack sword = new Attack("Sword", sword_value, sword_damage, sword_range);
		Attack bow = new Attack("Bow", bow_value, bow_damage, bow_range);
		
		Value dodge_value = new AttributeSkillCombo(agility, athletics);
		Value parry_value = new AttributeSkillCombo(agility, fighting);
		
		Defense dodge = new Defense("Dodge", dodge_value);
		Defense parry = new Defense("Parry", parry_value);
		
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
		
		map_ = new GameMap1d(10);
		map_.setCharacter(a, 1);
		map_.setCharacter(b, 2);
		map_.setCharacter(c, 8);
		map_.print();
		
		initiative_.add(a);
		initiative_.add(b);
		initiative_.add(c);
		
		while(true)
		{
			Character current = initiative_.getCurrent();
			
			if(!processInput(current))
				continue;
			
			initiative_.update(current);
		}
	}
	
	public static boolean processInput(Character character)
	{
		System.out.println("");
		System.out.print(character.getName() + "'s action: ");
		
		String command = input_.nextLine();
		String[] parts = command.split(" ");
		
		if(parts[0].equals("attack"))
		{
			return processAttack(character, parts);
		}
		else if(parts[0].equals("move"))
		{
			return processMove(character, parts);
		}
		
		System.err.println("Unknown command!");
		
		return false;
	}
	
	public static boolean processAttack(Character attacker, String[] parts)
	{
		if(parts.length != 3)
		{
			System.err.println("Attack command is invalid!");
			return false;
		}

		String target_name = parts[1];

		if(attacker.getName().equals(target_name))
		{
			System.err.println("Character can't attack itself!");
			return false;
		}

		Character defender = character_mgr_.get(target_name);

		if(defender == null)
		{
			System.err.println("Character \"" + target_name + "\" does not exist!");
			return false;
		}

		String attack_name = parts[2];
		Attack attack = attacker.getAttack(attack_name);

		if(attack == null)
		{
			System.err.println(attacker.getName() + " does not have Attack \"" + attack_name + "\"!");
			return false;
		}

		Defense defense = processDefense(defender, attack);

		if(defense == null)
			return false;
		
		AttackResult result = new AttackResult(attacker, attack, defender, defense);
		
		if(!attack.isPossible(result, map_))
			return false;
		
		attack(result);
		
		result.print();

		return true;
	}
	
	public static Defense processDefense(Character target, Attack attack)
	{
		Defense defense = null;

		while(defense == null)
		{
			System.out.print(target.getName() + "'s defense: ");
			String defense_name = input_.nextLine();
			
			if(defense_name.isEmpty())
			{
				defense = target.getBestDefense(attack);
			}
			else if(defense_name.equals("back"))
			{
				return null;
			}
			else
			{
				defense = target.getDefense(defense_name);
			}

			if(defense == null)
				System.err.println(target.getName() + " does not have Defense \"" + defense_name + "\"!");
		}
		
		return defense;
	}
	
	public static boolean processMove(Character character, String[] parts)
	{
		if(parts.length != 2)
		{
			System.err.println("Move command is invalid!");
			return false;
		}

		String dir_name_ = parts[1];
		Move move;

		if(dir_name_.equals("left"))
		{
			move = move_left_;
		}
		else if(dir_name_.equals("right"))
		{
			move = move_right_;
		}
		else
		{
			System.err.println("Invalid Direction!");
			return false;
		}

		if(!move.isPossible(character, map_))
		{
			System.err.println("Could not move!");
			return false;
		}
		
		move.handle(character, map_);

		map_.print();

		return true;
	}
	
	public static void attack(AttackResult result)
	{
		Attack.handle(test_mgr_, result);

		if(!result.hasHit())
			return;

		result.getAttack().getDamage().handle(result);

		wound_system_.handle(result);
		
		checkDeath(result.getDefender());
	}
	
	public static void checkDeath(Character character)
	{
		if(character.getHealthComponent().isDead())
		{
			onDeath(character);
		}
	}
	
	public static void onDeath(Character character)
	{
		System.out.println(character.getName() + " dies!");
		initiative_.remove(character);
		map_.removeCharacter(character);
	}
}
