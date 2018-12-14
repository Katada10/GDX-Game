package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import render.TwoRender;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class GameMap {
	private Grid grid;
	private List<Sprite> sprites;
	private boolean selected = false;

	Sprite sprite = null;
	int pPosx = 0, pPosy = 0;

	/**
	 * Initializes a new map with grid and background file
	 * 
	 * @param render
	 * @param backgroundName
	 */
	public GameMap(TwoRender render, String backgroundName) {
		this.sprites = render.sprites;

		Sprite back = new Sprite(backgroundName, new Vector2(Main.WIDTH, Main.HEIGHT));
		render.sprites.add(back);

		grid = new Grid(15, 10);
	}

	/**
	 * Adds the specified sprite at the specified x and y coordinate on the grid.
	 * 
	 * @param sprite
	 * @param x
	 * @param y
	 */

	public void addObject(Sprite sprite) {
		int x = (int) sprite.gridPosition.x;
		int y =  Grid.yLen - (int) sprite.gridPosition.y;

		int tileX = grid.getTiles()[y][x - 1].x;
		int tileY = grid.getTiles()[y][x - 1].y;

		sprite.position.x = tileX;
		sprite.position.y = tileY;

		grid.getTiles()[y][x - 1].empty = false;
		sprites.add(sprite);

		grid.getTiles()[y][x - 1].objectIndex = sprites.indexOf(sprite);
	}

	/**
	 * Draws the tiles of the grid onto the screen
	 * 
	 */
	public void drawGrid() {
		Sprite[][] sprite = new Sprite[Grid.yLen][Grid.xLen];

		for (int i = 0; i < sprite.length; i++) {
			for (int j = 0; j < sprite[i].length; j++) {
				sprite[i][j] = new Sprite("square.png", new Vector2(Tile.sizeX, Tile.sizeY));
				sprite[i][j].position.x = grid.getTiles()[i][j].x;
				sprite[i][j].position.y = grid.getTiles()[i][j].y;
				sprites.add(sprite[i][j]);
			}
		}
	}

	public void checkClicked() {
		int tileX = getTileX(Input.mouseX);
		int tileY = (Grid.yLen - 1) - getTileY(Input.mouseY);
		
		Tile t = grid.getTiles()[tileY][tileX];
		
		if (Input.dragging && !selected) {
			if (!t.empty) {
				sprite = sprites.get(t.objectIndex);
				selected = true;
				pPosx = tileX;
				pPosy = tileY;
			}
		}

		if (Input.dragging && selected) {
			pPosx = getTileX((int) sprite.position.x);
			pPosy = (Grid.yLen - 1) - getTileY((int) sprite.position.y);
			drag(sprite, pPosx, pPosy);
		} else {
			selected = false;
		}

	}

	private void drag(Sprite sprite, int tileX, int tileY) {

		int x = getTileX(Input.mouseX);
		int y = (Grid.yLen - 1) - getTileY(Input.mouseY);

		if (x != tileX) {
			sprite.position.x = grid.getTiles()[y][x].x;
			grid.getTiles()[tileY][tileX].empty = true;
			grid.getTiles()[y][x].empty = false;
			grid.getTiles()[y][x].objectIndex = sprites.indexOf(sprite);
		}
		if (y != tileY) {
			sprite.position.y = grid.getTiles()[y][x].y;
			grid.getTiles()[tileY][tileX].empty = true;
			grid.getTiles()[y][x].empty = false;
			grid.getTiles()[y][x].objectIndex = sprites.indexOf(sprite);
		}
	}

	private int getTileX(int mouseX) {
		if (mouseX >= Tile.sizeX * (grid.getTiles()[0].length - 1)
				&& mouseX <= Tile.sizeX * (grid.getTiles()[0].length)) {
			return (grid.getTiles()[0].length - 1);
		}

		int min = 0, max = grid.getTiles()[0].length - 1;
		Tile[] tiles = grid.getTiles()[0];
		int avg = 0;

		for (int i = 0; i < 8; i++) {
			int x = avg;
			avg = (min + max) / 2;

			if (x == avg) {
				return avg;
			}

			if (tiles[avg].x < mouseX) {
				min = avg;
			} else if (tiles[avg].x > mouseX) {
				max = avg;
			}
		}

		return -1;
	}

	private int getTileY(int mouseY) {
		if (mouseY >= Tile.sizeY * (grid.getTiles().length - 1) && mouseY <= Tile.sizeY * (grid.getTiles().length)) {
			return (grid.getTiles().length - 1);
		}

		int min = 0, max = grid.getTiles().length - 1;
		Tile[][] tiles = grid.getTiles();
		int avg = 0;

		for (int i = 0; i < 8; i++) {
			int x = avg;
			avg = (min + max) / 2;

			if (x == avg) {
				return avg;
			}

			if (tiles[avg][0].y < mouseY) {
				min = avg;
			} else if (tiles[avg][0].y > mouseY) {
				max = avg;
			}
		}

		return -1;
	}
}
