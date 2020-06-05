package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.scene.SceneManager.SceneType;

public class TavernScene extends AbstractScene{

	@Override
	public void show(GameManager gm) {
		
		
		switch(currentPos) {
		case 0:{
			Resources.print("0. 메뉴", "1. 나가기", "2. ", "3. 주변 용병 둘러보기");
			int in = gm.getInput().getInt(0, 3);

			switch(in){
				case 0:
					gm.getSceneManager().setScene(SceneType.OPTION);
					break;
				case 1:
					gm.getSceneManager().setScene(SceneType.MAIN);
					break;
				case 2:
					currentPos = 2;
					break;
			}
			
			break;
		}
		case 2:{
		Resources.print(3, 2, 
				RenderEnum.MAGICIAN, RenderEnum.NONE, RenderEnum.NONE,
			    RenderEnum.MAGICIAN, RenderEnum.NONE, RenderEnum.NONE);
		}
		}
		
		
	}

}
