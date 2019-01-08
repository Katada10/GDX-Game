package structs;

import com.badlogic.gdx.math.Vector2;

import core.Main;

public class Grid {
	public static Tile[][] tiles;
	public static int tileSize = 100;
	public static int len = (Main.SCALE / tileSize);
	
	public static void init()
	{
		tiles = new Tile[len][len];
		generate();
	}
	
	private static void generate()
	{
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(j, i);
			}
		}
	}
	
	public static Tile getTile(Vector2 mousePos) throws Exception {
		int xIndex = (int) Math.floor(mousePos.x / tileSize);
		int yIndex = (int) Math.floor(mousePos.y / tileSize);
	
		
		if(xIndex < 0 || yIndex < 0 || xIndex >= Grid.len || yIndex >= Grid.len)
		{
			throw new Exception("Invalid Index: X = " + xIndex + " Y = " + mousePos.y);
		}
		
		return Grid.tiles[yIndex][xIndex];
	}
}
