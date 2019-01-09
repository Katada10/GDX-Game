package game;

import java.util.*;

import com.badlogic.gdx.math.Vector2;

import core.Input;
import core.Main;
import managers.EnemyManager;
import managers.Shooter;
import managers.TowerManager;
import render.SpriteRenderer;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;
import structs.Path;
import structs.Tile;

public class GameMap {
	public static List<Sprite> sprites;
	
	public TowerManager towerManager;
	public EnemyManager enemyManager;
	Shooter shooter;

	public GameMap() {
		sprites = new ArrayList<>();
		sprites.add(new Sprite("background.jpg"));

		
		Grid.init();
		drawGrid();
	
		new Path(this).draw();
		
		addObject(new Tower(4, 0, Sprite.MODEL), false);
		
		enemyManager = new EnemyManager();
		towerManager = new TowerManager();
		shooter = new Shooter(towerManager.list, enemyManager.list);
	}

	public static Sprite addObject(Sprite sprite, boolean makeEmpty) {
		sprites.add(sprite);

		Grid.tiles[(int) sprite.getGridY()][(int) sprite.getGridX()].isEmpty = makeEmpty;
		Grid.tiles[(int) sprite.getGridY()][(int) sprite.getGridX()].objectIndex = sprites
				.indexOf(sprite);
		
		return sprite;
	}
	
	private void drawGrid() {
		for (int j = 0; j < Grid.tiles[0].length; j++) {
			Grid.tiles[0][j].isEmpty = false;
		}
		
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

	
	public void update() {
		enemyManager.update();
		towerManager.update();
		shooter.update();
	}
}
