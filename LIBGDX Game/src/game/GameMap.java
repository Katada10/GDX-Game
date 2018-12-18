package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import render.SpriteRenderer;
import structs.Grid;
import structs.Path;
import structs.Sprite;
import structs.Tile;

public abstract class GameMap {
	public static List<Sprite> sprites;

	public GameMap(String backgroundName) {
		sprites = new ArrayList<>();
		Sprite background = new Sprite(backgroundName);
		sprites.add(background);

		Grid.init();
		drawGrid();
	
		new Path(this).draw();
	}

	public static Sprite addObject(String fileName, int gridX, int gridY, boolean makeEmpty) {
		Sprite sprite = new Sprite(fileName, gridX, gridY);
		
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].objectIndex = sprites
				.indexOf(sprite);
		
		return sprite;
	}
	
	public static String addObject(String fileName, int gridX, int gridY, boolean makeEmpty, String name) {
		Sprite sprite = new Sprite(fileName, gridX, gridY);
		sprite.spriteTag = name;
		
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].objectIndex = sprites
				.indexOf(sprite);
		
		return sprite.spriteTag;
	}

	private void drawGrid() {
		for (int j = 0; j < Grid.tiles[0].length; j++) {
			Grid.tiles[0][j].isEmpty = false;
		}
		
		for (int i = 1; i < Grid.tiles.length; i++) {
			for (int j = 0; j < Grid.tiles[i].length; j++) {
				addObject("square.png", j, i, true);
			}
		}
	}
	
	

	public void dispose()
	{
		sprites.clear();
	}
}
