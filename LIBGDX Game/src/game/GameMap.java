package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Main;
import render.TwoRender;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class GameMap {
	private Grid grid;
	private List<Sprite> sprites;
	
	/**
	 * Initializes a new map with grid and background file
	 * @param render
	 * @param backgroundName
	 */
	public GameMap(TwoRender render, String backgroundName)
	{
		this.sprites = render.sprites;
		
		Sprite back = new Sprite(backgroundName, new Vector2(Main.WIDTH, Main.HEIGHT));
		render.sprites.add(back);
		
		grid = new Grid(15, 10);
	}
	
	/**
	 * Adds the specified sprite at the specified x and y coordinate on the grid.
	 * @param sprite
	 * @param x
	 * @param y
	 */
	
	public void addObject(Sprite sprite)
	{
		int x = (int) sprite.position.x;
		int y = (int) sprite.position.y;
		
		int tileX =  grid.getTiles()[ y - 1][x - 1].x;
		int tileY = grid.getTiles()[ y - 1][x - 1].y;
		
		sprite.position.x = tileX;
		sprite.position.y = tileY;
		sprites.add(sprite);
	}
	
	/**
	 * Draws the tiles of the grid onto the screen
	 * 
	 */
	public void drawGrid()
	{
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
}
