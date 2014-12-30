package jfws.cp.combat.map;

import java.util.Collection;
import jfws.cp.combat.Character;

public interface GameMap
{
	Collection<Character> getCharacters();
	
	// characters
	Character getCharacter(Pose pose);
	boolean setCharacter(Character character, int index);
	void removeCharacter(Character character);
	
	// checks
	boolean isValid(int index);
	boolean isFree(int index);
	
	// distance
	int getDistance(Character a, Character b);
	int getDistance(Character a, Pose b);
	int getDistance(Pose a, Pose b);
	
	// index
	int getNeighborIndex(int index, Direction dir);
	
	// pose
	Pose getPose(Character character);
}
