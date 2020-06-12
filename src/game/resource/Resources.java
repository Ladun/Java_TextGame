package game.resource;



/*
 * ����������
 * ��  ��  ��
 * ����������
 * ��  ��  ��
 * ����������
 */

public class Resources {

	public static String getAscii(RenderEnum tag) {
		switch(tag) {
		case TAG_HEALTH:
			return 
					" _   _  _____   ___   _      _____  _   _ \n" + 
					"| | | ||  ___| / _ \\ | |    |_   _|| | | |\n" + 
					"| |_| || |__  / /_\\ \\| |      | |  | |_| |\n" + 
					"|  _  ||  __| |  _  || |      | |  |  _  |\n" + 
					"| | | || |___ | | | || |____  | |  | | | |\n" + 
					"\\_| |_/\\____/ \\_| |_/\\_____/  \\_/  \\_| |_/";
		case TAG_TREASURE:
			return 
					" _____ ______  _____   ___   _____  _   _ ______  _____ \n" + 
					"|_   _|| ___ \\|  ___| / _ \\ /  ___|| | | || ___ \\|  ___|\n" + 
					"  | |  | |_/ /| |__  / /_\\ \\\\ `--. | | | || |_/ /| |__  \n" + 
					"  | |  |    / |  __| |  _  | `--. \\| | | ||    / |  __| \n" + 
					"  | |  | |\\ \\ | |___ | | | |/\\__/ /| |_| || |\\ \\ | |___ \n" + 
					"  \\_/  \\_| \\_|\\____/ \\_| |_/\\____/  \\___/ \\_| \\_|\\____/ ";
		case TAG_START_MAIN: 
			return
					"______  _   _  _   _  _____  _____  _____  _   _ \n" + 
					"|  _  \\| | | || \\ | ||  __ \\|  ___||  _  || \\ | |\n" + 
					"| | | || | | ||  \\| || |  \\/| |__  | | | ||  \\| |\n" + 
					"| | | || | | || . ` || | __ |  __| | | | || . ` |\n" + 
					"| |/ / | |_| || |\\  || |_\\ \\| |___ \\ \\_/ /| |\\  |\n" + 
					"|___/   \\___/ \\_| \\_/ \\____/\\____/  \\___/ \\_| \\_/\n";
		case TAG_START_SELECT:
			return
					" _____   ___   _   _  _____      ______  _____  _      _____  _____ \n" + 
					"/  ___| / _ \\ | | | ||  ___|     |  ___||_   _|| |    |  ___|/  ___|\n" + 
					"\\ `--. / /_\\ \\| | | || |__       | |_     | |  | |    | |__  \\ `--. \n" + 
					" `--. \\|  _  || | | ||  __|      |  _|    | |  | |    |  __|  `--. \\\n" + 
					"/\\__/ /| | | |\\ \\_/ /| |___      | |     _| |_ | |____| |___ /\\__/ /\n" + 
					"\\____/ \\_| |_/ \\___/ \\____/      \\_|     \\___/ \\_____/\\____/ \\____/ ";
		case TAG_SHOP:
			return 
					" _____  _   _  _____ ______ \n" + 
					"/  ___|| | | ||  _  || ___ \\\n" + 
					"\\ `--. | |_| || | | || |_/ /\n" + 
					" `--. \\|  _  || | | ||  __/ \n" + 
					"/\\__/ /| | | |\\ \\_/ /| |    \n" + 
					"\\____/ \\_| |_/ \\___/ \\_|    ";
		case TAG_TAVERN:
			return
					" _____   ___   _   _  _____ ______  _   _ \n" + 
					"|_   _| / _ \\ | | | ||  ___|| ___ \\| \\ | |\n" + 
					"  | |  / /_\\ \\| | | || |__  | |_/ /|  \\| |\n" + 
					"  | |  |  _  || | | ||  __| |    / | . ` |\n" + 
					"  | |  | | | |\\ \\_/ /| |___ | |\\ \\ | |\\  |\n" + 
					"  \\_/  \\_| |_/ \\___/ \\____/ \\_| \\_|\\_| \\_/";
		case TAG_MAIN:
			return
					"_________________________________________________________________________\n" + 
					"|                                                                       |\n" + 
					"|            Shop_                                                      |\n" + 
					"|           |  2  |                                                     |\n" + 
					"|           |_____|                                                     |\n" + 
					"|              |          Tavern                                        |\n" + 
					"|              |          |  3  |                                       |\n" + 
					"|              |          |_____|                                       |\n" + 
					"|              |_____________|_______                                   |\n" + 
					"|              |                     |                                  |\n" + 
					"|              |                     |                                  |\n" + 
					"|            Health                  |                                  |\n" + 
					"|            |  4  |             Adventure                              |\n" + 
					"|            |_____|             |       |                              |\n" + 
					"|                                |   1   |                              |\n" + 
					"|________________________________|_______|______________________________|";
				   //_____________________________________
			       //____________________________________
		case TAG_MAP_FOREST:
			return
					"  -   -   -  -      _\n" + 
					" =   -    -   -  -_   |_-=-\n" + 
					"-|-=__|,  | O__--|   |  _-_\n" + 
					" O    |  .|-- =_ |   |-=\n" + 
					"____  | . | |  -=|   | | __\n" + 
					".',\"} | __|_|_   |   | |{ (\n" + 
					"_____}|{______}__|___|_{)___";
			
		case MAGICIAN:
			return
					"        /\\        \n" + 
					"       /  \\       \n" + 
					"   .../    \\...   \n" + 
					".''  /      \\  ``.\n" + 
					"'...          ...'\n" + 
					"    ''''''''''";
		case ARCHER:
			return 
					"       ___---\n" + 
					"    .---    /\n" + 
					"  .       /\n" + 
					"  |     /\n" + 
					" |    /\n" + 
					"|   /\n" + 
					"| /";
		case TREASURE_HUNTER:
			return
					"       __\n" + 
					" _->--/  \\--<-_\n" + 
					"[_____ __ _____]\n" + 
					"|])   |..|   ([|\n" + 
					"|])    --    ([|\n" + 
					"|______________|";
		case WARRIOR:
			return 
					" _---------_\n" + 
					"|  _______  |\n" + 
					"| |__   __| |\n" + 
					"|   |   |   |\n" + 
					"|    | |    |\n" + 
					" |___| |___|";
		case GOBLIN:
			return 
					"      ___\n" + 
					".  _--   --_  .\n" + 
					"\\'| ..   .. |'/\n" + 
					" \\| '.' '.' |/\n" + 
					"  |  _____  |\n" + 
					"  \\_ \\:::/ _/\n" + 
					"    \\_:::_/\n" + 
					"      '''";
			
			
		default:
			return "";
			
		}
	}

}
