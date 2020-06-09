package game.resource;



/*
 * ����������
 * ��  ��  ��
 * ����������
 * ��  ��  ��
 * ����������
 */

public class Resources {

	
	public static String makeFrame(int col, int row, int width, boolean numeric, String... tags ) {

		if(tags.length != col * row)
			return "";
		
		StringBuilder sb = new StringBuilder();		
		
		int size = (col * width + 2);
		//�������� ���̸� ������ Ʋ�� ����� ����
		int[] heights = new int[row];
		int totalHeight = 0;
		
		for(int i = 0, heightIdx = 0; i < tags.length;i++) {
			int tmpHeight = 1;
			if(tags[i].length() > 0) {
				tmpHeight++;
				if(numeric)
					tmpHeight++;
			}
			
			//�� ���ڿ��� ���� ���ϱ�
			for(int j = 0; j <tags[i].length(); j++) 
				if(tags[i].charAt(j) == '\n')
					tmpHeight++;

			if(heights[heightIdx] < tmpHeight)
				heights[heightIdx] = tmpHeight;
			
			if((i + 1) % col == 0) {
				size += (heights[heightIdx]) * (col * width + 2);
				totalHeight += heights[heightIdx];
				heightIdx++;
			}
		}
				
		// ���ڸ� ���߱� ���� ����, ���ο� 1�� ����
		// ������ ��� \n�� �ʿ��ؼ� 1�� �� �߰�
		char[] ret = new char[size];//[(row * height + 1) * (col * width + 2)];
		
		// �ʱ�ȭ
		for(int y = 0; y < totalHeight + 1; y++) {
			int x;
			for(x = 0; x < col * width + 1;x++) {
				ret[y * (col *width + 2) + x] = ' ';				
			}
			if(y != totalHeight)
				ret[y * (col * width + 2)+ x] = '\n';
		}	
		
		
		
		// Frame �ȿ� �� �̹��� 
		for(int r = 0, curH = 0; r < row; r++) {
			for(int c = 0; c < col; c++) {
				String s = tags[r * col + c];
				if(s.length() == 0)
					continue;
				
				// �Է¹��� ���ڿ��� �ִ� �ʺ�, ����
				int rw = 0, rh = 1, tw = 0;
				for(int i = 0; i < s.length();i++)
				{
					if(s.charAt(i) == '\n') {
						rh++;
						tw = 0;
					}
					else {
						tw++;
						if(rw < tw)
							rw = tw;
					}
						
				}
				
				// ������ �»�ܿ��� �󸶳� ������ ����
				int wOffset = (width - rw) / 2;
				int hOffset = (heights[r] - rh) / 2;
				if(wOffset < 0 || hOffset < 0)
					continue;				

				// ���ڿ� ���� ���̴� ��
				if(numeric) {
					int number = (r * col + c + 2);
					int numLen = 0;
					while(number > 0) {
						numLen++;
						number /= 10;
					}
					number = (r * col + c + 2);
					while(numLen > 0) {
						ret[(curH + 1) * (col * width + 2) + c * width + numLen] = (char)('0' + number % 10);
						number /= 10;
						numLen--;
					}
				}
				int rIdx = 0;
				for(int h = 0; h < rh; h++) {
					for(int w = 0; w < rw + 1;w++) {
						if(rIdx >= s.length())
							break;
						char _ch = s.charAt(rIdx++);
						if(_ch == '\n')
							break;
						
						ret[(curH + h + 1 + hOffset) * (col * width + 2) + c * width + w + 1 + wOffset] = _ch;
					}
				}
			}
			curH += heights[r];
		}
		
		// Frame Ʋ ����
		for(int vh = 0, tmpH = 0; vh < row; vh++) {
			// �� ��
			for(int x = 0; x < col * width + 1;x++)
				ret[x] = '_';
			
			for(int y = 0; y < heights[vh]; y++) { 
				for(int x = 0; x < col * width + 1;x++) {
					int idx =  (tmpH + y + 1) * (col * width + 2) + x;
					if(x % width == 0) 
						ret[idx] = '|';
					else if(y == heights[vh] - 1)
						ret[idx] = '_';			
				}
			}
			tmpH += heights[vh];
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
		case TAG_MAP_FOREST:
			return
					"  -   -   -  -      _\n" + 
					" =   -    -   -  -_   |_-=-\n" + 
					"-0-=__|,  |00__--|   |  _-_\n" + 
					"      |  .|-- =_ |   |-=\n" + 
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
