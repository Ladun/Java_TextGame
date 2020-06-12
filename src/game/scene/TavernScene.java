package game.scene;

import game.GameManager;
import game.object.Adventurer;
import game.object.StateInfo;
import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class TavernScene extends AbstractScene{
	
	private static final int MAX_ADVENTURER = 8;
	
	private Adventurer[] adventurers;
	private int[] prices;
	private int[] leftDays; // 선술집에 남아있을 기간
	
	private int showTargetIdx;
	
	private GridFrame adventurerListFrame;
	private GridFrame adventurerShowFrame;
	
	public TavernScene() {
		adventurerListFrame = new GridFrame(2, MAX_ADVENTURER / 2, GridFrame.IMAGE_WIDTH, true);
		adventurerShowFrame = new GridFrame(1, 1, 23, false);
		
		adventurers = new Adventurer[MAX_ADVENTURER];
		prices = new int[MAX_ADVENTURER];
		leftDays = new int[MAX_ADVENTURER];
		
		for(int i = 0; i < 4;i++) {
			adventurers[i] = new Adventurer(StateInfo.makeName());
			prices[i] =    (adventurers[i].getStateInfo().getStrength() + 
							adventurers[i].getStateInfo().getVitality() +
							adventurers[i].getStateInfo().getIntellect() +
							adventurers[i].getStateInfo().getAgility() +
							adventurers[i].getStateInfo().getDexterity()) / 2;
			leftDays[i] = (int)(Math.random() * 10) + 1;
		}
	}


	public void update() {	
		
		//머물 기간이 넘긴 모험가 삭제
		int left = 0;
		for(int i = 0; i < MAX_ADVENTURER;i++) {
			if(leftDays[i] > 0) {
				leftDays[i]--;
				
				if(leftDays[i] <= 0) {
					adventurers[i] = null;					
					left++;
				}
			
			}else {
				left++;
			}
		}	
		
		// 모험가 랜덤 생성
		int addAdventurer = (int)(Math.random() * left) + 1;
		for(int i = 0; i < MAX_ADVENTURER;i++) {
			if(adventurers[i] == null) {
				adventurers[i] = new Adventurer(StateInfo.makeName());
				prices[i] =    (adventurers[i].getStateInfo().getStrength() + 
								adventurers[i].getStateInfo().getVitality() +
								adventurers[i].getStateInfo().getIntellect() +
								adventurers[i].getStateInfo().getAgility() +
								adventurers[i].getStateInfo().getDexterity()) / 2;
				leftDays[i] = (int)(Math.random() * 10) + 1;
				addAdventurer--;	
				if(addAdventurer <= 0)
					break;
			}
		}		
	}
	
	@Override
	public void clear() {
		
	}
	
	@Override
	public void show(GameManager gm) {
		
		
		switch(currentPos) {
		case 0:{
			TextPrinter.printWithTag(RenderEnum.TAG_TAVERN);
			TextPrinter.print("메뉴", "나가기", "술 사기", "주변 용병 둘러보기");
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
					currentPos = 3;
					break;
			}
			
			break;
		}
		case 3:{
			RenderEnum[] strs = new RenderEnum[MAX_ADVENTURER];
			for(int i = 0; i < MAX_ADVENTURER; i ++) {
				if(adventurers[i] != null) {
					strs[i] = adventurers[i].getType();
				}
				else
					strs[i] = RenderEnum.NONE;
			}			
			
			adventurerListFrame.setting(strs);
			for(int r = 0, i = 0; r < adventurerListFrame.getRow(); r++)
				for(int c = 0; c < adventurerListFrame.getCol();c++,  i++)
					if(adventurers[i] != null)
						adventurerListFrame.setGridTag(r, c, adventurers[i].getStateInfo().getName());
			TextPrinter.printFrame(adventurerListFrame);
			
			String[] menus = new String[MAX_ADVENTURER + 2];
			menus[0] = "메뉴";
			menus[1] = "뒤로 가기";
			for(int i = 0; i < MAX_ADVENTURER; i ++) {
				if(adventurers[i] != null) 
					menus[i + 2] = adventurers[i].getStateInfo().getName() + "(" + prices[i] + "G)";
				else
					menus[i + 2] = "";
			}
			TextPrinter.print(menus);
			
			int in = gm.getInput().getInt(0, 1 + MAX_ADVENTURER);
			switch(in){
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
				break;
			case 1:
				currentPos = 0;
				break;
			default:
				if(adventurers[in - 2] == null) {
					TextPrinter.printWithDelay(1200, "잘못 선택하셨습니다");
					
				}
				else {
					currentPos = 4;
					showTargetIdx = in - 2;
				}
				
			}
			break;
		}
		case 4:{
			if (adventurers[showTargetIdx] == null)
				currentPos = 3;
			
			adventurerShowFrame.setting(adventurers[showTargetIdx].getInfo());
			TextPrinter.printFrame(adventurerShowFrame);
			
			TextPrinter.print("메뉴", "뒤로 가기", "고용(" + prices[showTargetIdx] + "G)");
			int in = gm.getInput().getInt(0, 2);

			switch(in){
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
				break;
			case 1:
				currentPos = 3;
				break;
			case 2:
				int idx = gm.getPlayInfo().getEmptyAdventurerSpace();
				
				if(idx == -1)
				{
					TextPrinter.printWithDelay(500, "이미 용병이 두 명 있습니다.");
					break;
				}
				if(prices[showTargetIdx] <= gm.getPlayInfo().getMoney()) {
					gm.getPlayInfo().useMoney(prices[showTargetIdx]);
					gm.getPlayInfo().setTeamMember(idx, adventurers[showTargetIdx]);
					adventurers[showTargetIdx] = null;
				}
				currentPos = 3;
				
				break;			
			}
			break;
		}
		}
		
	}

}
