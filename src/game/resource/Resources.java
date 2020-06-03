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
			
		default:
			return "";
			
		}
	}
	
	private static String getMenus(String... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append("=========================================================================\n");
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
