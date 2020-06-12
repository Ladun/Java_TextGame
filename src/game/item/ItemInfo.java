package game.item;

public class ItemInfo {

	private String name;
	private int id;
	private int maxCount;
	
	public ItemInfo(String name, int id, int maxCount) {
		this.name = name;
		this.id = id;
		this.maxCount = maxCount;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	public int getMaxCount() {
		return maxCount;
	}	
}
