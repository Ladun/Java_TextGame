package game.scene;

import java.util.HashMap;
import java.util.Map;

import game.GameManager;
import game.scene.adventure.AdventureScene;

public class SceneManager {
	
	public enum SceneType{
		MAIN, SHOP, ADVENTURE, ADVENTURE_SELETE,TAVERN, OPTION
	}
	
	private SceneType currentScene, preScene;
	private Map<SceneType, AbstractScene> scenes;
	
	public SceneManager() {
		scenes = new HashMap<>();
		scenes.put(SceneType.MAIN, new MainScene());
		scenes.put(SceneType.SHOP, new ShopScene());
		scenes.put(SceneType.ADVENTURE, new AdventureScene());
		scenes.put(SceneType.ADVENTURE_SELETE, new AdventureSelectScene());
		scenes.put(SceneType.TAVERN, new TavernScene());
		scenes.put(SceneType.OPTION, new OptionScene());
		currentScene = SceneType.MAIN;
	}
	
	public void update() {
		((TavernScene)scenes.get(SceneType.TAVERN)).update();
	}
	
	public void setScene(SceneType st) {
		preScene = currentScene;
		currentScene = st;
	}
	
	public AbstractScene getScene() {
		return scenes.get(currentScene);
	}
	
	public <T extends AbstractScene> T getScene(SceneType st) {
		
		return (T)scenes.get(st);
	}
	
	public SceneType getCurrentScene() {
		return currentScene;
	}

	public SceneType getPreviousScene() {
		return preScene;
	}
}
