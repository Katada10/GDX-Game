package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import render.SpriteRenderer;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class GameMap {
	public List<Sprite> sprites;
	Sprite sprite = null;
	boolean selected = false;

	public GameMap(String backgroundName) {
		sprites = new ArrayList<>();
		
		Sprite back = new Sprite(backgroundName);
		sprites.add(back);

		Grid.init();
	}

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

	public Tile getTile(Vector2 mousePos) {
		int xIndex = (int) Math.floor(mousePos.x / Tile.sizeX);
		int yIndex = (int) Math.floor(mousePos.y / Tile.sizeY);

		return Grid.tiles[yIndex][xIndex];
	}

	public void checkClicked() {
		Tile t = getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (!t.isEmpty && Input.dragging && !selected) {
			sprite = sprites.get(t.objectIndex);
			selected = true;
		}

		if (selected && Input.dragging) {
			drag(sprite);
		}

		if (!Input.dragging) {
			selected = false;
		}

	}

	public void drag(Sprite s) {
		Tile t = getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile spriteTile = Grid.tiles[(int) sprite.getGridPosition().y][(int) sprite.getGridPosition().x];

		if (t.isEmpty) {
			if (t.xCoord != spriteTile.xCoord) {
				sprite.setPos(t.xCoord, (int) sprite.getGridPosition().y);
				spriteTile.isEmpty = true;
				t.isEmpty = false;
				t.objectIndex = sprites.indexOf(sprite);
			}

			if (t.yCoord != spriteTile.yCoord) {
				sprite.setPos((int) sprite.getGridPosition().x, t.yCoord);
				spriteTile.isEmpty = true;
				t.isEmpty = false;
				t.objectIndex = sprites.indexOf(sprite);
			}
		}
	}
	
	public void dispose()
	{
		sprites.clear();
	}
}
