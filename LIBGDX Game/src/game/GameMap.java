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
	}
	
	public abstract void checkClicked();

	public void addObject(Sprite sprite, boolean makeEmpty) {
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x].objectIndex = sprites
				.indexOf(sprite);
	}

	public void drawGrid() {
		for (int i = 0; i < Grid.tiles.length; i++) {
			for (int j = 0; j < Grid.tiles[i].length; j++) {
				Sprite sprite = new Sprite("square.png", j, i);
				addObject(sprite, true);
			}
		}
	}

	public void dispose()
	{
		sprites.clear();
	}
}
