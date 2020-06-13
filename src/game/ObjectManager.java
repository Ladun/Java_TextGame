package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game.object.Enemy;
import game.object.StateInfo;
import game.scene.adventure.MapData;
import game.scene.adventure.MapData.MapType;

public class ObjectManager {
	
	private Map<MapData.MapType,ArrayList<StateInfo>> enemyMap = new HashMap<>();
	
	
	public ObjectManager() {
		ArrayList<StateInfo> forestEnemy = new ArrayList<>();
		forestEnemy.add(new StateInfo("Goblin",60, 40, 15, 50, 40));
		
		enemyMap.put(MapType.FOREST, forestEnemy);
	}
	
	public ArrayList<StateInfo> getEnemyState(MapData.MapType livingArea) {
		return enemyMap.get(livingArea);
	}

}
