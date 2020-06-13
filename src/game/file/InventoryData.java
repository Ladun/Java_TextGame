package game.file;

import java.io.Serializable;

import game.item.Inventory;

public class InventoryData implements Serializable{
	int[] itemID;
	int[] itemAmount;

	public void setting(Inventory inv) {
		itemID = inv.getItemIDs();
		itemAmount = inv.getItemCounts();
	}

	public int[] getItemID() {
		return itemID;
	}

	public int[] getItemAmount() {
		return itemAmount;
	}
	
	
}
