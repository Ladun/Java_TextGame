package game;

import java.util.ArrayList;
import java.util.Random;

import game.object.Adventurer;
import game.object.GameObject;
import game.resource.RenderEnum;
import game.scene.SceneManager;
import game.util.TextPrinter;


public class GameManager{
	
	private Input input ;
	private StringBuilder sb;
	
	private PlayInfo playInfo;
	private SceneManager sceneManager;	
	private ObjectManager objectManager;	
	private ItemDatabase itemDatabase;
	
	private int days = 0;
	private int times = 0; // 0 ~ 23;
	
	private boolean running = true;
	
	public GameManager() {

		input = new Input(this);	
		sb = new StringBuilder();
		sceneManager = new SceneManager();
		objectManager = new ObjectManager();
		itemDatabase = new ItemDatabase();
	}

	
	public boolean init() {
		TextPrinter.printWithTag(RenderEnum.TAG_START_MAIN);
		TextPrinter.print("게임 시작", "게임 종료");
		
		int in = input.getInt(0, 1);		
		
		if(in == 0) {
			TextPrinter.printWithTag(RenderEnum.TAG_START_SELECT);

			String[] saveFiles = new String[10];
			// 세이브 파일 확인 하기
			int curSaveFileCount = 0;

			//
			if(curSaveFileCount < 10) {
				saveFiles[curSaveFileCount] = "New";
			}
 
			TextPrinter.print(saveFiles);
			in = input.getInt(0, curSaveFileCount);
			if(in== curSaveFileCount) {
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
		
		while(running) {
			
			sceneManager.getScene().show(this);
			
			while(times >= 24)
			{
				sceneManager.update();
				days++;
				times -= 24;
			}
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
		
		Adventurer player = new Adventurer(str);
		do {
			player.setRandomly();
			TextPrinter.printWithDelay(50, 
					"Name: " + str,
					player.getStateInfo().getStat(),
					"\n다시 설정하시겠습니까?\n1. 예\n2. 아니요");	
								
			in = input.getInt(1, 2);
		}while(in == 1);
		
		playInfo = new PlayInfo(player);
		
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
	
	public ObjectManager getObjectManager() {
		return objectManager;
	}
	
	public ItemDatabase getItemDatabse() {
		return itemDatabase;
	}

	public PlayInfo getPlayInfo() {
		return playInfo;
	}
	
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public int getDays() {
		return days;
	}
	
	public int getTimes() {
		return times;
	}

	public void addTimes(int time) {
		times += time;
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