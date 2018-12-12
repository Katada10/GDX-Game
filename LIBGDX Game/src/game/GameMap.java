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
		int y = (int) sprite.gridPosition.y;

		int tileX = grid.getTiles()[y - 1][x - 1].x;
		int tileY = grid.getTiles()[y - 1][x - 1].y;

		sprite.position.x = tileX;
		sprite.position.y = tileY;

		grid.getTiles()[y - 1][x - 1].empty = false;
		sprites.add(sprite);

		grid.getTiles()[y - 1][x - 1].objectIndex = sprites.indexOf(sprite);
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
		int tileY = getTileY(Input.mouseY);
		
		if (Input.dragging && !selected) {
			Tile t = grid.getTiles()[tileY][tileX];

			if (!t.empty && !selected) {
				sprite = sprites.get(t.objectIndex);
				selected = true;
				pPosx = tileX;
				pPosy = tileY;
			}
		}
		
		if (Input.dragging && selected) {
			drag(sprite, pPosx, pPosy);
		} 
		
		if(!Input.dragging && selected)	
			selected = false;
		
		return;
	}

	private void drag(Sprite sprite, int tileX, int tileY) {
		int x = getTileX(Input.mouseX);
		int y = getTileY(Input.mouseY);
		
		if(x != tileX)
		{
			sprite.position.x = grid.getTiles()[y][x].x;
		}
		if(y != tileY)
		{
			sprite.position.y = grid.getTiles()[y][x].y;
		}
		
	}

	private int getTileY(int mouseY) {
		for (int i = 1; i < grid.getTiles().length; i++) {
			if (mouseY > grid.getTiles()[i - 1][0].y && mouseY < grid.getTiles()[i][0].y) {
				return Grid.yLen - i;
			}
		}
		return 0;
	}

	private int getTileX(int mouseX) {
		for (int i = 1; i < grid.getTiles().length; i++) {
			for (int j = 1; j < grid.getTiles()[i].length; j++) {
				if (mouseX > grid.getTiles()[i][j - 1].x && mouseX < grid.getTiles()[i][j].x) {
					return j - 1;
				}
			}
		}
		return Grid.xLen - 1;
	}
}
