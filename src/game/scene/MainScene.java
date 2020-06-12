package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class MainScene extends AbstractScene {
	@Override
	public void clear() {
		
	}

	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub

		TextPrinter.printWithTag(RenderEnum.TAG_MAIN);
		TextPrinter.print("메뉴", "모험 가기","상점 가기","선술집 가기");
		int	in = gm.getInput().getInt(0, 3);		
		
		switch(in) {
		case 0:
			gm.getSceneManager().setScene(SceneType.OPTION);				
			break;
		case 1:
			gm.getSceneManager().setScene(SceneType.ADVENTURE_SELETE);	
			break;
		case 2:
			gm.getSceneManager().setScene(SceneType.SHOP);
			break;
		case 3:
			gm.getSceneManager().setScene(SceneType.TAVERN);
			break;
		}
	}

}
