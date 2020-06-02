package game;

import java.util.ArrayList;
import java.util.Scanner;

import game.object.GameObject;
import resource.RenderEnum;
import resource.Resources;

public class GameManager{
	
	private Scanner sc ;
	private StringBuilder sb;
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager() {

		sc = new Scanner(System.in);	
		sb = new StringBuilder();
	}

	
	public boolean init() {
		Resources.print(RenderEnum.TAG_START_MAIN);
		Resources.print("1. ���� ����", "2. ���� ����");
		
		int in = input(1, 2);		
		
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
			in = input(1, curSaveFileCount + 1);
			if(in== curSaveFileCount + 1) {
				
			}
			else {
				
			}
			
			return true;
		}
		
		return false;
	}


	public void update() {
		
		
		while(true) {
			
			
			
			
		}		
	}
	
	public void clear() {
		sb.setLength(0);
		for(int i = 0; i < 60;i++) {
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	public int input(int min, int max) {
		System.out.print(">>");
		int in = sc.nextInt();
		while(in < min || in > max) {
			System.out.print("Wrong Input. Re >>");
			in = sc.nextInt();
		}
		clear();
		return in;
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