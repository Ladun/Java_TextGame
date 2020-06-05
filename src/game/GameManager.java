package game;

import java.util.ArrayList;
import java.util.Random;

import game.object.Adventurer;
import game.object.GameObject;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.scene.SceneManager;


public class GameManager{
	
	private Input input ;
	private StringBuilder sb;
	
	private PlayInfo playInfo;
	private SceneManager sceneManager;	
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager() {

		input = new Input(this);	
		sb = new StringBuilder();
		sceneManager = new SceneManager();
	}

	
	public boolean init() {
		Resources.print(RenderEnum.TAG_START_MAIN);
		Resources.print("1. 게임 시작", "2. 게임 종료");
		
		int in = input.getInt(1, 2);		
		
		if(in == 1) {
			Resources.print(RenderEnum.TAG_START_SELECT);

			String[] saveFiles = new String[10];
			// 세이브 파일 확인 하기
			int curSaveFileCount = 0;

			//
			if(curSaveFileCount < 10) {
				saveFiles[curSaveFileCount] = (curSaveFileCount + 1) + ". New";
			}
 
			Resources.print(saveFiles);
			in = input.getInt(1, curSaveFileCount + 1);
			if(in== curSaveFileCount + 1) {
				MakePlayInfo();
			}
			else {
				// TODO: Load PlayInfo
			}
			
			if(playInfo == null)
				return false;
			else {
				
				
				return true;				
			}
		}
		
		return false;
	}

	public void update() {
		
		
		while(true) {
			
			sceneManager.getScene().show(this);
			
			
		}		
	}
	
	public void clear() {
		sb.setLength(0);
		for(int i = 0; i < 60;i++) {
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	private void MakePlayInfo() {
		
		int in;

		System.out.print("이름을 정해주세요.(최대 10자)");
		String str = input.getString(10);
		
		int stre, vita, inte, agil, dexi;
		Random rand = new Random();
		do {
			stre = rand.nextInt(100);
			vita = rand.nextInt(100);
			inte = rand.nextInt(100);
			agil = rand.nextInt(100);
			dexi = rand.nextInt(100);
			Resources.print(50, 
					"Name: " + str,
					"Strength: " + stre,
					"Vitality: " + vita, 
					"Intellect: " + inte, 
					"Agility: " + agil, 
					"Dexterity: " + dexi,
					"\n다시 설정하시겠습니까?\n1. 예\n2. 아니요");	
								
			in = input.getInt(1, 2);
		}while(in == 1);
		
		playInfo = new PlayInfo(new Adventurer(str, stre, vita, inte, agil, dexi));
		
	}
	
	private void LoadPlayInfo() {
		
	}
	
	//------------------------------------------------------------
	public Input getInput() {
		return input;
	}


	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public PlayInfo getPlayInfo() {
		return playInfo;
	}
	
	//------------------------------------------------------------
	public static void main(String[] args) {	
		GameManager gm = new GameManager();
		if(gm.init())
			gm.update();
		
		
		System.out.println("게임 종료....");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/*
 - 클래스간 상속 관계가 존재한다. (5장) 
 - 메소드 overriding이 존재한다. (5장) 
 - overriding한 메소드 호출이 존재한다. 
 - 인터페이스와 그에 대한 구현 클래스가 존재한다. (5장) 
 - 2개 이상의 패키지로 구성되어 있다. (6장) 
 - 컬렉션 클래스의 사용이 존재한다. (7장) 
 - 파일 입출력의 사용이 존재한다. (8장)

*/