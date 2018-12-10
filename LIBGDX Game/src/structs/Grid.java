package structs;

import core.Main;

public class Grid {
	private Tile[][] tiles;
	private int firstX = 0, firstY = 0;
	
	public static int numOfTilesX = Main.WIDTH /Tile.sizeX;
	public static int numOfTilesY= Main.HEIGHT /Tile.sizeY;
	
	public Grid()
	{
		
	
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
