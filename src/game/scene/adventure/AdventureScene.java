package game.scene.adventure;

import game.GameManager;
import game.resource.FixedGridFrame;
import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.scene.AbstractScene;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class AdventureScene extends AbstractScene{

	private int mapIdx = 0;
	
	private MapData mapData;
	private FixedGridFrame fightFrame;
	
	public AdventureScene() {
		fightFrame = new FixedGridFrame(3, 4, GridFrame.IMAGE_WIDTH, new int[] {GridFrame.IMAGE_HEIGHT, 1, GridFrame.IMAGE_HEIGHT}, false);
	
	}
	@Override
	public void show(GameManager gm) {

		switch(currentPos) {
		case 0: {// 움직이는 도중
			System.out.println(mapData.getMapString());
			TextPrinter.print("메뉴","동쪽", "서쪽", "남쪽", "북쪽");
			
			int in = gm.getInput().getInt(0, 3);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
			default:
				if(mapData.canMove(in - 1)) {
					mapData.move(in - 1);
					gm.addTimes(8);
				}
			}

			break;
		}
		case 1: {// 전투	
			fightFrame.setting(
				gm.getPlayInfo().getPlayer().getType(),
				gm.getPlayInfo().getTeamMember(0) == null? RenderEnum.NONE: gm.getPlayInfo().getTeamMember(0).getType(),
				gm.getPlayInfo().getTeamMember(1) == null? RenderEnum.NONE: gm.getPlayInfo().getTeamMember(1).getType(),
				RenderEnum.NONE,
				RenderEnum.NONE, RenderEnum.TAG_VS, RenderEnum.NONE
				);
			TextPrinter.printFrame(fightFrame);
			int in = gm.getInput().getInt(0, 3);

			break;
		}
		case 2: {// 이외
			
			break;
		}
		}
		
	}
	
	public void setting(GameManager gm, int idx,int difficulty) {
		mapIdx = idx;
		mapData = MapData.makeMapData(gm, difficulty);
	}

}
