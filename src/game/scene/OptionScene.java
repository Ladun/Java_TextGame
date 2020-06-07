package game.scene;

import game.GameManager;
import game.object.Adventurer;
import game.util.TextMaker;

public class OptionScene extends AbstractScene {



	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void show(GameManager gm) {


		switch(currentPos){
		case 0:{
			TextMaker.print("0. 나가기", "1. 정보");

			int in = gm.getInput().getInt(0, 1);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(gm.getSceneManager().getPreviousScene());
				break;
			case 1:
				Adventurer player = gm.getPlayInfo().getPlayer();
				
				TextMaker.printFrame(1, 1, 16, 8, false,
						player.getInfo());
				TextMaker.printFrame(1, 1, 16, 2, false,
						"Money: ");
				break;
			}
			break;
		}
		case 1:{
			
			break;
		}
			
		}

	}

}
