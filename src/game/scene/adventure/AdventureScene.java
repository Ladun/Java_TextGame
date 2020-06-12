package game.scene.adventure;

import game.GameManager;
import game.object.Adventurer;
import game.object.AttackInfo;
import game.object.Enemy;
import game.object.Entity;
import game.resource.FixedGridFrame;
import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.resource.Resources;
import game.scene.AbstractScene;
import game.scene.SceneManager.SceneType;
import game.scene.adventure.MapData.Room;
import game.util.TextPrinter;

public class AdventureScene extends AbstractScene{
	
	private static final int BATTLE_DELAY = 2000;

	private MapData mapData;
	private FixedGridFrame fightFrame;
	private MapData.Room currentRoom;
	
	private int fightState;
	private int selectAdv, selectEnemy, selectSkill;
	private int treasureIdx;
	
	public AdventureScene() {
		fightFrame = new FixedGridFrame(3, 4, GridFrame.IMAGE_WIDTH, new int[] {GridFrame.IMAGE_HEIGHT + 2, 1, GridFrame.IMAGE_HEIGHT + 2}, false);
	
	}
	
	@Override
	public void clear() {		
		fightState = 0;
		selectAdv = -1;
		selectEnemy = -1;
		selectSkill = 0;
		treasureIdx = 0;
	}
	
	@Override
	public void show(GameManager gm) {

		switch(currentPos) {
		case 0: {// �����̴� ����
			String[] strs = new String[5];
			int idx = 1;
			strs[0] = "�޴�";
			if ((currentRoom.openDir & Room.OPEN_EAST) != 0)
				strs[idx++] = "����";
			if ((currentRoom.openDir & Room.OPEN_WEST) != 0)
				strs[idx++] = "����";
			if ((currentRoom.openDir & Room.OPEN_SOUTH) != 0)
				strs[idx++] = "����";
			if ((currentRoom.openDir & Room.OPEN_NORTH) != 0)
				strs[idx++] = "����";
			
			if(mapData.allIsFound())
				strs[idx] = "���� ������";
			System.out.println(mapData.getMapString());
			int len = TextPrinter.print(strs);
			
			int in = gm.getInput().getInt(0, len - 1);
			if(in == 0) {
				gm.getSceneManager().setScene(SceneType.OPTION);
			}
			else if(in == idx) {
				currentPos = 3;
			}
			else {
				int dir = 0;
				if(strs[in].equals("����"))
					dir = 0;
				else if (strs[in].equals("����"))
					dir = 1;
				else if (strs[in].equals("����"))
					dir = 2;
				else if (strs[in].equals("����"))
					dir = 3;
				
				
				if(mapData.canMove(dir)) {
					gm.addTimes(8);
					currentRoom = mapData.move(dir);
					
					switch(currentRoom.type) {
					case BATTLE:
						if(!currentRoom.detected)
							currentPos = 1;
						break;
					case TREASURE:
						if(!currentRoom.detected)
							currentPos = 2;
						break;
					}
					currentRoom.detected = true;
				}
			}
			

			break;
		}
		case 1: {// ����	
			// TODO: ����� 1�Ͼ� �Ѿ�鼭 �ϴµ�, �̰ſ� �� ��ü���� �ӵ��� �߰��ؼ� �ӵ��� ���� ��ü���� �����ϰ�
			Adventurer selectedAdventurer =null;
			if(selectAdv != -1)
				selectedAdventurer = selectAdv == 0? gm.getPlayInfo().getPlayer(): gm.getPlayInfo().getTeamMember(selectAdv - 1);
			Enemy selectedEnemy = null;
			if(selectEnemy != -1)
				selectedEnemy = currentRoom.enemys[selectEnemy];
			
			fightFrame.setting(
				Resources.getAscii(getType(gm.getPlayInfo().getPlayer())),
				Resources.getAscii(getType(gm.getPlayInfo().getTeamMember(0))),
				Resources.getAscii(getType(gm.getPlayInfo().getTeamMember(1))),
				selectedAdventurer == null? " " :selectedAdventurer.getInfo(),
				"VS", "", "", "VS", 
				selectedEnemy == null? " " :selectedEnemy.getInfo(),
				Resources.getAscii(getType(currentRoom.enemys[0])),
				Resources.getAscii(getType(currentRoom.enemys[1])),
				Resources.getAscii(getType(currentRoom.enemys[2]))
				);
			
			fightFrame.setGridTag(0, 1, getAlive(gm.getPlayInfo().getTeamMember(0)));
			fightFrame.setGridTag(0, 2, getAlive(gm.getPlayInfo().getTeamMember(1)));
			for(int i = 0; i < 3; i ++)
				fightFrame.setGridTag(2, 1 + i, getAlive(currentRoom.enemys[i]));
			
			TextPrinter.printFrame(fightFrame);
			
			int in = 0;
			switch(fightState) {
			case 0:{ // ������ �ڱ� �÷��̾�
				String[] strs = new String[5];
				int[] idx = new int[2];
				strs[0] = "�޴�";
				strs[1] = gm.getPlayInfo().getPlayer().getStateInfo().getName();
				for(int i = 0, k = 0; i <2 ;i++)
					if(gm.getPlayInfo().getTeamMember(i) != null && gm.getPlayInfo().getTeamMember(i).isActive()) {
						strs[2 + k] =gm.getPlayInfo().getTeamMember(i).getStateInfo().getName();
						idx[k] = i;
						k++;
					}
				int len = TextPrinter.print(strs);
			
				in = gm.getInput().getInt(0, len - 1);
				if(in == 0)
					gm.getSceneManager().setScene(SceneType.OPTION);					
				else {
					fightState = 1;
					if(in >= 2)
						selectAdv = idx[in - 2] + 1;
					else
						selectAdv = 0;
				}
				
				break;
			}			
			case 1:{
				String[] strs = new String[5];
				int[] idx = new int[3];
				strs[0] = "�޴�";
				strs[1] = "�ڷ� ����";
				for(int i = 0, k = 0; i <3 ;i++)
					if(currentRoom.enemys[i] != null && currentRoom.enemys[i].isActive()) {
						strs[2 + k] = currentRoom.enemys[i].getStateInfo().getName();
						idx[k] = i;
						k++;
					}
				int len = TextPrinter.print(strs);
				in = gm.getInput().getInt(0, len - 1);
				
				if(in == 0)
					gm.getSceneManager().setScene(SceneType.OPTION);	
				else if(in == 1) {
					fightState = 0;
					selectAdv = -1;
				}
				else{
					fightState = 2;
					selectEnemy = idx[in - 2];
				}
				break;
			}
			case 2:{ // ������ �÷��̾��� ���� ���
				String[] strs = new String[5];
				strs[0] = "�޴�";
				strs[1] = "�ڷ� ����";
				for(int i = 0; i < selectedAdventurer.getSkillInfo().length; i++) {
					if(selectedAdventurer.getSkillInfo()[i] != null)
						strs[2 + i] = selectedAdventurer.getSkillInfo()[i];
					else						
						break;
				}
				
				int len = TextPrinter.print(strs);
				in = gm.getInput().getInt(0, len - 1);
				if(in == 0)
					gm.getSceneManager().setScene(SceneType.OPTION);	
				else if(in == 1) {
					fightState = 1;
					selectEnemy = -1;
				}
				else{
					selectSkill = in - 2;
				}
				
				// Attack---------------------------
				AttackInfo atInfo = selectedAdventurer.getDamage(selectSkill);
				// TODO: ������ �߰�, ���� ���� �߰�, �÷��̾� �׾��� �� ���� ����, ����, ������ ����
				if(selectedAdventurer.attack(selectedEnemy,atInfo))				
					TextPrinter.printWithDelay(BATTLE_DELAY, 
							selectedAdventurer.getStateInfo().getName() + " did "+ atInfo.getDamage() + " damage to " + selectedEnemy.getStateInfo().getName());
				else
					TextPrinter.printWithDelay(BATTLE_DELAY, 
							selectedEnemy.getStateInfo().getName() + " avoided " +selectedAdventurer.getStateInfo().getName() + "`s attack");
				
  				boolean flag = true;
				for(int i = 0; i < currentRoom.enemys.length;i++) {
					if(currentRoom.enemys[i] != null && currentRoom.enemys[i].isActive()) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					fightState = 0;
					currentPos = 2;
					treasureIdx = 0;
				}
				else
					fightState = 3;
				
				break;
			}
			case 3:{
				// ���� ���� �����°�
				int attackIdx = (int)(Math.random() * 3);
				while(currentRoom.enemys[attackIdx] == null || (currentRoom.enemys[attackIdx] != null && !currentRoom.enemys[attackIdx].isActive() )) 
					attackIdx = (int)(Math.random() * 3);
				
				int targetIdx = (int)(Math.random() * 3);
				while(targetIdx > 0 && gm.getPlayInfo().getTeamMember(targetIdx - 1) == null)
					targetIdx = (int)(Math.random() * 3);
				
				selectedAdventurer = targetIdx == 0? gm.getPlayInfo().getPlayer(): gm.getPlayInfo().getTeamMember(targetIdx - 1);
				selectedEnemy = currentRoom.enemys[attackIdx];
				
				AttackInfo atInfo = selectedEnemy.getDamage((int)(Math.random() * selectedEnemy.getMaxSkill()));
				if(selectedEnemy.attack(selectedAdventurer, atInfo))
					TextPrinter.printWithDelay(BATTLE_DELAY, 
							selectedEnemy.getStateInfo().getName() + " did "+ atInfo.getDamage() + " damage to " + selectedAdventurer.getStateInfo().getName());
				else
					TextPrinter.printWithDelay(BATTLE_DELAY, 
							selectedAdventurer.getStateInfo().getName() + " avoided " +selectedEnemy.getStateInfo().getName() + "`s attack");
				

  				if(gm.getPlayInfo().getPlayer().isActive()) {
					selectAdv = -1;
					selectEnemy = -1;
					fightState = 0;
  				}
  				else {
  					TextPrinter.printWithDelay(3000, "����� �׾����ϴ�.");
  					gm.setRunning(false);
  				}
				break;
			}
			}

			break;
		}
		case 2: {// ����
			if(currentRoom.treasures[treasureIdx] != null) {
				TextPrinter.printWithTag(RenderEnum.TAG_TREASURE);
				System.out.println("[ " + currentRoom.treasures[treasureIdx].toString() + " ]");
				TextPrinter.print("�޴�", "������", "������");
				int in = gm.getInput().getInt(0, 3);
				
				switch(in) {
				case 0:
					gm.getSceneManager().setScene(SceneType.OPTION);
					break;
				case 1:
					int left = gm.getPlayInfo().getInv().addItem(currentRoom.treasures[treasureIdx], 1);
					if(left != 0)
						TextPrinter.printWithDelay(500, "Inventory is Full, " + left + " Item(s) discarded");
					break;
				}
			}
			do {
				treasureIdx++;
			} while (treasureIdx < currentRoom.treasures.length && currentRoom.treasures[treasureIdx] == null);

			if (treasureIdx == currentRoom.treasures.length)
				currentPos = 0;
			break;
		}
		case 3:{
			TextPrinter.printWithDelay(3000, "������ �������ϴ�. \n������ ���ư��ϴ�.");
			gm.getSceneManager().setScene(SceneType.MAIN);
			break;
		}
		}
		
	}
	
	private RenderEnum getType(Entity entity) {
		if(entity == null) return RenderEnum.NONE;
		return entity.getType();
	}
	private String getAlive(Entity entity) {
		if(entity == null) return "";
		if(!entity.isActive()) return "Dead";
		return "";
	}
	
	public void setting(GameManager gm, int idx,int difficulty) {		
		MapData.MapType type = MapData.MapType.FOREST;
		if(idx < 4)
			 type = MapData.MapType.FOREST;
		mapData = MapData.makeMapData(gm, type, difficulty);
		currentRoom = mapData.getCurrentRoom();
	}

}
