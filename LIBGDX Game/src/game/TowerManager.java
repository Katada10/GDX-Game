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
	Tower currentTower = null;
	boolean towerSelected = false;

	boolean timerStarted = false;
	private Timer timer;
	boolean doFire = false;

	private List<Tower> towers;
	private List<Enemy> enemies;

	public TowerManager(List<Enemy> liveEnemies) {

		towers = new ArrayList<>();
		this.enemies = liveEnemies;
		Tower t = (Tower)GameMap.addObject(new Tower( 1, 2), true);
		towers.add(t);
	}

	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (!t.isEmpty && Input.dragging && !towerSelected) {
			currentTower = (Tower) MapManager.sprites.get(t.objectIndex);
			
			if (currentTower.tag == "modelTower") {
				towerSelected = true;
			}
		}

		if (towerSelected && Input.dragging) {
			drag(currentTower);
		}

		if (!Input.dragging) {
			towerSelected = false;
		}

		for (Tower tower : towers) {
			for (Enemy enemy : enemies) {
				if (tower.shouldShoot(enemy)) {
					if (!timerStarted) {
						timerStarted = true;
						timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {

							@Override
							public void run() {
								doFire = true;

							}
						}, 0, 1000);
					} else {
						if (doFire) {
							doFire = false;
							tower.fireBullet(enemy);
						}
					}
				} else {
					if (timer != null) {
						timer.cancel();
					}
				}
			}
		}
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
				if (tower.tag == "modelTower") {
					tower.tag = "default";
					towers.add(tower);
					MapManager.addObject(new Tower(4, 0, "modelTower"), false);
				}
			}
		}
	}

}
