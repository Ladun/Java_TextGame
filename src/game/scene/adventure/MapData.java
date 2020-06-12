package game.scene.adventure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import game.GameManager;
import game.item.ItemInfo;
import game.object.Enemy;
import game.object.StateInfo;

public class MapData {

	public enum MapType{
		FOREST
	}
	
	public enum RoomType{
		EMPTY, BATTLE, TREASURE
	};
	
	public static final int DIFFICULTY_EASY = 6;
	public static final int DIFFICULTY_NORMAL = 10;
	public static final int DIFFICULTY_HARD = 14;
	private static int[] dr = new int[] {0, 0, 1, -1};
	private static int[] dc = new int[] {1, -1, 0, 0};
	
	public class Room {		
		
		public static final int OPEN_EAST = 1;
		public static final int OPEN_WEST = 2;
		public static final int OPEN_SOUTH = 4;
		public static final int OPEN_NORTH = 8;
		
		public int openDir; // 4bit¸¸ »ç¿ë
		public RoomType type;
		public boolean detected;
		
		public Enemy[] enemys;
		public ItemInfo[] treasures;
		public int rewardMoney;
		
		public void setRoom(GameManager gm,MapData md, Random rand) {
			detected = false; 
			
			int east = rand.nextInt(2);
			int west = rand.nextInt(2) << 1;
			int south = rand.nextInt(2) << 2;
			int north = rand.nextInt(2) << 3;
			
			openDir = east | west | south | north;
			
			double f = rand.nextDouble();
			if(f < 0.5) 
				type = RoomType.EMPTY;
			else if(f < 0.85) {
				int enemyCount = rand.nextInt(3) + 1;
				enemys = new Enemy[3];
				treasures = new ItemInfo[3];
				
				
				ArrayList<StateInfo> list = gm.getObjectManager().getEnemyState(md.type);
				for(int i = 0; i < enemyCount; i++)
					enemys[i] = new Enemy(list.get(rand.nextInt(list.size())));
				type = RoomType.BATTLE;				
			}
			else {
				treasures = new ItemInfo[1];
				treasures[0] = gm.getItemDatabse().getItemInfo(0);
				type = RoomType.TREASURE;				
			}
			
		}

	}

	class Info {
		public int r, c;
		public int preDir;
		public Info(int r, int c, int preDir) {
			this.r = r;
			this.c = c;
			this.preDir = preDir;
		}
	}
		

	private Room[][] rooms;
	private int currentDifficulty ;
	private int spawnR, spawnC;
	private int pr, pc;
	private MapType type;
	
	private MapData(MapType type, int currentDifficulty) {
		this.currentDifficulty = currentDifficulty;
		rooms = new Room[currentDifficulty / 2][currentDifficulty];
		this.type = type;
	}
	
	public Room move(int dir) {
		pr += dr[dir];
		pc += dc[dir];	
		
		return rooms[pr][pc];
	}
	
	public Room getCurrentRoom() {
		return rooms[pr][pc];
	}
	
	public boolean canMove(int dir) {
		return (rooms[pr][pc].openDir & (1 << dir)) != 0;
	}
	
	public boolean allIsFound() {
		for(int r = 0; r < currentDifficulty / 2; r++) {
			for(int c = 0; c < currentDifficulty; c++) {
				if(rooms[r][c] != null && !rooms[r][c].detected) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String getMapString() {

		int stW = currentDifficulty - 1, edW = 0;
		int stH = -1, edH = 0;
		for(int r = 0; r < currentDifficulty / 2; r++) {
			boolean flag = false;

			for(int c = 0; c < currentDifficulty; c++) {
				if(rooms[r][c] != null)
				{
					flag = true;
					if(edW < c)
						edW = c;
					if(stW > c)
						stW = c;
				}
			}
			if(flag) {
				if(stH == - 1)
					stH = r;
				edH = r;				
			}
		}
		int width = 4 * (edW - stW + 1) + 2;
		char[] ret = new char[(2 * (edH - stH + 1) + 1) * width];
		for(int r = 0; r < 2 * (edH - stH + 1) + 1; r++)
		{
			for(int c = 0; c < width - 1; c++) {
				ret[r * width + c] = ' ';
			}
			ret[r * width + width -1] = '\n';
		}
		
		for(int r = stH; r <= edH; r++) {
			for(int c = stW; c <= edW; c++) {
				if(rooms[r][c] != null)
				{
					int _r = (r - stH) * 2;
					int _c = (c - stW) * 4;
					
					if(_r == 0) 
						for(int i = 1; i < 4; i ++) 
							ret[_c + i] = '_';
					if(_c == 0)
						for(int i = 0; i < 2; i++)
							ret[(_r + i + 1) * width] = '|';
					
					if((rooms[r][c].openDir & Room.OPEN_EAST) == 0)
						ret[(_r + 1) * width + _c + 4] = '|';
					ret[(_r + 2) * width + _c + 4] = '|';
					
					ret[(_r + 2) * width + _c + 1] = '_';
					if((rooms[r][c].openDir & Room.OPEN_SOUTH) == 0)
						ret[(_r + 2) * width + _c + 2] = '_';
					ret[(_r + 2) * width + _c + 3] = '_';
					
					if(_r - 1 >= 0) {
						if(rooms[r - 1][c] == null) {
							for(int i = 1; i < 4; i ++) 
								ret[_r * width + _c + i] = '_';						
						}
					}
					if(_c - 1 >= 0) {
						if(rooms[r][c - 1] == null) {
							ret[(_r + 1) * width + _c] = '|';
							ret[(_r + 2) * width + _c] = '|';							
						}
					}
					
					if(rooms[r][c].detected) {
						char t = ' ';
						switch(rooms[r][c].type) {
							case EMPTY: t = 'E'; break;
							case BATTLE: t = 'B'; break;
							case TREASURE: t = 'T'; break;
						}
						ret[((r - stH) * 2 + 1) * width + (c - stW) * 4 + 1] = t;						
					}
				}
			}
		}
		
		ret[((pr - stH) * 2 + 1) * width + (pc - stW) * 4 + 2] = 'O';
		
		return new String(ret);
	}
	
	public static MapData makeMapData(GameManager gm,MapType md, int currentDifficulty) {
		MapData data = new MapData(md, currentDifficulty);
		Random rand = new Random(gm.getDays());
		Queue<Info> q = new LinkedList<>();
		
		data.spawnR = rand.nextInt(currentDifficulty / 2);
		data.spawnC = rand.nextInt(currentDifficulty);	
		data.pr = data.spawnR;
		data.pc = data.spawnC;
		
		q.add(data.new Info(data.spawnR, data.spawnC, -1));
		while(!q.isEmpty()) {
			Info info = q.remove();
			Room room = data.new Room();
			room.setRoom(gm, data, rand);
			
			if(info.r == data.spawnR && info.c == data.spawnC) {
				room.type = RoomType.EMPTY;
				room.detected = true;
			}
			
			if(info.preDir != -1) {
				int openedDir = info.preDir;
				
				if(openedDir == 0) openedDir = 1;
				else if(openedDir == 1) openedDir = 0;
				else if(openedDir == 2) openedDir = 3;
				else if(openedDir == 3) openedDir = 2;
				
				room.openDir = room.openDir | (1 << openedDir);				
			}
			
			if(info.r == 0) 
				room.openDir = (room.openDir & (~Room.OPEN_NORTH));
			if(info.r == currentDifficulty / 2 - 1) 
				room.openDir = (room.openDir & (~Room.OPEN_SOUTH));
			if(info.c == 0) 
				room.openDir = (room.openDir & (~Room.OPEN_WEST));
			if(info.c == currentDifficulty - 1) 
				room.openDir = (room.openDir & (~Room.OPEN_EAST));
			
			
			for(int i = 0; i < 4; i++) {
				int nr = info.r + dr[i];
				int nc = info.c + dc[i];
				if(nr < 0 || nr >= currentDifficulty / 2 || nc < 0 || nc >= currentDifficulty)
					continue;
				
				if(data.rooms[nr][nc] != null)
					continue;
				
				if((room.openDir & (1 << i)) != 0) 
					q.add(data.new Info(nr, nc, i));
			}
			data.rooms[info.r][info.c]= room; 
		}	
		
		return data;
	}

	
}
