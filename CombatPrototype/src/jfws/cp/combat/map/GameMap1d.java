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
	
	@Override
	public Collection<Character> getCharacters()
	{
		return poses_.keySet();
	}
	
	// characters
	
	@Override
	public Character getCharacter(Pose pose)
	{
		if(!isValid(pose.index_))
			throw new IllegalArgumentException("Pose is outside map!");
		
		return cells_[pose.index_];
	}
	
	@Override
	public boolean setCharacter(Character character, int index)
	{
		if(character == null)
		{
			throw new IllegalArgumentException("Character cannot be null!");
		}
		
		if(!isValid(index))
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
	
	@Override
	public void removeCharacter(Character character)
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
	
	// checks
	
	@Override
	public boolean isValid(int index)
	{
		return index >= 0 && index < cells_.length;
	}
	
	@Override
	public boolean isFree(int index)
	{
		return cells_[index] == null;
	}
	
	// index
	
	public int getLeft(int index)
	{
		return index - 1;
	}
	
	public int getRight(int index)
	{
		return index + 1;
	}
	
	@Override
	public int getNeighborIndex(int index, Direction dir)
	{
		if(dir == Direction.WEST)
			return getLeft(index);
		else if(dir == Direction.EAST)
			return getRight(index);
		else
			throw new IllegalArgumentException("Unsupported Direction!");
	}
	
	// distance
	
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
	
	// pose
	
	@Override
	public Pose getPose(Character character)
	{
		return poses_.get(character);
	}
	
	// rest
	
	public void print()
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
