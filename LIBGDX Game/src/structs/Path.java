package structs;

import map.GameMap;
import sprites.Sprite;

public class Path {
	/*
	 * A class that manages placement of the path that the chickens will follow.
	 */

	public static int firstSizeX = 1, secondSizeX = 2, sizeY = 2;
	
	/**
	 * Method to draw either a vertical or horizontal segment of the path with the given parameters.
	 * @param startLevel
	 * @param startTile
	 * @param endTile
	 * @param yDirection
	 */
	private void drawPathSegment(int startLevel, int startTile, int endTile, boolean yDirection)
	{
		for (int i = startTile; i < endTile; i++) {
			if(yDirection)
			{				
				GameMap.addObject(new Sprite("path.jpg", startLevel, i), false);
			}
			else
			{
				GameMap.addObject(new Sprite("path.jpg", i, startLevel), false);
			}
		}
	}
	
	/**
	 * Draws all the segments correctly according to the grid size, which is reliant on screen scale.
	 */
	public void draw() {
		drawPathSegment(1, 0, firstSizeX, false);
		drawPathSegment(sizeY + 1, firstSizeX + 1, secondSizeX + (firstSizeX + 1), false);
		drawPathSegment(7, 4, 8, false);
		
		
		drawPathSegment(firstSizeX , 1, (sizeY + 1) + 1, true);
		drawPathSegment(firstSizeX + 3 , 3, (sizeY + 1) + 4, true);
	}

}
