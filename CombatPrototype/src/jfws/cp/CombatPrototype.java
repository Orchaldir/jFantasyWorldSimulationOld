package jfws.cp;

import jfws.cp.combat.Character;
import jfws.cp.combat.Skill;
import jfws.cp.combat.SkillMgr;

public class CombatPrototype
{
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Combat Prototype");
		System.out.println("----------------\n");
		
		SkillMgr skill_mgr = new SkillMgr();
		Skill fighting = skill_mgr.createSkill("Fighting", -2);
		Skill shooting = skill_mgr.createSkill("Shooting", -4);
		
		Character character = new Character();
		character.setSkillLevel(fighting, 6);
		
		System.out.println(fighting.getName() + ": " + character.getSkillLevel(fighting));
		System.out.println(shooting.getName() + ": " + character.getSkillLevel(shooting));
	}
	
}
