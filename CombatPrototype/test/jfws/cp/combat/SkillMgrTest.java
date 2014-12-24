package jfws.cp.combat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkillMgrTest
{
	static private String name_ = "Skill";
	static private int level_ = 9;
	static private SkillMgr skill_mgr_;
	
	@Before
	public void setUp()
	{
		skill_mgr_ = new SkillMgr();
	}

	@Test
	public void testCreateSkill()
	{
		Skill skill = skill_mgr_.createSkill(name_, level_);
		
		assertNotNull(skill);
		assertEquals(name_, skill.getName());
		assertEquals(level_, skill.getDefaultLevel());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateSkillWithNull()
	{
		skill_mgr_.createSkill(null, level_);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateSkillWithEmptyString()
	{
		skill_mgr_.createSkill("", level_);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateSkillTwice()
	{
		skill_mgr_.createSkill(name_, level_);
		skill_mgr_.createSkill(name_, level_);
	}

	@Test
	public void testGetSkill()
	{
		Skill skill = skill_mgr_.createSkill(name_, level_);
		
		assertEquals(skill, skill_mgr_.getSkill(name_));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetSkillWithNull()
	{
		skill_mgr_.getSkill(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetSkillWithEmptyString()
	{
		skill_mgr_.getSkill("");
	}
}
