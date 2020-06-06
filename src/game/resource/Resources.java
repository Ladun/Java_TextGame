package game.resource;



/*
 * ����������
 * ��  ��  ��
 * ����������
 * ��  ��  ��
 * ����������
 */

public class Resources {

	
	public static String makeFrame(int col, int row, int width, int height, boolean numeric, String... tags ) {

		if(tags.length != col * row)
			return "";
		
		StringBuilder sb = new StringBuilder();		
		
		int r = 0, c = 0;
				
		// ���ڸ� ���߱� ���� ����, ���ο� 1�� ����
		// ������ ��� \n�� �ʿ��ؼ� 1�� �� �߰�
		char[] ret = new char[(row * height + 1) * (col * width + 2)];
		
		// �ʱ�ȭ
		for(int y = 0; y < row * height + 1; y++) {
			int x;
			for(x = 0; x < col * width + 1;x++) {
				ret[y * (col *width + 2) + x] = ' ';				
			}
			ret[y * (col * width + 2)+ x] = '\n';
		}		
		
		
		// Frame �ȿ� �� �̹��� 
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < col; x++) {
				String s = tags[y * col + x];
				if(s.length() == 0)
					continue;
				
				// �Է¹��� ���ڿ��� �ִ� �ʺ�, ����
				int rw = 0, rh = 1, tw = 0;
				for(int i = 0; i < s.length();i++)
				{
					if(s.charAt(i) == '\n') {
						rh++;
						if(rw < tw)
							rw = tw;
						tw = 0;
					}
					else
						tw++;
				}

				// ������ �»�ܿ��� �󸶳� ������ ����
				int wOffset = (width - 1 - rw) / 2;
				int hOffset = (height - 1 - rh) / 2;
				if(wOffset < 0 || hOffset < 0)
					continue;
				
				
				if(numeric)
					ret[(y * height + 1) * (col * width + 2) + x * width + 1] = (char)((int)'1' + (y * col + x));
				int rIdx = 0;
				for(int h = 0; h < rh; h++) {
					for(int w = 0; w < rw + 1;w++) {
						char _ch = s.charAt(rIdx++);
						if(_ch == '\n' || rIdx >= s.length())
							break;
						
						ret[(y * height + h + 1 + hOffset) * (col * width + 2) + x * width + w + 1 + wOffset] = _ch;
					}
				}
			}
		}
		
		// Frame Ʋ ����
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
	
	public static String getAscii(RenderEnum tag) {
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
	
	public static String getMenus(String... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append("=========================================================================\n"); // = 73��
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
