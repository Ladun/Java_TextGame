package game.resource;



/*
 * ┌─┬─┐
 * │  │  │
 * ├─┼─┤
 * │  │  │
 * └─┴─┘
 */

public class Resources {


	public static void print(RenderEnum tag) {
		
		System.out.println(getAscii(tag));
	}
	
	public static void print(int col, int row, RenderEnum... tags) {
		
		System.out.println(makeFrame(col, row, tags));
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
	
	private static String makeFrame(int col, int row, RenderEnum... tags ) {

		if(tags.length != col * row)
			return "";
		
		StringBuilder sb = new StringBuilder();		
		int width = 23;
		int height = 10;
		
		int r = 0, c = 0;
				
		// 격자를 맞추기 위해 가로, 세로에 1씩 더함
		// 가로의 경우 \n도 필요해서 1이 더 추가
		char[] ret = new char[(row * height + 1) * (col * width + 2)];
		
		// 초기화
		for(int y = 0; y < row * height + 1; y++) {
			int x;
			for(x = 0; x < col * width + 1;x++) {
				ret[y * (col *width + 2) + x] = ' ';				
			}
			ret[y * (col * width + 2)+ x] = '\n';
		}		
		
		
		// Frame 안에 들어갈 이미지 
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < col; x++) {
				if(tags[y * col + x] == RenderEnum.NONE)
					continue;
				String s = getAscii(tags[y * col + x]);
				ret[(y * height + 1) * (col * width + 2) + x * width + 1] = (char)((int)'1' + (y * col + x));
				for(int h = 0; h < 7; h++) {
					for(int w = 0; w < 21;w++) {
						char _ch = s.charAt(h * 21 + w);
						if(_ch == '\n')
							break;
						
						ret[(y * height + h + 2) * (col * width + 2) + x * width + w + 2] = _ch;
					}
				}
			}
		}
		
		// Frame 틀 생성
		for(int y = 0; y < row * height + 1; y++) { 
			for(int x = 0; x < col * width + 1;x++) {
				int idx =  y * (col * width + 2) + x;
				if(y == 0)
					ret[idx] = '_';
				else if(x % width == 0) 
					ret[idx] = '|';
				else if(y % height == 0)
					ret[idx] = '_';			
			}
		}
		
		return new String(ret);
	}
	
	private static String getAscii(RenderEnum tag) {
		switch(tag) {
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
					"|                                                                       |\n" + 
					"|                                                                       |\n" + 
					"|                                                                       |\n" + 
					"|                                                                       |\n" + 
					"|                                                                       |\n" + 
					"|            Shop_                                                      |\n" + 
					"|           |  2  |                                                     |\n" + 
					"|           |_____|                                                     |\n" + 
					"|              |          Tavern                                        |\n" + 
					"|              |          |  3  |                                       |\n" + 
					"|              |          |_____|                                       |\n" + 
					"|              |_____________|_______                                   |\n" + 
					"|                                    |                                  |\n" + 
					"|                                    |                                  |\n" + 
					"|                                    |                                  |\n" + 
					"|                                    |                                  |\n" + 
					"|                                Adventure                              |\n" + 
					"|                                |       |                              |\n" + 
					"|                                |   1   |                              |\n" + 
					"|________________________________|_______|______________________________|";
				   //_____________________________________
			       //____________________________________
			
		case MAGICIAN:
			return
					"         /\\         \n" + 
					"        /  \\        \n" + 
					"       /    \\       \n" + 
					"   .../      \\...   \n" + 
					".''  /        \\  ``.\n" + 
					"'...            ...'\n" + 
					"    ''''''''''''     ";
			
		default:
			return "";
			
		}
	}
	
	private static String getMenus(String... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append("=========================================================================\n"); // = 73개
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
