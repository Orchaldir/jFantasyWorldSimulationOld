package jfws.cp.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jfws.cp.combat.health.WoundComponent;

public class Character
{
	private String name_;
	
	private List<Integer> attributes_;
	private Map<Skill,Integer> skills_ = new HashMap<>();
	
	private List<Protection> protection_list_ = new ArrayList<>();
	
	private WoundComponent wound_component_ = new WoundComponent();
	
	public Character(String name, AttributeMgr attribute_mgr)
	{
		name_ = name;
		
		attributes_ = new ArrayList<>(attribute_mgr.getNumberOfAttributes());
		
		for(int i = 0; i < attribute_mgr.getNumberOfAttributes(); i++)
		{
			attributes_.add(0);
		}
	}
	
	public String getName()
	{
		return name_;
	}
	
	public int getAttributeLevel(Attribute attribute)
	{
		return attributes_.get(attribute.getId());
	}
	
	public void setAttributeLevel(Attribute attribute, int level)
	{
		attributes_.set(attribute.getId(), level);
	}
	
	public int getSkillLevel(Skill skill)
	{
		if(skills_.containsKey(skill))
			return skills_.get(skill);
		
		return skill.getDefaultLevel();
	}
	
	public void setSkillLevel(Skill skill, int level)
	{
		skills_.put(skill, level);
	}
	
	public void addProtection(Protection protection)
	{
		protection_list_.add(protection);
	}
	
	public void removeProtection(Protection protection)
	{
		protection_list_.remove(protection);
	}
	
	public int getProtectionValue()
	{
		int value = 0;
		
		for(Protection protection : protection_list_)
		{
			value += protection.getProtectionValue();
		}
		
		return value;
	}
	
	public WoundComponent getWoundComponent()
	{
		return wound_component_;
	}
}
