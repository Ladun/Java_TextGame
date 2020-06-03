package game.resource;



/*
 * ¦£¦¡¦¨¦¡¦¤
 * ¦¢  ¦¢  ¦¢
 * ¦§¦¡¦«¦¡¦©
 * ¦¢  ¦¢  ¦¢
 * ¦¦¦¡¦ª¦¡¦¥
 */

public class Resources {
	
	
	public static void print(RenderEnum tag) {
		
		System.out.println(getAscii(tag));
	}
	
	public static void print(String... strings) {
		
		System.out.println(getMenus(strings));
	}

	public static void print(int delay, String... strings) {

		System.out.println("=========================================================================");
		for(int i = 0;i < strings.length;i++) {
			System.out.println(strings[i]);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		System.out.println("=========================================================================");
	}
	
	private static String getAscii(RenderEnum tag) {
		switch(tag) {
		case TAG_START_MAIN: 
			return
					"______  _   _  _   _  _____  _____  _____  _   _ \r\n" + 
					"|  _  \\| | | || \\ | ||  __ \\|  ___||  _  || \\ | |\r\n" + 
					"| | | || | | ||  \\| || |  \\/| |__  | | | ||  \\| |\r\n" + 
					"| | | || | | || . ` || | __ |  __| | | | || . ` |\r\n" + 
					"| |/ / | |_| || |\\  || |_\\ \\| |___ \\ \\_/ /| |\\  |\r\n" + 
					"|___/   \\___/ \\_| \\_/ \\____/\\____/  \\___/ \\_| \\_/\n";
		case TAG_START_SELECT:
			return
					" _____   ___   _   _  _____      ______  _____  _      _____  _____ \r\n" + 
					"/  ___| / _ \\ | | | ||  ___|     |  ___||_   _|| |    |  ___|/  ___|\r\n" + 
					"\\ `--. / /_\\ \\| | | || |__       | |_     | |  | |    | |__  \\ `--. \r\n" + 
					" `--. \\|  _  || | | ||  __|      |  _|    | |  | |    |  __|  `--. \\\r\n" + 
					"/\\__/ /| | | |\\ \\_/ /| |___      | |     _| |_ | |____| |___ /\\__/ /\r\n" + 
					"\\____/ \\_| |_/ \\___/ \\____/      \\_|     \\___/ \\_____/\\____/ \\____/ ";
		case TAG_SHOP:
			return 
					" _____  _   _  _____ ______ \r\n" + 
					"/  ___|| | | ||  _  || ___ \\\r\n" + 
					"\\ `--. | |_| || | | || |_/ /\r\n" + 
					" `--. \\|  _  || | | ||  __/ \r\n" + 
					"/\\__/ /| | | |\\ \\_/ /| |    \r\n" + 
					"\\____/ \\_| |_/ \\___/ \\_|    ";
		case TAG_MAIN:
			return
					"_________________________________________________________________________\r\n" + 
					"|                                                                       |\r\n" + 
					"|                                                                       |\r\n" + 
					"|                                                                       |\r\n" + 
					"|                                                                       |\r\n" + 
					"|                                                                       |\r\n" + 
					"|                                                                       |\r\n" + 
					"|            Shop_                                                      |\r\n" + 
					"|           |  2  |                                                     |\r\n" + 
					"|           |_____|                                                     |\r\n" + 
					"|              |          Tavern                                        |\r\n" + 
					"|              |          |  3  |                                       |\r\n" + 
					"|              |          |_____|                                       |\r\n" + 
					"|              |_____________|_______                                   |\r\n" + 
					"|                                    |                                  |\r\n" + 
					"|                                    |                                  |\r\n" + 
					"|                                    |                                  |\r\n" + 
					"|                                    |                                  |\r\n" + 
					"|                                Adventure                              |\r\n" + 
					"|                                |       |                              |\r\n" + 
					"|                                |   1   |                              |\r\n" + 
					"|________________________________|_______|______________________________|";
				   //_____________________________________
			       //____________________________________
			
		default:
			return "";
			
		}
	}
	
	private static String getMenus(String... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append("=========================================================================\n"); // = 73°³
		for(String str : strings) {
			if(str != null) {			
				sb.append(str);
				sb.append('\n');
			}
		}
		sb.append("=========================================================================");
		return sb.toString();
	}

}
