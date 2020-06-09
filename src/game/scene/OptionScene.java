package game.scene;

import game.GameManager;
import game.object.Adventurer;
import game.util.TextPrinter;

public class OptionScene extends AbstractScene {


	@Override
	public void show(GameManager gm) {


		switch(currentPos){
		case 0:{
			TextPrinter.print("0. �ڷΰ���", "1. ����", "2. ���� ����");

			int in = gm.getInput().getInt(0, 2);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(gm.getSceneManager().getPreviousScene());
				break;
			case 1:
				
				TextPrinter.printFrame(3, 1, 19, false,
						gm.getPlayInfo().getAdventurerInfo());
				TextPrinter.printFrame(1, 1, 16, false,
						"Money: ");
				break;
			case 2:
				gm.setRunning(false);
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
