package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextMaker;

public class TavernScene extends AbstractScene{

	@Override
	public void show(GameManager gm) {
		
		
		switch(currentPos) {
		case 0:{
			TextMaker.print("0. 메뉴", "1. 나가기", "2. ", "3. 주변 용병 둘러보기");
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
					currentPos = 2;
					break;
			}
			
			break;
		}
		case 2:{
			TextMaker.printFrame(3, 2, TextMaker.FRAME_IMAGE_WIDTH, TextMaker.FRAME_IMAGE_HEIGHT, true,
				RenderEnum.MAGICIAN, RenderEnum.NONE, RenderEnum.NONE,
			    RenderEnum.MAGICIAN, RenderEnum.NONE, RenderEnum.NONE);
			
			TextMaker.print("0. 메뉴", "1. 나가기");
			int in = gm.getInput().getInt(0, 1);
			switch(in){
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
				break;
			case 1:
				currentPos = 0;
				break;
			}
		}
		}
		
	}

}
