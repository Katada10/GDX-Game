package structs;

import com.badlogic.gdx.math.Vector2;

import core.Main;

public class Grid {
	public static Tile[][] tiles;
	public static int yLen = (Main.HEIGHT / Tile.sizeY), xLen = (Main.WIDTH / Tile.sizeX);
	
	public static void init()
	{
		tiles = new Tile[yLen][xLen];
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
	
	public static Tile getTile(Vector2 mousePos) {
		int xIndex = (int) Math.floor(mousePos.x / Tile.sizeX);
		int yIndex = (int) Math.floor(mousePos.y / Tile.sizeY);

		return Grid.tiles[yIndex][xIndex];
	}
}
