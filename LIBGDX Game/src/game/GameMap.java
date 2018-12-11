package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import render.TwoRender;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class GameMap {
	private Grid grid;
	private List<Sprite> sprites;
	
	public GameMap(TwoRender render)
	{
		this.sprites = render.sprites;
		grid = new Grid(15, 10);
	}
	
	public void addObject(Sprite sprite, int x, int y)
	{
		int tileX =  grid.getTiles()[ y - 1][x - 1].x;
		int tileY = grid.getTiles()[ y - 1][x - 1].y;
		
		sprite.position.x = tileX;
		sprite.position.y = tileY;
		sprites.add(sprite);
	}
	
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
