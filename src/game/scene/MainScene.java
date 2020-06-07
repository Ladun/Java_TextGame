package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextMaker;

public class MainScene extends AbstractScene {

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub

		TextMaker.printWithTag(RenderEnum.TAG_MAIN);
		TextMaker.print("0. 메뉴", "1. 모험 가기","2. 상점 가기","3. 선술집 가기");
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
