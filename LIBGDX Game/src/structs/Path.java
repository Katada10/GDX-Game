package structs;

import game.GameMap;

public class Path {

	public static int firstSizeX = 3, secondSizeX = 4, sizeY = 3;

	private GameMap map;
	
	public Path(GameMap map)
	{
		this.map = map;
	}
	
	private void drawPathSegment(int startLevel, int startTile, int endTile, boolean yDirection)
	{
		for (int i = startTile; i < endTile; i++) {
			if(yDirection)
			{				
				map.addObject("path.jpg", startLevel, i, false);
			}
			else
			{
				map.addObject("path.jpg", i, startLevel, false);
			}
		}
	}
	
	public void draw() {
		drawPathSegment(1, 0, firstSizeX, false);
		drawPathSegment(4, firstSizeX + 1, secondSizeX + (firstSizeX + 1), false);
		
		drawPathSegment(3, 1, (sizeY + 1) + 1, true);
	}

}
