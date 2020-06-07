package game.scene;

import java.util.ArrayList;

import game.GameManager;
import game.object.Adventurer;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.util.TextMaker;

public class TavernScene extends AbstractScene{
	
	private static final int MAX_ADVENTURER = 8;
	
	private Adventurer[] adventurers;
	private int[] leftDays; // �������� �������� �Ⱓ
	
	private Adventurer showTarget;
	
	public TavernScene() {
		adventurers = new Adventurer[MAX_ADVENTURER];
		leftDays = new int[MAX_ADVENTURER];
		int addAdventurer = (int)(Math.random() * MAX_ADVENTURER) + 1;	
		
		for(int i = 0; i < addAdventurer;i++) {
			adventurers[i] = new Adventurer(Adventurer.makeName());
			leftDays[i] = (int)(Math.random() * 10) + 1;
		}
	}
	
	public void update() {	
		
		//�ӹ� �Ⱓ�� �ѱ� ���谡 ����
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
		
		// ���谡 ���� ����
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
			TextMaker.print("0. �޴�", "1. ������", "2. ", "3. �ֺ� �뺴 �ѷ�����");
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
				if(adventurers[i] != null) 
					strs[i] = adventurers[i].getType();
				else
					strs[i] = RenderEnum.NONE;
			}			
			TextMaker.printFrame(MAX_ADVENTURER / 2, 2, TextMaker.FRAME_IMAGE_WIDTH, TextMaker.FRAME_IMAGE_HEIGHT, true, strs);
			
			String[] menus = new String[MAX_ADVENTURER + 2];
			menus[0] = "0. �޴�";
			menus[1] = "1. �ڷ� ����";
			for(int i = 0; i < MAX_ADVENTURER; i ++) {
				if(adventurers[i] != null) 
					menus[i + 2] = (i + 2) + ". " + adventurers[i].getName() + "(" + 100 + "G)";
				else
					menus[i + 2] = (i + 2) + ". ";
			}
			TextMaker.print(menus);
			
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
					TextMaker.printWithDelay(1200, "�߸� �����ϼ̽��ϴ�");
					
				}
				else {
					currentPos = 4;
					showTarget = adventurers[in - 2];
				}
				
			}
			break;
		}
		case 4:{
			
			//TODO: makeFrame �Լ��� �����ؼ� ���ϴ� ũ���� �簢���� ���� �� �ְ�
			TextMaker.printFrame(1, 1, 16, 8, false,
					showTarget.getInfo());
			
			TextMaker.print("0. �޴�", "1. �ڷ� ����", "2. ���");
			int in = gm.getInput().getInt(0, 3);

			switch(in){
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
				break;
			case 1:
				currentPos = 3;
				break;
			case 2:
				
				// TODO: �뺴 ��� �����ϱ�
				
				break;			
			}
			break;
		}
		}
		
	}

}
