package game.resource;

public class GridFrame {
	public static final int IMAGE_WIDTH = 23;
	public static final int IMAGE_HEIGHT = 9;
	
	protected int col, row;
	protected int width;
	protected int[] heights;
	protected int totalHeight;
	protected boolean numeric;
	protected char[] ret;

	public GridFrame(int row, int col, int width, boolean numeric) {
		this.row = row;
		this.col = col;
		this.width = width;
		this.numeric = numeric;
	}

	public String getString() {
		if (ret != null)
			return new String(ret);
		return null;
	}
	
	public void setting(RenderEnum... args) {
		String[] strs = new String[args.length];
		for(int i = 0; i < args.length; i++) 
			strs[i] = Resources.getAscii(args[i]);
		setting(strs);
	}

	public void setting(String... strs) {
		if (strs.length != col * row)
			return;
		
		setArray(strs);
		clear();
		makeFrame();
		setStrings(strs);
	}

	private boolean setArray(String... strs) {
		boolean flag = heights ==  null;

		int size = (col * width + 2);
		// 가변적인 높이를 가지는 틀을 만들기 위해
		int[] _heights = new int[row];
		int _totalHeight = 0;

		for (int i = 0, heightIdx = 0; i < strs.length; i++) {
			int tmpHeight = 1;
			if (strs[i].length() > 0) {
				tmpHeight++;
				if (numeric)
					tmpHeight++;
			}

			// 각 문자열별 높이 구하기
			for (int j = 0; j < strs[i].length(); j++)
				if (strs[i].charAt(j) == '\n')
					tmpHeight++;

			if (_heights[heightIdx] < tmpHeight)
				_heights[heightIdx] = tmpHeight;

			if ((i + 1) % col == 0) {
				size += (_heights[heightIdx]) * (col * width + 2);
				_totalHeight += _heights[heightIdx];

				if (heights != null)
					if (heights[heightIdx] != _heights[heightIdx])
						flag = true;
				heightIdx++;
			}
		}

		// 격자를 맞추기 위해 가로, 세로에 1씩 더함
		// 가로의 경우 \n도 필요해서 1이 더 추가
		if (flag ) {
			ret = new char[size];// [(row * height + 1) * (col * width + 2)];
			heights = _heights;
			totalHeight = _totalHeight;
		}

		return flag;
	}

	protected void clear() {
		// 초기화
		for (int y = 0; y < totalHeight + 1; y++) {
			int x;
			for (x = 0; x < col * width + 1; x++) {
				ret[y * (col * width + 2) + x] = ' ';
			}
			if (y != totalHeight)
				ret[y * (col * width + 2) + x] = '\n';
		}
	}
	
	protected void setStrings(String... strs) {
		// Frame 안에 들어갈 이미지
		for (int r = 0; r < row; r++)
			for (int c = 0; c < col; c++) {
				if(r * col + c >= strs.length)
					return;
				setString(r, c, strs[r * col + c]);
			}
	}
		
	public void setString(int r, int c, String s) {
		if (s.length() == 0)
			return;
		
		int curH = 0;
		for(int i = 0; i < r;i++)
			curH += heights[i];

		// 입력받은 문자열의 최대 너비, 높이
		int rw = 0, rh = 1, tw = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\n') {
				rh++;
				tw = 0;
			} else {
				tw++;
				if (rw < tw)
					rw = tw;
			}

		}

		// 격자의 좌상단에서 얼마나 떨어질 건지
		int wOffset = (width - rw) / 2, wMax;
		int hOffset = (heights[r] - rh) / 2;
		wMax = rw;
		if (wOffset < 0){
			wOffset = 0;
			wMax = width - 3;
		}
		if (hOffset < 0) 
			return;
		
		
		
		// 격자에 숫자 붙이는 거
		if (numeric) {
			int number = (r * col + c + 2);
			int numLen = 0;
			while (number > 0) {
				numLen++;
				number /= 10;
			}
			number = (r * col + c + 2);
			while (numLen > 0) {
				ret[(curH + 1) * (col * width + 2) + c * width + numLen] = (char) ('0' + number % 10);
				number /= 10;
				numLen--;
			}
		}
		int rIdx = 0;
		for (int h = 0; h < rh; h++) {					
			for (int w = 0; w < rw + 1; w++) {
				if(w >wMax) {
					ret[(curH + h + 1 + hOffset) * (col * width + 2) + c * width + w + 1 + wOffset] = '.';
					ret[(curH + h + 1 + hOffset) * (col * width + 2) + c * width + w + 2 + wOffset] = '.';
					ret[(curH + h + 1 + hOffset) * (col * width + 2) + c * width + w + 3 + wOffset] = '.';
				}
				
				if (rIdx >= s.length())
					break;
				char _ch = s.charAt(rIdx++);
				if (_ch == '\n')
					break;

				ret[(curH + h + 1 + hOffset) * (col * width + 2) + c * width + w + 1 + wOffset] = _ch;
			}
			
		}
	}
	
	public void setGridTag(int r, int c, String str) {
		int xOffset = (width - str.length()) / 2;
		if(xOffset <= 0)
			return;
		
		int tmpH = 0;
		for(int i = 0; i < r; i++)
			tmpH += heights[i];
		
		int _r = (tmpH + heights[r]) * (col * width + 2) + c * width + xOffset + 1;
		for(int i = 0; i < str.length(); i++) 
			ret[_r + i] = str.charAt(i);
		
	}
		
	protected void makeFrame() {

		// Frame 틀 생성
		for (int vh = 0, tmpH = 0; vh < row; vh++) {
			// 맨 위
			for (int x = 0; x < col * width + 1; x++)
				ret[x] = '_';

			for (int y = 0; y < heights[vh]; y++) {
				for (int x = 0; x < col * width + 1; x++) {
					int idx = (tmpH + y + 1) * (col * width + 2) + x;
					if (x % width == 0)
						ret[idx] = '|';
					else if (y == heights[vh] - 1)
						ret[idx] = '_';
				}
			}
			tmpH += heights[vh];
		}
	}

	
	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public int getWidth() {
		return width;
	}

	public boolean isNumeric() {
		return numeric;
	}

	
	
}
