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
		grid = new Grid();
	}
	
	public void addObject(Sprite sprite)
	{
		int tileX = (int)Math.ceil(sprite.position.x);
		int tileY = (int)Math.ceil(sprite.position.y);
		int yIndex = 0;
		
		Tile[][] tiles = grid.getTiles();
		
		for (int i = 1; i < tiles.length; i++) {
			//Check range
			if(tiles[i][0].y > tileY && tileY > tiles[i-1][0].y)
			{
				if((tileY - tiles[i-1][0].y) > (tiles[i][0].y - tileY))
				{
					tileY = tiles[i][0].y;
					yIndex = i;
				}
				else
				{
					tileY = tiles[i-1][0].y;
					yIndex = i-1;
				}
			}
		}
		
		for (int i = 1; i < tiles[yIndex].length; i++) {
			Tile[] arr = tiles[yIndex];
			
			if(arr[i].x > tileX && tileX > arr[i-1].x)
			{
				if((tileX - arr[i-1].x) > (arr[i].x - tileX))
				{
					tileX = arr[i].x;
				}
				else
				{
					tileX = arr[i-1].x;
				}
			}
		}
		
		sprite.position.x = tileX;
		sprite.position.y = tileY;
		sprites.add(sprite);
	}
	
	public void drawGrid()
	{
		Sprite[][] sprite = new Sprite[Grid.numOfTilesY][Grid.numOfTilesX];
		
		for (int i = 0; i < sprite.length; i++) {
			for (int j = 0; j < sprite[i].length; j++) {
				sprite[i][j] = new Sprite("square.png", "tile");
				sprite[i][j].position.x = grid.getTiles()[i][j].x;
				sprite[i][j].position.y = grid.getTiles()[i][j].y;
				sprites.add(sprite[i][j]);
			}
		}
	}
}
