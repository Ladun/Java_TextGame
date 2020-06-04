package game.screen;

import game.GameManager;
import game.resource.RenderEnum;
import game.resource.Resources;

public class TavernScene extends AbstractScene{

	@Override
	public void show(GameManager gm) {
		
		Resources.print(3, 2, 
				RenderEnum.MAGICIAN, RenderEnum.NONE, RenderEnum.NONE,
			    RenderEnum.MAGICIAN, RenderEnum.NONE, RenderEnum.NONE);
		
		
		int in = gm.getInput().getInt(1, 3);
	}

}
