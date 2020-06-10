package game.scene;

import game.GameManager;
import game.object.Adventurer;
import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class TavernScene extends AbstractScene{
	
	private static final int MAX_ADVENTURER = 8;
	
	private Adventurer[] adventurers;
	private int[] leftDays; // 선술집에 남아있을 기간
	
	private Adventurer showTarget;
	
	private GridFrame adventurerListFrame;
	private GridFrame adventurerShowFrame;
	
	public TavernScene() {
		adventurerListFrame = new GridFrame(2, MAX_ADVENTURER / 2, GridFrame.IMAGE_WIDTH, true);
		adventurerShowFrame = new GridFrame(1, 1, 16, false);
		
		adventurers = new Adventurer[MAX_ADVENTURER];
		leftDays = new int[MAX_ADVENTURER];
		
		for(int i = 0; i < 4;i++) {
			adventurers[i] = new Adventurer(Adventurer.makeName());
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
				adventurers[i] = new Adventurer(Adventurer.makeName());
				leftDays[i] = (int)(Math.random() * 10) + 1;
				addAdventurer--;	
				if(addAdventurer <= 0)
					break;
			}
		}		
	}

	@Override
	public void show(GameManager gm) {
		
		
		switch(currentPos) {
		case 0:{
			TextPrinter.print("메뉴", "나가기", " ", "주변 용병 둘러보기");
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
						adventurerListFrame.setGridTag(r, c, adventurers[i].getName());
			TextPrinter.printFrame(adventurerListFrame);
			
			String[] menus = new String[MAX_ADVENTURER + 2];
			menus[0] = "메뉴";
			menus[1] = "뒤로 가기";
			for(int i = 0; i < MAX_ADVENTURER; i ++) {
				if(adventurers[i] != null) 
					menus[i + 2] = adventurers[i].getName() + "(" + 100 + "G)";
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
					showTarget = adventurers[in - 2];
				}
				
			}
			break;
		}
		case 4:{
			
			//TODO: makeFrame 함수를 수정해서 원하는 크기의 사각형을 만들 수 있게
			adventurerShowFrame.setting(showTarget.getInfo());
			TextPrinter.printFrame(adventurerShowFrame);
			
			TextPrinter.print("메뉴", "뒤로 가기", "고용");
			int in = gm.getInput().getInt(0, 3);

			switch(in){
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
				break;
			case 1:
				currentPos = 3;
				break;
			case 2:
				
				// TODO: 용병 고용 구현하기
				
				break;			
			}
			break;
		}
		}
		
	}

}
