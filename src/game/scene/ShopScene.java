package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextMaker;

public class ShopScene  extends AbstractScene{


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub
		
		TextMaker.printWithTag(RenderEnum.TAG_SHOP);
		switch(currentPos) {
		case 0:{
			TextMaker.print("0. 메뉴", "1. 나가기", "2. 장비 구매","3. 모험 물품 구매");

			int in = gm.getInput().getInt(0, 3);
			switch(in){
				case 0:
					gm.getSceneManager().setScene(SceneType.OPTION);
					break;
				case 1:
					gm.getSceneManager().setScene(SceneType.MAIN);
					break;
				case 2:
					break;
				case 3:
					break;
			}
			break;
		}
		case 1:
			break;
		case 2: 
			
			break;
		
		}		
		
		
		
	}

}
