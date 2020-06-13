package game.file;

import java.io.Serializable;

import game.GameManager;
import game.PlayInfo;
import game.scene.TavernScene;
import game.scene.SceneManager.SceneType;
public class SaveData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int days;
	private int times;
	private int money;

	private AdventurerData player;
	private AdventurerData[] team_members;

	private AdventurerData[] mercenarys;
	private int[] mercenarys_leftDays;

	private InventoryData inv;

	public SaveData() {
		player = new AdventurerData();

		team_members = new AdventurerData[2];
		for(int i = 0;i  < 2; i++)
			team_members[i] = new AdventurerData();
		mercenarys = new AdventurerData[TavernScene.MAX_ADVENTURER];
		mercenarys_leftDays = new int[TavernScene.MAX_ADVENTURER];
		for(int i = 0;i  < TavernScene.MAX_ADVENTURER; i++)
			mercenarys[i] = new AdventurerData();
		
		inv = new InventoryData();
	}	

	public int getDays() {
		return days;
	}

	public int getTimes() {
		return times;
	}

	public void setPlayInfo(GameManager gm, PlayInfo info) {
		player.setting(info.getPlayer());
		for(int i = 0;i  < 2; i++)
			team_members[i].setting(info.getTeamMember(i));
		for(int i = 0;i  < TavernScene.MAX_ADVENTURER; i++) {
			mercenarys[i].setting(gm.getSceneManager().<TavernScene>getScene(SceneType.TAVERN).getAdventurers()[i]);
			mercenarys_leftDays[i] = gm.getSceneManager().<TavernScene>getScene(SceneType.TAVERN).getLeftDays()[i];
		}
		money = info.getMoney();
		days = gm.getDays();
		times = gm.getTimes();
		inv.setting(info.getInv());
	}
	
	public int getMoney() {
		return money;
	}

	public AdventurerData getPlayer() {
		return player;
	}

	public AdventurerData[] getTeam_members() {
		return team_members;
	}

	public AdventurerData[] getMercenarys() {
		return mercenarys;
	}

	public int[] getMercenarys_leftDays() {
		return mercenarys_leftDays;
	}

	public InventoryData getInv() {
		return inv;
	}

	
	
}
