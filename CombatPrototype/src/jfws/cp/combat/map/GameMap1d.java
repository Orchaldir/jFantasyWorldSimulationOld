package jfws.cp.combat.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import jfws.cp.combat.Character;

public class GameMap1d implements GameMap
{
	private Map<Character,Pose> poses_ = new HashMap<>();
	private Character[] cells_;
	
	public GameMap1d(int size)
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
	
	@Override
	public Collection<Character> getCharacters()
	{
		return poses_.keySet();
	}
	
	public int getDistance(int a, int b)
	{
		return Math.abs(b - a);
	}
	
	@Override
	public int getDistance(Character a, Character b)
	{
		Pose pose_a = poses_.get(a);
		Pose pose_b = poses_.get(b);
		
		return getDistance(pose_a.index_, pose_b.index_);
	}
	
	@Override
	public int getDistance(Character a, Pose b)
	{
		Pose pose_a = poses_.get(a);
		
		return getDistance(pose_a.index_, b.index_);
	}
	
	@Override
	public int getDistance(Pose a, Pose b)
	{
		return getDistance(a.index_, b.index_);
	}
	
	@Override
	public Pose getPose(Character character)
	{
		return poses_.get(character);
	}
	
	public boolean isInside(int index)
	{
		return index >= 0 && index < cells_.length;
	}
	
	public boolean isFree(int index)
	{
		return cells_[index] == null;
	}
	
	public boolean set(Character character, int index)
	{
		if(character == null)
		{
			throw new IllegalArgumentException("Character cannot be null!");
		}
		
		if(!isInside(index))
			return false;
		
		if(!isFree(index))
			return false;
		
		Pose pose = poses_.get(character);
		
		if(pose == null)
		{
			pose = new Pose(index);
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
	
	public boolean move(Character character, Direction1d dir)
	{
		if(character == null)
		{
			throw new IllegalArgumentException("Character cannot be null!");
		}
		
		Pose pose = poses_.get(character);
		
		if(pose == null)
		{
			throw new IllegalArgumentException("Character has no Pose1d!");
		}
		
		int new_index = getNeighbor(pose.index_, dir);
		
		return set(character, new_index);
	}
	
	public void remove(Character character)
	{
		if(character == null)
		{
			throw new IllegalArgumentException("Character cannot be null!");
		}
		
		for(int i = 0; i < cells_.length; i++)
		{
			if(cells_[i] == character)
				cells_[i] = null;
		}
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
