package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import core.Input;
import structs.Grid;
import structs.Sprite;
import structs.Tile;

public class TowerManager {

	Sprite currentTower = null;
	boolean towerSelected = false;
	boolean canShoot = false, timerStarted = false;
	boolean startShooting = false;
	
	private List<Sprite> towers;
	private List<Sprite> enemies;

	public TowerManager(List<Sprite> liveEnemies)
	{
		towers = new ArrayList<>();
		this.enemies = liveEnemies;
	}
	
	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (!t.isEmpty && Input.dragging && !towerSelected) {
			currentTower = MapManager.sprites.get(t.objectIndex);
		
			if(currentTower.spriteTag == "modelTower") {
				towerSelected = true;
			}
		}

		if (towerSelected && Input.dragging) {
			drag(currentTower);
		}

		if (!Input.dragging) {
			towerSelected = false;
		}
		
		
		for (Sprite tower : towers) {
			for (Sprite enemy : enemies) {
				if(shouldShoot(tower, enemy))
				{
					//Should shoot, do something
				}
			}
		}
	}

	private boolean shouldShoot(Sprite tower, Sprite enemy) {
		int dx = Math.abs((int)(tower.position.x - enemy.position.x));
		int dy = Math.abs((int)(tower.position.y - enemy.position.y));
		
		if(dx < (Grid.tileSize * 2) && dy < (Grid.tileSize * 2))
		{
			return true;
		}
		
		return false;
	}

	public void drag(Sprite tower) {
		Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile currentTile = Grid.tiles[(int) tower.getGridPosition().y][(int) tower.getGridPosition().x];

		if (newTile.isEmpty) {
			if (newTile.xCoord != currentTile.xCoord) {
				tower.setPos(newTile.xCoord, (int) tower.getGridPosition().y);
				currentTile.isEmpty = true;
				newTile.objectIndex = MapManager.sprites.indexOf(tower);
			}
			if (newTile.yCoord != currentTile.yCoord) {
				tower.setPos((int) tower.getGridPosition().x, newTile.yCoord);
				currentTile.isEmpty = true;
				newTile.objectIndex = MapManager.sprites.indexOf(tower);
				if (tower.spriteTag == "modelTower") {
					tower.spriteTag = "default";
					towers.add(tower);
					MapManager.addObject("tower.png", 4, 0, false, "modelTower");
				}
			}
		}
	}

}
