package game.util;

import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.resource.Resources;

public class TextPrinter {
	

	public static void printWithTag(RenderEnum tag) {
		
		System.out.println(Resources.getAscii(tag));
	}
	
	public static void printFrame(GridFrame frame) {
		
		System.out.println(frame.getString());
	}	
	
	public static void print(String... args) {

		int i = 0;
		System.out.println("========================================================================="); // = 73°³
		for(String str : args) {
			if(str != null) {
				System.out.println(i + ". "+ str);
				i++;
			}
		}
		System.out.println("=========================================================================");
	}

	public static void printWithDelay(int delay, String... args) {

		System.out.println("=========================================================================");
		for(String str : args) {
			if(str != null) {
				System.out.println(str);
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
		System.out.println("=========================================================================");
	}
}
