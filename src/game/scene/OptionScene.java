package game.scene;

import game.GameManager;
import game.object.Adventurer;
import game.resource.Resources;

public class OptionScene extends AbstractScene {

	@Override
	public void show(GameManager gm) {


		switch(currentPos){
		case 0:{
			Resources.print("0. 나가기", "1. 내 상태");

			int in = gm.getInput().getInt(0, 1);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(gm.getSceneManager().getPreviousScene());
				break;
			case 1:
				Adventurer player = gm.getPlayInfo().getPlayer();
				
				//TODO: makeFrame 함수를 수정해서 원하는 크기의 사각형을 만들 수 있게
				Resources.print(
						"Name: " + player.getName(),
						"HP: " + player.getHealth() + "/" + player.getMaxHealth(),
						"Stress: " + player.getStress() + "/" + player.getMaxStress(),
						"Strength: " + player.getStrength(),
						"Vitality: " + player.getVitality(), 
						"Intellect: " + player.getIntellect(), 
						"Agility: " + player.getAgility(), 
						"Dexterity: " + player.getDexterity());
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
