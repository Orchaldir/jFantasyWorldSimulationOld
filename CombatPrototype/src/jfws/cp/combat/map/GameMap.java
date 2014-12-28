package jfws.cp.combat.map;

import java.util.Collection;
import jfws.cp.combat.Character;

public interface GameMap
{
	Character getCharacter(Pose pose);
	Collection<Character> getCharacters();
	
	int getDistance(Character a, Character b);
	int getDistance(Character a, Pose b);
	int getDistance(Pose a, Pose b);
	
	Pose getPose(Character character);
}
