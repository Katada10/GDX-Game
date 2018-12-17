package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import render.SpriteRenderer;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public abstract class GameMap {
	public List<Sprite> sprites;

	public GameMap(String backgroundName) {
		sprites = new ArrayList<>();
		Sprite background = new Sprite(backgroundName);
		sprites.add(background);

		Grid.init();
		drawGrid();
		drawPath();
	}

	public void addObject(String fileName, int gridX, int gridY, boolean makeEmpty) {
		Sprite sprite = new Sprite(fileName, gridX, gridY);
		
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].objectIndex = sprites
				.indexOf(sprite);
	}
	
	public String addObject(String fileName, int gridX, int gridY, boolean makeEmpty, String name) {
		Sprite sprite = new Sprite(fileName, gridX, gridY);
		sprite.spriteName = name;
		
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].objectIndex = sprites
				.indexOf(sprite);
		
		return sprite.spriteName;
	}

	private void drawGrid() {
		for (int i = 1; i < Grid.tiles.length; i++) {
			for (int j = 0; j < Grid.tiles[i].length; j++) {
				addObject("square.png", j, i, true);
			}
		}
	}
	
	private void drawPathSegment(int startLevel, int startTile, int endTile, boolean yDirection)
	{
		for (int i = startTile; i < endTile; i++) {
			if(yDirection)
			{				
				addObject("path.jpg", startLevel, i, false);
			}
			else
			{
				addObject("path.jpg", i, startLevel, false);
			}
		}
	}
	
	private void drawPath() {
		drawPathSegment(1, 0, 3, false);
		drawPathSegment(4, 4, 8, false);
		
		drawPathSegment(3, 1, 5, true);
	}

	public void dispose()
	{
		sprites.clear();
	}
}
