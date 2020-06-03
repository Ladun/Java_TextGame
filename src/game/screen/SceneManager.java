package game.screen;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
	
	public enum SceneType{
		MAIN, SHOP, ADVENTURE, ADVENTURE_SELETE,TAVERN
	}
	
	private SceneType currentScene;
	private Map<SceneType, AbstractScene> scenes;
	
	public SceneManager() {
		scenes = new HashMap<>();
		scenes.put(SceneType.MAIN, new MainScene());
		scenes.put(SceneType.SHOP, new ShopScene());
		scenes.put(SceneType.ADVENTURE, new AdventureScene());
		scenes.put(SceneType.ADVENTURE_SELETE, new AdventureSelectScene());
		scenes.put(SceneType.TAVERN, new TavernScene());
		currentScene = SceneType.MAIN;
	}
	
	public void setScene(SceneType st) {
		currentScene = st;
	}
	
	public AbstractScene getScene() {
		return scenes.get(currentScene);
	}
	
	public SceneType currentScene() {
		return currentScene;
	}

}
