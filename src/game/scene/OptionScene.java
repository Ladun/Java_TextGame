package game.scene;

import game.GameManager;
import game.resource.GridFrame;
import game.util.TextPrinter;

public class OptionScene extends AbstractScene {


	private GridFrame adventurerStateFrame;
	private GridFrame stateFrame;
	
	public OptionScene() {
		adventurerStateFrame = new GridFrame(1, 3, 19, false);
		stateFrame = new GridFrame(1, 1, 16, false);
	}
	
	@Override
	public void show(GameManager gm) {


		switch(currentPos){
		case 0:{
			TextPrinter.print("0. 뒤로가기", "1. 정보", "2. 게임 종료");

			int in = gm.getInput().getInt(0, 2);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(gm.getSceneManager().getPreviousScene());
				break;
			case 1:
				adventurerStateFrame.setting(gm.getPlayInfo().getAdventurerInfo());
				TextPrinter.printFrame(adventurerStateFrame);
				stateFrame.setting("Money: ");
				TextPrinter.printFrame(stateFrame);
				
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
