package game;

import java.util.ArrayList;

import game.object.GameObject;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.screen.MainScene;
import game.screen.SceneManager;


public class GameManager{
	
	private Input input ;
	private StringBuilder sb;
	
	private Player player;
	private SceneManager sceneManager;	
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager() {

		input = new Input(this);	
		sb = new StringBuilder();
		sceneManager = new SceneManager();
	}

	
	public boolean init() {
		Resources.print(RenderEnum.TAG_START_MAIN);
		Resources.print("1. ���� ����", "2. ���� ����");
		
		int in = input.getInt(1, 2);		
		
		if(in == 1) {
			Resources.print(RenderEnum.TAG_START_SELECT);

			String[] saveFiles = new String[10];
			// ���̺� ���� Ȯ�� �ϱ�
			int curSaveFileCount = 0;

			//
			if(curSaveFileCount < 10) {
				saveFiles[curSaveFileCount] = (curSaveFileCount + 1) + ". New";
			}
 
			Resources.print(saveFiles);
			in = input.getInt(1, curSaveFileCount + 1);
			if(in== curSaveFileCount + 1) {
				// Add New Player
				player = new Player();
			}
			else {
				// Load Player
			}
			
			if(player == null)
				return false;
			else {
				
				// ���� ���� ǥ��
				
				return true;				
			}
		}
		
		return false;
	}


	public void update() {
		
		
		while(true) {
			
			sceneManager.getScene().show(input);
			
			
		}		
	}
	
	public void clear() {
		sb.setLength(0);
		for(int i = 0; i < 60;i++) {
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {	
		GameManager gm = new GameManager();
		if(gm.init())
			gm.update();
		else {
			System.out.println("���� ����....");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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