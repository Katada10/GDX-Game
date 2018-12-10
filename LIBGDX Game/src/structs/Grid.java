package structs;

import core.Main;

public class Grid {
	private Tile[][] tiles;
	
	
	public Grid()
	{
		int numOfTilesX = Main.WIDTH /Tile.sizeX;
		int numOfTilesY= Main.HEIGHT /Tile.sizeY;
	
		tiles = new Tile[numOfTilesY][numOfTilesX];
	}
	

}
