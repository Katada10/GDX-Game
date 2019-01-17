package managers;

import java.util.List;

import com.badlogic.gdx.Gdx;

import map.GameMap;
import sprites.Enemy;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;

public class BulletManager{
	/*
	 * This class handles the shooting of bullets at enemies, detecting range and 
	 * sufficient ammo as well as removing killed enemies.
	 */
	private List<Tower> towers;
	private List<Enemy> enemies;
	
	private Enemy toShoot;
	
	private float tileRange = 1;
	
	public BulletManager(List<Tower> towers, List<Enemy> enemies) {
		this.towers = towers;
		this.enemies = enemies;
	}
	
	/**
	 * Counts ammo and calls the shoot method for each tower.
	 */
	public void update()
	{
		for (Tower tower : towers) {
			if(tower.ammoLabel != null)
				tower.ammoLabel.setText("Ammo: " + (tower.maxAmmo - tower.bullets.size()));
			shoot(tower, enemies);
		}
	}

	/**
	 * For the given tower, loops through the list of enemies and checks their range,
	 * then adds a bullet if ammo is available and calls the lead method.
	 * @param tower
	 * @param enemies
	 */
	private void shoot(Tower tower, List<Enemy> enemies) {
		if (!tower.shouldShoot) {
			for (Enemy enemy : enemies) {
				float distance = (float) Math
						.sqrt(Math.pow(enemy.position.x - tower.position.x, 2) + Math.pow(enemy.position.y - tower.position.y, 2));

				if (!tower.didDrawBullet && Math.abs(distance) <= (Grid.tileSize * tileRange) + 10) {
					toShoot = enemy;
					tower.shouldShoot = true;
					break;
				}
			}
		} else {
			if (!tower.didDrawBullet && tower.bullets.size() < tower.maxAmmo) {
				tower.createBullet(tower.getGridX(), tower.getGridY());
				GameMap.addObject(tower.currentBullet, true);
				tower.didDrawBullet = true;
			}
			if(GameMap.sprites.contains(tower.currentBullet))
					lead(tower, tower.currentBullet, toShoot);
		}
	}

	
	/**
	 * 
	 * Leads bullet from tower to enemy, detects collision with target then removes itself and target.
	 * @param tower
	 * @param bullet
	 * @param enemy
	 */
	private void lead(Tower tower, Sprite bullet, Enemy enemy) {
		float dx = enemy.position.x - bullet.position.x;
		float dy = enemy.position.y - bullet.position.y;
		
		float bulletSpeed = 10;

		bullet.position.x += (dx * Gdx.graphics.getDeltaTime()) * bulletSpeed;
		bullet.position.y += (dy * Gdx.graphics.getDeltaTime()) * bulletSpeed;

		if (Math.abs(dx) < 20 && Math.abs(dy) < 20) {
			GameMap.sprites.remove(enemy);
			enemies.remove(enemy);
			GameMap.sprites.remove(bullet);


			tower.shouldShoot = false;
			tower.didDrawBullet = false;
		}
	}
}
