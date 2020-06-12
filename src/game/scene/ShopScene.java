package game.scene;

import java.util.ArrayList;

import game.GameManager;
import game.item.Item;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class ShopScene  extends AbstractScene{

	public ArrayList<Item> items = new ArrayList<>();	
	@Override
	public void clear() {
		currentPos = 0;
	}
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
					currentPos = 1;
					
					break;
				case 3:
					currentPos = 2;
					break;
			}
			break;
		}
		case 1:
			TextPrinter.printWithDelay(500, "미구현");
			currentPos = 0;
			break;
		case 2: 
			TextPrinter.printWithDelay(500, "미구현");
			currentPos = 0;
			break;
		
		}		
		
		
		
	}

}
