package game.scene;

import game.GameManager;
import game.resource.GridFrame;
import game.util.TextPrinter;

public class OptionScene extends AbstractScene {


	private GridFrame adventurerStateFrame;
	private GridFrame stateFrame;
	
	public OptionScene() {
		adventurerStateFrame = new GridFrame(1, 3, 19, false);
		stateFrame = new GridFrame(1, 1, 57, false);
	}
	
	@Override
	public void show(GameManager gm) {


		switch(currentPos){
		case 0:{
			TextPrinter.print("뒤로가기", "정보", "게임 종료");

			int in = gm.getInput().getInt(0, 2);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(gm.getSceneManager().getPreviousScene());
				break;
			case 1:
				adventurerStateFrame.setting(gm.getPlayInfo().getAdventurerInfo());
				TextPrinter.printFrame(adventurerStateFrame);
				stateFrame.setting(
						"Time: "+ gm.getDays() + "일 " + gm.getTimes() + "시\n" + 
						"Money: ");
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
