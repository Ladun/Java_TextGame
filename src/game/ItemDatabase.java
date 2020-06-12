package game;

import java.util.HashMap;
import java.util.Map;

import game.item.ItemInfo;

public class ItemDatabase {

	private Map<Integer, ItemInfo> itemInfos = new HashMap<>();
	
	public ItemDatabase() {
		itemInfos.put(0, new ItemInfo("Gold Bar", 0, 10));
		itemInfos.put(1, new ItemInfo("Tree Branch", 1, 10));
	}
	
	public ItemInfo getItemInfo(int idx) {
		return itemInfos.get(idx);
	}
}
