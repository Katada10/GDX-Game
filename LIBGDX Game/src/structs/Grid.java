package structs;

import com.badlogic.gdx.math.Vector2;

import core.Main;

public class Grid {
	/*
	 * Creates an array of tiles to make a grid. Also retrieves grid position based on pixel position.
	 */
	public static Tile[][] tiles;
	public static int tileSize = 100;
	public static int length = (Main.SCALE / tileSize);

	/**
	 * Initializes grid size and calls the generate method.
	 */
	public static void init() {
		tiles = new Tile[length][length];
		generate();
	}

	/**
	 * Creates the new tiles to fill up the grid.
	 */
	private static void generate() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(j, i);
			}
		}
	}

	/**
	 * Returns a tile based on the pixel coordinates, useful to know which tile 
	 * the mouse clicked and coordinates of tile can be used.
	 * @param pixelCoordinates
	 * @return
	 */
	public static Tile getTile(Vector2 pixelCoordinates) {
		int xIndex = (int) Math.floor(pixelCoordinates.x / tileSize);
		int yIndex = (int) Math.floor(pixelCoordinates.y / tileSize);

		if (xIndex < 0 || yIndex < 0 || xIndex >= Grid.length || yIndex >= Grid.length) {
			return null;
		} else {
			return Grid.tiles[yIndex][xIndex];
		}
	}
}
