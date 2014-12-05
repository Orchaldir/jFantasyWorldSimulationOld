package jfws.cp;

import jfws.cp.combat.Attack;
import jfws.cp.combat.Character;
import jfws.cp.combat.Defense;
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
		
		TestMgr test_mgr = new TestMgr(10);
		
		SkillMgr skill_mgr = new SkillMgr();
		Skill athletics = skill_mgr.createSkill("Athletics", -2);
		Skill fighting = skill_mgr.createSkill("Fighting", -2);
		Skill shooting = skill_mgr.createSkill("Shooting", -4);
		
		Attack swing = new Attack("Swing", fighting, 0);
		Attack shoot = new Attack("Shoot", shooting, 0);
		
		Defense dodge = new Defense("Dodge", athletics, 0);
		Defense parry = new Defense("Parry", fighting, 0);
		
		Character character = new Character();
		character.setSkillLevel(athletics, 4);
		character.setSkillLevel(fighting, 6);
		
		System.out.println(athletics.getName() + ": " + character.getSkillLevel(athletics));
		System.out.println(fighting.getName() + ": " + character.getSkillLevel(fighting));
		System.out.println(shooting.getName() + ": " + character.getSkillLevel(shooting));
		System.out.println("");
		System.out.println(swing.getName() + ": " + swing.getAttackValue(character));
		System.out.println(shoot.getName() + ": " + shoot.getAttackValue(character));
	}
	
}
