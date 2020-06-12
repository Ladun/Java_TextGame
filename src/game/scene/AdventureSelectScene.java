package game.scene;

import java.util.ArrayList;

import game.GameManager;
import game.resource.GridFrame;
import game.resource.RenderEnum;
import game.scene.SceneManager.SceneType;
import game.scene.adventure.AdventureScene;
import game.scene.adventure.MapData;
import game.util.TextPrinter;

public class AdventureSelectScene extends AbstractScene{
	
	private class MapInfo{
		public String[] names;
		public RenderEnum[] types;
		
		public MapInfo(String[] names, RenderEnum[] types) {
			this.names = names;
			this.types = types;
		}
	}

	private ArrayList<MapInfo> mapInfos = new ArrayList<MapInfo>();
	
	private int mapIdx = 0;
	private int selectOffset = 0;
	
	private GridFrame mapInfoFrame;
	private GridFrame difficultyFrame;
	
	public AdventureSelectScene() {
		mapInfoFrame = new GridFrame(1, 4, 29, true);
		difficultyFrame = new GridFrame(1, 3, 10, true);
		difficultyFrame.setting("EASY", "NORMAL", "HARD");
		mapInfos.add(new MapInfo(
				new String[] {"East Forest","West Forest","South Forest","North Forest"},
				new RenderEnum[] {RenderEnum.TAG_MAP_FOREST, RenderEnum.TAG_MAP_FOREST, RenderEnum.TAG_MAP_FOREST, RenderEnum.TAG_MAP_FOREST}));
	}
	@Override
	public void clear() {
		mapIdx = 0;
		selectOffset = 0;
	}
	@Override
	public void show(GameManager gm) {

		switch(currentPos) {
		case 0:{
			mapInfoFrame.setting(mapInfos.get(mapIdx).types);
			for(int i = 0; i < 4; i++) 
				mapInfoFrame.setGridTag(0, i, mapInfos.get(mapIdx).names[i]);

			TextPrinter.printFrame(mapInfoFrame);
			TextPrinter.print("메뉴", "뒤로가기", mapInfos.get(mapIdx).names[0], mapInfos.get(mapIdx).names[1], mapInfos.get(mapIdx).names[2], mapInfos.get(mapIdx).names[3], "다음", "이전");
			int	in = gm.getInput().getInt(0, 7);		
			
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);				
				break;
			case 1:
				gm.getSceneManager().setScene(SceneType.MAIN);			
				break;
			case 6:
				mapIdx = (mapIdx + 1) % mapInfos.size();
				break;
			case 7:
				mapIdx = (mapIdx + mapInfos.size() - 1) % mapInfos.size();		
				break;
				
			default:
				currentPos = 1;
				selectOffset = in - 2;
			}
			break;
		}
		case 1:{
			TextPrinter.printFrame(difficultyFrame);
			TextPrinter.print("메뉴", "뒤로가기", "EASY", "NORMAL", "HARD");
			int	in = gm.getInput().getInt(0, 4);	
			
			int difficulty = 0;
			
			switch(in) {
			case 0:
				gm.getSceneManager().setScene(SceneType.OPTION);				
				break;
			case 1:
				currentPos = 0;		
				break;
			case 2:
				difficulty = MapData.DIFFICULTY_EASY;
				break;
			case 3:
				difficulty = MapData.DIFFICULTY_NORMAL;
				break;
			case 4:
				difficulty = MapData.DIFFICULTY_HARD;
				break;
				
			default:
				currentPos = 1;
			}
			if(in >= 2) {
				gm.getSceneManager().<AdventureScene>getScene(SceneType.ADVENTURE).setting(gm, mapIdx * 4 + selectOffset, difficulty);
				gm.getSceneManager().setScene(SceneType.ADVENTURE);
			}
			break;
		}
		}
	}

}
