package game.scene;

import game.GameManager;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.scene.SceneManager.SceneType;

public class ShopScene  extends AbstractScene{

	
	
	@Override
	public void show(GameManager gm) {
		// TODO Auto-generated method stub
		
		Resources.print(RenderEnum.TAG_SHOP);
		switch(currentPos) {
		case 0:{
			Resources.print("0. �޴�", "1. ������", "2. ��� ����","3. ���� ��ǰ ����");

			int in = gm.getInput().getInt(0, 2);
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
