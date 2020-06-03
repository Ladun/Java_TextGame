package game;

import java.util.Scanner;

public class Input {

	private Scanner sc;
	private GameManager gm;
	
	public Input(GameManager gm) {
		sc = new Scanner(System.in);
		this.gm = gm;
	}
	
	public int getInt(int min, int max) {
		int in = getIntWithoutClear(min, max);
		gm.clear();
		return in;
	}
	public int getIntWithoutClear(int min, int max) {
		System.out.print(">>");
		int in = sc.nextInt();
		while(in < min || in > max) {
			System.out.print("Wrong Input. Re >>");
			in = sc.nextInt();
		}
		return in;
	}
	
	public String getString(int maxLength) {
		System.out.print(">>");
		String st ;
		do {
			st = sc.next();
		}while(st.length() > maxLength);
		
		return st;
	}
}
