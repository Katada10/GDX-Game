package map;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import managers.EnemyManager;
import managers.BulletManager;
import managers.TowerManager;
import render.SpriteRenderer;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;
import structs.Path;
import structs.Tile;

public class GameMap {
	/*
	 * This class manager the game map, it draws the background and displays the tiles
	 * of the grid, as well as the original tower from which other towers are dragged. It
	 * also allows other classes to do things on the map, such as shooting and enemy movement.
	 */
	public static List<Sprite> sprites;
	
	public TowerManager towerManager;
	public EnemyManager enemyManager;
	BulletManager shooter;

	/**
	 * Creates the background, grid and path, as well as the original tower.
	 */
	public GameMap() {
		sprites = new ArrayList<>();
		sprites.add(new Sprite("sand_back.jpg"));

		Grid.init();
		addObject(new Tower(4, 0, Sprite.MODEL), false);
		
		drawGrid();
		new Path().draw();
	
		
		enemyManager = new EnemyManager();
		towerManager = new TowerManager();
		shooter = new BulletManager(towerManager.list, enemyManager.list);
	}

	
	/**
	 * Adds sprite to the screen and sets it's current tile to empty or not, which is used by other classes.
	 * @param sprite
	 * @param empty
	 * @return
	 */
	public static Sprite addObject(Sprite sprite, boolean empty) {
		Tile t = Grid.tiles[sprite.getGridY()][sprite.getGridX()];
		
		t.isEmpty = empty;
		
		sprites.add(sprite);

		return sprite;
	}
	
	/**
	 * Draws a series of images at the locations of the tiles to create a visual grid.
	 */
	private void drawGrid() {
		
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

	
	/**
	 * Calls the game logic managers' update methods.
	 */
	public void update() {
		enemyManager.update();
		towerManager.update();
		shooter.update();
	}
}
