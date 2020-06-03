package game.screen;

import game.GameManager;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.screen.SceneManager.SceneType;

public class ShopScene  extends AbstractScene{

	
	
	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub
		
		Resources.print(RenderEnum.TAG_SHOP);
		switch(currentPos) {
		case 0:
			Resources.print("1. 장비 구매","2. 모험 물품 구매", "3. 상점 나가기");
			break;
		
		}
		
		int in = gm.getInput().getInt(1, 3);
		
		
		switch(in){
			case 1:
				break;
			case 2:
				break;
			case 3:
				gm.getSceneManager().setScene(SceneType.MAIN);
				break;
		}
		
	}

}
