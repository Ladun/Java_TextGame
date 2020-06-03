package game.screen;

import game.GameManager;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.screen.SceneManager.SceneType;

public class MainScene extends AbstractScene {

	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub

		Resources.print(RenderEnum.TAG_MAIN);
		Resources.print("1. ���� ����","2. ���� ����","3. ������ ����");
		
		int in = gm.getInput().getInt(1, 3);
		
		switch(in) {
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
