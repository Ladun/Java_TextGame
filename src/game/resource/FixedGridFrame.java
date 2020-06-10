package game.resource;

public class FixedGridFrame extends GridFrame {

	public FixedGridFrame(int row, int col, int width, int[] heights, boolean numeric) {
		super(row, col, width, numeric);


		int size = (col * width + 2);
		this.heights = heights;
		for (int i = 0; i < row; i++) {
			size += (heights[i]) * (col * width + 2);
			totalHeight += heights[i];
		}

		ret = new char[size];

		clear();
		makeFrame();
	}

	@Override
	public void setting(String... strs) {
		setStrings(strs);
	}

}
