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
	
	public int getLeft(int index)
	{
		return index - 1;
	}
	
	public int getRight(int index)
	{
		return index + 1;
	}
	
	public int getNeighbor(int index, Direction1d dir)
	{
		if(dir == Direction1d.LEFT)
			return getLeft(index);
		else
			return getRight(index);
	}
	
	public int getDistance(int a, int b)
	{
		return Math.abs(b - a);
	}
	
	public int getDistance(Character a, Character b)
	{
		Pose1d pose_a = poses_.get(a);
		Pose1d pose_b = poses_.get(b);
		
		return getDistance(pose_a.index_, pose_b.index_);
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
			cells_[pose.index_] = null;
			pose.index_ = index;
		}
		
		cells_[index] = character;
		
		return true;
	}
	
	public boolean moveCharacter(Character character, Direction1d dir)
	{
		if(character == null)
		{
			throw new IllegalArgumentException("Character cannot be null!");
		}
		
		Pose1d pose = poses_.get(character);
		
		if(pose == null)
		{
			throw new IllegalArgumentException("Character has no Pose1d!");
		}
		
		int new_index = getNeighbor(pose.index_, dir);
		
		return setCharacter(character, new_index);
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
