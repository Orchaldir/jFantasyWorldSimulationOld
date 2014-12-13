package jfws.cp.combat.map;

import java.util.HashMap;
import java.util.Map;
import jfws.cp.combat.Character;

public class Map1d
{
	private Map<Character,Pose1d> poses_ = new HashMap<>();
	private Character[] cells_;
	
	public Map1d(int size)
	{
		cells_ = new Character[size];
	}
	
	public boolean isInside(int index)
	{
		return index >= 0 && index < cells_.length;
	}
	
	public boolean isFree(int index)
	{
		return cells_[index] == null;
	}
	
	public boolean setCharacter(Character character, int index)
	{
		if(!isInside(index))
			return false;
		
		if(!isFree(index))
			return false;
		
		Pose1d pose = poses_.get(character);
		
		if(pose == null)
		{
			pose = new Pose1d(index);
			poses_.put(character, pose);
		}
		else
		{
			pose.index_ = index;
		}
		
		cells_[index] = character;
		
		return true;
	}
	
	public void render()
	{
		System.out.print("Battlefield: ");
		
		for(Character character : cells_)
		{
			if(character == null)
			{
				System.out.print(".");
			}
			else
			{
				System.out.print(character.getName().charAt(0));
			}
		}
		
		System.out.print("\n");
	}
}
