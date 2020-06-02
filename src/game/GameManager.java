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
		Resources.print("1. 게임 시작", "2. 게임 종료");
		
		int in = input(1, 2);		
		
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
			System.out.println("게임 종료....");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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