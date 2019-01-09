package managers;

import java.util.List;

import com.badlogic.gdx.Gdx;

import game.GameMap;
import sprites.Enemy;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;

public class Shooter {
	
	private List<Tower> towers;
	private List<Enemy> enemies;
	
	private boolean shouldShoot = false, didDrawBullet = false;
	private Enemy toShoot;
	private Sprite bullet;
	
	private float tileRange = 2;
	
	
	public Shooter(List<Tower> towers, List<Enemy> enemies) {
		this.towers = towers;
		this.enemies = enemies;
	}
	
	public void update()
	{
		for (Tower tower : towers) {
			shoot(tower, enemies);
		}
	}

	private void shoot(Tower tower, List<Enemy> enemies) {
		if (!shouldShoot) {
			for (Enemy enemy : enemies) {
				float distance = (float) Math
						.sqrt(Math.pow(enemy.position.x - tower.position.x, 2) + Math.pow(enemy.position.y - tower.position.y, 2));

				if (!didDrawBullet && distance < Grid.tileSize * tileRange) {
					toShoot = enemy;
					shouldShoot = true;
					break;
				}
			}
		} else {
			if (!didDrawBullet) {
				bullet = GameMap.addObject(new Sprite("bullet.png", tower.getGridX(), tower.getGridY()), true);
				didDrawBullet = true;
			}

			lead(bullet, toShoot);
		}
	}

	private void lead(Sprite bullet, Enemy enemy) {
		float dx = enemy.position.x - bullet.position.x;
		float dy = enemy.position.y - bullet.position.y;
		
		float bulletSpeed = 10;

		bullet.position.x += (dx * Gdx.graphics.getDeltaTime()) * bulletSpeed;
		bullet.position.y += (dy * Gdx.graphics.getDeltaTime()) * bulletSpeed;

		if (Math.abs(dx) < 10 && Math.abs(dy) < 10) {
			GameMap.sprites.remove(bullet);

			GameMap.sprites.remove(enemy);

			shouldShoot = false;
			didDrawBullet = false;
		}
	}
}
