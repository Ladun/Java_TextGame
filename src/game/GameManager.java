package game;

import game.file.DataManager;
import game.file.SaveData;
import game.object.Adventurer;
import game.resource.RenderEnum;
import game.scene.SceneManager;
import game.scene.SceneManager.SceneType;
import game.scene.TavernScene;
import game.util.TextPrinter;


public class GameManager{
	
	private Input input ;
	private StringBuilder sb;
	
	private PlayInfo playInfo;
	private SceneManager sceneManager;	
	private ObjectManager objectManager;	
	private DataManager dataManager;
	private ItemDatabase itemDatabase;
	
	private int dataIdx;
	
	private int days = 0;
	private int times = 0; // 0 ~ 23;
	
	private boolean running = true;
	
	public GameManager() {

		input = new Input(this);	
		sb = new StringBuilder();
		sceneManager = new SceneManager();
		objectManager = new ObjectManager();
		dataManager = new DataManager();
		itemDatabase = new ItemDatabase();
		
	}

	
	public boolean init() {
		TextPrinter.printWithTag(RenderEnum.TAG_START_MAIN);
		TextPrinter.print("���� ����", "���� ����");
		
		int in = input.getInt(0, 1);		
		
		if(in == 0) {
			dataManager.load();
			
			TextPrinter.printWithTag(RenderEnum.TAG_START_SELECT);

			String[] saveFiles = new String[11];
			
			int curSaveFileCount = dataManager.getDataList().size();
			
			for(int i = 0; i <curSaveFileCount;i++) {
				saveFiles[i] = dataManager.getDataList().get(i).getPlayer().getName();
			}
			
			if(curSaveFileCount < DataManager.MAX_COUNT) {
				saveFiles[curSaveFileCount] = "New";
			}
 
			int len = TextPrinter.print(saveFiles);
			in = input.getInt(0, len - 1);
			dataIdx = in;
			if(in == curSaveFileCount) {
				MakePlayInfo();
				dataManager.makeSaveFile(this, playInfo);
			}
			else {
				LoadPlayInfo(dataManager.getDataList().get(dataIdx));
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

		System.out.print("�̸��� �����ּ���.(�ִ� 10��)");
		String str = input.getString(10);
		
		Adventurer player = new Adventurer(str);
		do {
			player.setRandomly();
			TextPrinter.printWithDelay(50, 
					"Name: " + str,
					player.getStateInfo().getStat(),
					"\n�ٽ� �����Ͻðڽ��ϱ�?\n1. ��\n2. �ƴϿ�");	
								
			in = input.getInt(1, 2);
		}while(in == 1);
		
		playInfo = new PlayInfo(player);
		
	}
	
	private void LoadPlayInfo(SaveData saveData) {
		this.days = saveData.getDays();
		this.times = saveData.getTimes();
		playInfo = new PlayInfo(this, saveData);
		sceneManager.<TavernScene>getScene(SceneType.TAVERN).loadData(saveData);
	}
	
	public void save() {
		dataManager.getDataList().get(dataIdx).setPlayInfo(this, playInfo);
		dataManager.serialize(dataIdx);
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
		
		
		System.out.println("���� ����....");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/*
 - Ŭ������ ��� ���谡 �����Ѵ�. (5��) 
 - �޼ҵ� overriding�� �����Ѵ�. (5��) 
 - overriding�� �޼ҵ� ȣ���� �����Ѵ�. 
 - �������̽��� �׿� ���� ���� Ŭ������ �����Ѵ�. (5��) 
 - 2�� �̻��� ��Ű���� �����Ǿ� �ִ�. (6��) 
 - �÷��� Ŭ������ ����� �����Ѵ�. (7��) 
 - ���� ������� ����� �����Ѵ�. (8��)

*/