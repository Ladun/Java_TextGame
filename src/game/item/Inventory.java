package game.item;

import game.resource.GridFrame;

public class Inventory {
	
	class Slot{
		Item item;
		int count;
		public Slot() {
			item = null;
			count = 0;
		}
	}
	
	public static final int ROW_COUNT = 4;
	public static final int COL_COUNT = 10;

	private Slot[] slots;
	private GridFrame invFrame;
	
	public Inventory() {
		slots = new Slot[ROW_COUNT * COL_COUNT];
		for(int i = 0; i < slots.length;i++) {
			slots[i] = new Slot();
		}
		invFrame = new GridFrame(ROW_COUNT, COL_COUNT, 10, true);
	}
	
	public int addItem(ItemInfo itemInfo, int amount) {
		int idx = findIdx(itemInfo.getId());
		
		if(idx == -1)
			return amount;
		
		int free = itemInfo.getMaxCount() - slots[idx].count;
		if(free >= amount) {
			if(slots[idx].item == null)
				slots[idx].item = new Item(itemInfo);
			slots[idx].count += amount;
			amount = 0;
		}
		else {
			amount = amount - free;
			amount = addItem(itemInfo, amount);
		}
		return amount;
		
	}
	
	private int findIdx(int id) {
		int m = -1;
		
		for(int i = 0; i < ROW_COUNT * COL_COUNT; i++) {
			if(slots[i].item == null)
			{
				if(m == -1)
					m = i;
			}
			else if(slots[i].item.getInfo().getId() == id) {
				if( slots[i].count < slots[i].item.getInfo().getMaxCount())
					return i;
			}
		}
		return m;
		
	}
	
	public GridFrame getGridFrame() {
		String[] strs = new String[ROW_COUNT * COL_COUNT];
		for(int idx = 0; idx < ROW_COUNT * COL_COUNT;  idx++) {
				if(slots[idx].item == null)
					strs[idx] = "";
				else
					strs[idx] = slots[idx].item.getInfo().toString();			
		}
		invFrame.setting(strs);
		
		return invFrame;
	}
}
