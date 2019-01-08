package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import render.SpriteRenderer;
import sprites.Sprite;
import structs.Grid;
import structs.Path;
import structs.Tile;

public abstract class GameMap {
	public static List<Sprite> sprites;
	public Sprite background;

	public GameMap(String backgroundName) {
		sprites = new ArrayList<>();
		background = new Sprite(backgroundName);

		Grid.init();
		drawGrid();
	
		new Path(this).draw();
	}

	public static Sprite addObject(Sprite sprite, boolean makeEmpty) {
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridY()][(int) sprite.getGridX()].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridY()][(int) sprite.getGridX()].objectIndex = sprites
				.indexOf(sprite);
		
		return sprite;
	}
	
	private void drawGrid() {
		for (int j = 0; j < Grid.tiles[0].length; j++) {
			Grid.tiles[0][j].isEmpty = false;
		}
		
		for (int i = 1; i < Grid.tiles.length; i++) {
			for (int j = 0; j < Grid.tiles[i].length; j++) {
				addObject(new Sprite("square.png", j, i), true);
			}
		}
	}
	
	

	public void dispose()
	{
		sprites.clear();
	}
}
