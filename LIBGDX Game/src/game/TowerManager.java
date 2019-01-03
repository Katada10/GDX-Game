package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import core.Input;
import sprites.Enemy;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;
import structs.Tile;

public class TowerManager {
	Sprite currentTower;
	boolean towerSelected = false;

	private List<Tower> towers;
	private List<Enemy> enemies;
	
	Tower tower;

	public TowerManager(List<Enemy> liveEnemies) {
		towers = new ArrayList<>();
		this.enemies = liveEnemies;
		tower = (Tower) GameMap.addObject(new Tower(1, 2, Sprite.TOWER), true);
		towers.add(tower);
	}

	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (!t.isEmpty && Input.dragging && !towerSelected) {
			currentTower = MapManager.sprites.get(t.objectIndex);
			if (currentTower.type == Sprite.MODEL) {
				towerSelected = true;
			}
		}

		if (towerSelected && Input.dragging) {
			drag((Tower)currentTower);
		}

		if (!Input.dragging) {
			towerSelected = false;
		}

		tower.shoot(enemies);
	}

	public void drag(Tower tower) {
		Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile currentTile = Grid.tiles[(int) tower.getGridY()][(int) tower.getGridX()];

		if (newTile.isEmpty) {
			if (newTile.xCoord != currentTile.xCoord) {
				tower.setPos(newTile.xCoord, (int) tower.getGridY());
				currentTile.isEmpty = true;
				newTile.objectIndex = MapManager.sprites.indexOf(tower);
			}
			if (newTile.yCoord != currentTile.yCoord) {
				tower.setPos((int) tower.getGridX(), newTile.yCoord);
				currentTile.isEmpty = true;
				newTile.objectIndex = MapManager.sprites.indexOf(tower);
				if (tower.type == Sprite.MODEL) {
					tower.type = Sprite.TOWER;
					towers.add(tower);
					MapManager.addObject(new Tower(4, 0, Sprite.MODEL), false);
				}
			}
		}
	}

}
