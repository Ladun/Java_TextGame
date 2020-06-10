package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class ShopScene  extends AbstractScene{


	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub
		
		TextPrinter.printWithTag(RenderEnum.TAG_SHOP);
		switch(currentPos) {
		case 0:{
			TextPrinter.print("메뉴", "나가기", "장비 구매","모험 물품 구매");

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
