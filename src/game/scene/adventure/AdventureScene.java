package game.scene.adventure;

import game.GameManager;
import game.object.Enemy;
import game.resource.RenderEnum;
import game.scene.AbstractScene;
import game.scene.SceneManager.SceneType;
import game.util.TextPrinter;

public class AdventureScene extends AbstractScene{

	private int mapIdx = 0;
	
	private MapData mapData;
		
	public AdventureScene() {
		
	}
	@Override
	public void show(GameManager gm) {
		currentPos = 1;
		switch(currentPos) {
		case 0: {// 움직이는 도중

			TextPrinter.print("0. 메뉴");
			
			int in = gm.getInput().getInt(0, 3);
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);
			}

			break;
		}
		case 1: {// 전투
			TextPrinter.printFrame(3, 2, TextPrinter.FRAME_IMAGE_WIDTH, false, 
					gm.getPlayInfo().getPlayer().getType(),
					gm.getPlayInfo().getTeamMember(0) == null? RenderEnum.NONE: gm.getPlayInfo().getTeamMember(0).getType(),
					gm.getPlayInfo().getTeamMember(1) == null? RenderEnum.NONE: gm.getPlayInfo().getTeamMember(1).getType(),
					RenderEnum.NONE, RenderEnum.TAG_VS, RenderEnum.NONE);
			int in = gm.getInput().getInt(0, 3);

			break;
		}
		case 2: {// 이외
			
			break;
		}
		}
		
	}
	
	public void setMapIdx(int idx) {
		mapIdx = idx;
	}

}
