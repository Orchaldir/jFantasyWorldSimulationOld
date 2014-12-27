package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap;

public interface TargetSelection
{
	boolean canTarget(Character user, GameMap map);
	boolean canTarget(Character user, Character target, GameMap map);
	
	List<Character> getTargets(Character user, GameMap map);
	List<Character> getTargets(Character user, Character target, GameMap map);
}
