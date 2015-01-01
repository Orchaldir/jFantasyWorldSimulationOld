package jfws.cp.combat.target;

import java.util.List;
import jfws.cp.combat.Character;
import jfws.cp.combat.map.GameMap;
import jfws.cp.combat.map.Pose;

public interface TargetSelection
{
	boolean canTargetUser(Character user, GameMap map);
	boolean canTargetCharacter(Character user, Character target, GameMap map);
	boolean canTargetPosition(Character user, Pose pose, GameMap map);
	
	List<Character> targetUser(Character user, GameMap map);
	List<Character> targetCharacter(Character user, Character target, GameMap map);
	List<Character> targetPosition(Character user, Pose pose, GameMap map);
}
