package structs;

import core.Main;

public class Grid {
	private Tile[][] tiles;
	private int firstX = 0, firstY = 0;
	
	public static int xLen, yLen;
	
	public Grid(int numOfTilesX, int numOfTilesY)
	{
		Tile.sizeX = Main.WIDTH / numOfTilesX;
		Tile.sizeY = Main.HEIGHT / numOfTilesY;
		xLen = numOfTilesX;
		yLen = numOfTilesY;
		
		tiles = new Tile[numOfTilesY][numOfTilesX];
		
		generateTiles();
	}
	
	private void generateTiles()
	{
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(firstX, firstY);
				firstX += Tile.sizeX;
			}
			firstY += Tile.sizeY;
			firstX = 0;
		}
	}

	
	public Tile[][] getTiles()
	{
		return tiles;
	}
}
