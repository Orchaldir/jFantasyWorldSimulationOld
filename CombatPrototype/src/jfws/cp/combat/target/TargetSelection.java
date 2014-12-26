package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap;

public interface TargetSelection
{
	List<Character> getTargets(Character user);
	List<Character> getTargets(Character user, Character target, GameMap map);
}
