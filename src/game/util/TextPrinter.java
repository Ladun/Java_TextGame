package game.util;

import game.resource.RenderEnum;
import game.resource.Resources;

public class TextPrinter {
	
	public static final int FRAME_IMAGE_WIDTH = 23;
	public static final int FRAME_IMAGE_HEIGHT = 9;

	public static void printWithTag(RenderEnum tag) {
		
		System.out.println(Resources.getAscii(tag));
	}
	
	public static void printFrame(int col, int row, int width, boolean numeric, RenderEnum... args) {
		String[] strs = new String[args.length];
		for(int i = 0; i < args.length; i++) 
			strs[i] = Resources.getAscii(args[i]);
		
		System.out.println(Resources.makeFrame(col, row, width, numeric, strs));
	}	
	
	public static void printFrame(int col, int row, int width, boolean numeric, String... args) {
		
		System.out.println(Resources.makeFrame(col, row, width, numeric, args));
	}
	
	public static void print(String... args) {
		
		System.out.println(Resources.getMenus(args));
	}

	public static void printWithDelay(int delay, String... args) {

		System.out.println("=========================================================================");
		for(int i = 0;i < args.length;i++) {
			System.out.println(args[i]);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		System.out.println("=========================================================================");
	}
}
