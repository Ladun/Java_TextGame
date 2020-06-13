package game;

import game.file.SaveData;
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
		money = 300;
	}
	
	public PlayInfo(GameManager gm, SaveData saveData) {
		player = new Adventurer(saveData.getPlayer());
		team_members = new Adventurer[2];
		for(int i = 0; i < 2; i++) {
			if(!saveData.getTeam_members()[i].isNull())
				team_members[i] =  new Adventurer(saveData.getTeam_members()[i]);
		}
		money = saveData.getMoney();
		inv = new Inventory(gm, saveData.getInv());
	}
	
	public String[] getAdventurerInfo() {
		return new String[] {
				player.getInfo(),
				team_members[0] == null? "":team_members[0].getInfo(),
				team_members[1] == null? "":team_members[1].getInfo()				
		};
	}
	public int getEmptyAdventurerSpace() {
		for(int i = 0;i < 2; i++) {
			if(team_members[i] == null)
				return i;
		}
		return -1;
	}
	
	public Inventory getInv() {
		return inv;
	}

	public Adventurer getPlayer() {		
		return player;
	}
	

	public void useMoney(int amount) {
		money -= amount;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setTeamMember(int idx, Adventurer adv) {
		team_members[idx] = adv;
	}
	
	public Adventurer getTeamMember(int idx) {
		if(idx < 0 || idx >= 2)
			return null;
		return team_members[idx];
	}
}
