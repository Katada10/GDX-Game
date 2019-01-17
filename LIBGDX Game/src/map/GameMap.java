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
	public static List<Sprite> sprites;
	
	public TowerManager towerManager;
	public EnemyManager enemyManager;
	BulletManager shooter;

	public GameMap() {
		sprites = new ArrayList<>();
		sprites.add(new Sprite("sand_back.jpg"));

		Grid.init();
		addObject(new Tower(4, 0, Sprite.MODEL), false);
		
		drawGrid();
		new Path(this).draw();
	
		
		enemyManager = new EnemyManager();
		towerManager = new TowerManager();
		shooter = new BulletManager(towerManager.list, enemyManager.list);
	}

	public static Sprite addObject(Sprite sprite, boolean empty) {
		Tile t = Grid.tiles[sprite.getGridY()][sprite.getGridX()];
		
		t.isEmpty = empty;
		
		sprites.add(sprite);

		return sprite;
	}
	
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

	
	public void update() {
		enemyManager.update();
		towerManager.update();
		shooter.update();
	}
}
