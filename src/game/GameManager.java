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
	
	private int days = 0;
	private int times = 0; // 0 ~ 23;
	
	private boolean running = false;
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager() {

		input = new Input(this);	
		sb = new StringBuilder();
		sceneManager = new SceneManager();
	}

	
	public boolean init() {
		TextPrinter.printWithTag(RenderEnum.TAG_START_MAIN);
		TextPrinter.print("1. ���� ����", "2. ���� ����");
		
		int in = input.getInt(1, 2);		
		
		if(in == 1) {
			TextPrinter.printWithTag(RenderEnum.TAG_START_SELECT);

			String[] saveFiles = new String[10];
			// ���̺� ���� Ȯ�� �ϱ�
			int curSaveFileCount = 0;

			//
			if(curSaveFileCount < 10) {
				saveFiles[curSaveFileCount] = (curSaveFileCount + 1) + ". New";
			}
 
			TextPrinter.print(saveFiles);
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
		
		running = true;
		while(running) {
			
			sceneManager.getScene().show(this);
			
			if(times >= 24)
			{
				sceneManager.update();
				days++;
				times = 0;
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
					player.getStat(),
					"\n�ٽ� �����Ͻðڽ��ϱ�?\n1. ��\n2. �ƴϿ�");	
								
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

	public PlayInfo getPlayInfo() {
		return playInfo;
	}
	
	
	public void setRunning(boolean running) {
		this.running = running;
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