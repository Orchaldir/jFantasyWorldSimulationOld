package jfws.cp.combat;

import java.util.HashMap;
import java.util.Map;
import static jfws.cp.CombatPrototype.attribute_mgr_;

public class CharacterMgr
{
	private Map<String,Character> characters_ = new HashMap<>();
	private AttributeMgr attribute_mgr_;
	
	public CharacterMgr(AttributeMgr attribute_mgr)
	{
		attribute_mgr_ = attribute_mgr;
	}
	
	public Character create(String name)
	{
		if(name == null)
			throw new IllegalArgumentException("Argument name can not be null!");
		else if(name.isEmpty())
			throw new IllegalArgumentException("Argument name can not be empty!");
		else if(characters_.containsKey(name))
			throw new IllegalArgumentException("Character \"" + name + "\"already exists!");
		
		Character character = new Character(name, attribute_mgr_);
		
		characters_.put(name, character);
		
		return character;
	}
	
	public Character get(String name)
	{
		return characters_.get(name);
	}
}
