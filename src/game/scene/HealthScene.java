package game.scene;

import game.GameManager;
import game.object.Entity;
import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class HealthScene  extends AbstractScene {

	private GridFrame adventurerStateFrame;
	
	public HealthScene() {
		adventurerStateFrame = new GridFrame(1, 3, 23, false);
	}
	@Override
	public void clear() {
		currentPos = 0;
	}

	@Override
	public void show(GameManager gm) {
		
		switch(currentPos) {
		case 0:{
			TextPrinter.printWithTag(RenderEnum.TAG_HEALTH);
			TextPrinter.print("메뉴", "나기기", "용병 치료");

			int in = gm.getInput().getInt(0, 2);
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
			}
			break;
		}
		case 1:
			adventurerStateFrame.setting(gm.getPlayInfo().getAdventurerInfo());
			TextPrinter.printFrame(adventurerStateFrame);
			
			String[] strs = new String[5];
			int[] prices = new int[3];
			prices[0] = differentHealth(gm.getPlayInfo().getPlayer());
			for(int i = 0; i < 2; i++) {
				prices[1 + i] = differentHealth(gm.getPlayInfo().getTeamMember(i));
			}
			
			strs[0] = "메뉴"; strs[1] = "뒤로 가기";
			if(prices[0] == 0)
				strs[2] = " ";
			else
				strs[2] = gm.getPlayInfo().getPlayer().getStateInfo().getName()+ "치료하기 (" + prices[0] + "G)";
			for(int i = 0; i < 2; i++) {
				if(prices[1 + i] <= 0)
					strs[3 + i] = " ";
				else
					strs[3 + i] = gm.getPlayInfo().getTeamMember(i).getStateInfo().getName()+ "치료하기 (" + prices[i] + "G)";
			}
			int len = TextPrinter.print(strs);
			
			int in = gm.getInput().getInt(0, len - 1);
			
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
				break;
			case 1:
				currentPos = 0;
				break;
			default:
				if(prices[in - 2] > 0) {
					if(prices[in - 2] <= gm.getPlayInfo().getMoney()) {
						gm.getPlayInfo().useMoney(prices[in - 2]);
						if(in == 0)
							gm.getPlayInfo().getPlayer().heal();
						else
							gm.getPlayInfo().getTeamMember(in - 3).heal();
					}
				}
			}
			
			
			break;
		
		}		
	}
	
	private String getHealthInfo(Entity entity) {
		if(entity == null) return " ";
		int m = differentHealth(entity);
		if(m == 0)
			return " ";		
		return entity.getStateInfo().getName()+ "치료하기 (" + m + "G)";
	}
	
	private int differentHealth(Entity entity) {
		if(entity == null) return -1;
		return entity.getMaxHealth() - entity.getHealth();
	}
	

}
