package game;

import game.object.Adventurer;

public class PlayInfo {
	
	private Adventurer player;
	private Adventurer[] team_members; // max 2
	

	public PlayInfo(Adventurer player) {
		this.player = player;
		
	}
	
	public PlayInfo(String saveFile) {
		
	}
	
	
}