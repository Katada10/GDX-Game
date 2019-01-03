package sprites;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;

import game.EnemyManager;
import game.GameMap;
import structs.Grid;

public class Tower extends Sprite {

	boolean didDrawBullet = false, hit = false;
	Sprite bullet = null;
	int hitX = 0, hitY = 0;
	Enemy toShoot = null;
	boolean shouldShoot = false;

	

	public Tower(int gridX, int gridY, int type) {
		super("tower.png", gridX, gridY);
		this.type = type;
	}

	public void shoot() {
		if (!shouldShoot) {
			for (Enemy enemy : EnemyManager.aliveEnemies) {
				float distance = (float) Math
						.sqrt(Math.pow(enemy.position.x - position.x, 2) + Math.pow(enemy.position.y - position.y, 2));

				if (!didDrawBullet && distance < 150) {
					toShoot = enemy;
					shouldShoot = true;
					break;
				}
			}
		} else {
			if(!didDrawBullet) {
				bullet = GameMap.addObject(new Sprite("bullet.png", this.getGridX(), this.getGridY()), true);
				didDrawBullet = true;
			}

			lead(bullet, toShoot);
		}
	}

	private void lead(Sprite bullet, Enemy enemy) {
		float dx = Math.abs(enemy.position.x - bullet.position.x);
		float dy = Math.abs(enemy.position.y - bullet.position.y);
		float bulletSpeed = 10;

		if (hitX == 1 && hitY == 1) {
			hit = true;
		}

		if (!hit) {
			if (dx > 10) {
				bullet.position.x -= (dx * Gdx.graphics.getDeltaTime()) * bulletSpeed;
			} else {
				hitX = 1;
			}

			if (dy > 10) {
				bullet.position.y += (dy * Gdx.graphics.getDeltaTime()) * bulletSpeed;
			} else {
				hitY = 1;
			}
		} else {
			GameMap.sprites.remove(bullet);
			EnemyManager.kill(enemy);
			GameMap.sprites.remove(enemy);
			shouldShoot = false;
			hit = false;
			hitX = 0;
			hitY = 0;
			didDrawBullet = false;
		}
	}
}
