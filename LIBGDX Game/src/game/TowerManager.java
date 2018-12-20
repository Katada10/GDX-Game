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
	
	boolean timerStarted = false;
	private Timer timer;
	boolean doFire = false;
	
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
					if(!timerStarted) {
						timerStarted = true;
						timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {

							@Override
							public void run() {
								doFire = true;
								
							}}, 0, 1000);
					}
					else
					{
						if(doFire)
						{
							doFire = false;
							fireBullet(tower, enemy);
						}
					}
				}
				else
				{
					if(timer != null)
					{
						timer.cancel();
					}
				}
			}
		}
	}

	private void fireBullet(Sprite tower, Sprite enemy) {
		Sprite bullet = new Sprite("bullet.png", (int)tower.getGridPosition().x, (int)tower.getGridPosition().y);
		GameMap.sprites.add(bullet);
		lead(bullet, enemy);
	}

	private void lead(Sprite bullet, Sprite enemy) {
		double bulletVelocity = 1 / Gdx.graphics.getDeltaTime();
		
		float dx = Math.abs(enemy.position.x - bullet.position.x);
		
		double time = dx / bulletVelocity;
		
		double position = (EnemyManager.speed / Gdx.graphics.getDeltaTime()) * time;
		
		System.out.println(position + " " + enemy.position.x);
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
