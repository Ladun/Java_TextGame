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
		System.out.print(">>");
		int in = sc.nextInt();
		while(in < min || in > max) {
			System.out.print("Wrong Input. Re >>");
			in = sc.nextInt();
		}
		gm.clear();
		return in;
	}
}
