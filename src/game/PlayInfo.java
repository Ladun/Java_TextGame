package game;

import game.item.Inventory;
import game.object.Adventurer;

public class PlayInfo {
	
	private int money;
	
	private Adventurer player;
	private Adventurer[] team_members; // max 2	
	
	//inventory
	private Inventory inv;

	public PlayInfo(Adventurer player) {
		this.player = player;
		team_members = new Adventurer[2];
		inv = new Inventory();
	}
	
	public PlayInfo(String saveFile) {
		
	}
	
	public String[] getAdventurerInfo() {
		return new String[] {
				player.getInfo(),
				team_members[0] == null? "":team_members[0].getInfo(),
				team_members[1] == null? "":team_members[1].getInfo()				
		};
	}
	
	public Adventurer getPlayer() {		
		return player;
	}
	public Adventurer getTeamMember(int idx) {
		if(idx < 0 || idx >= 2)
			return null;
		return team_members[idx];
	}
}
