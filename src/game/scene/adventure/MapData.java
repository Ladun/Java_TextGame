package game.scene.adventure;

public class MapData {
	
	public static final int DIFFICULTY_EASY = 10;
	public static final int DIFFICULTY_NORMAL = 20;
	public static final int DIFFICULTY_HARD = 30;
	
	public class Room {

		private int eastIdx;
		private int westIdx;
		private int southIdx;
		private int northIdx;
	}

	private Room[] rooms;
	private int currentDifficulty ;
	
	public MapData() {
		rooms = new Room[10];
	}
	
	public static MapData makeMapData() {
		
		return new MapData();
	}
}
