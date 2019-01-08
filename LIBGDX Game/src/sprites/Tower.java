package sprites;

import com.badlogic.gdx.Gdx;

import game.EnemyManager;
import game.GameMap;
import structs.Grid;

public class Tower extends Sprite {

	boolean didDrawBullet = false, shouldShoot = false;
	Sprite bullet = null;
	Enemy toShoot = null;

	private float tileRange = 2, bulletSpeed = 10;

	public Tower(int gridX, int gridY, int type) {
		super("tower.png", gridX, gridY);
		this.type = type;
	}

	public void shoot() {
		if (!shouldShoot) {
			for (Enemy enemy : EnemyManager.aliveEnemies) {
				float distance = (float) Math
						.sqrt(Math.pow(enemy.position.x - position.x, 2) + Math.pow(enemy.position.y - position.y, 2));

				if (!didDrawBullet && distance < Grid.tileSize * tileRange) {
					toShoot = enemy;
					shouldShoot = true;
					break;
				}
			}
		} else {
			if (!didDrawBullet) {
				bullet = GameMap.addObject(new Sprite("bullet.png", this.getGridX(), this.getGridY()), true);
				didDrawBullet = true;
			}

			lead(bullet, toShoot);
		}
	}

	private void lead(Sprite bullet, Enemy enemy) {
		float dx = enemy.position.x - bullet.position.x;
		float dy = enemy.position.y - bullet.position.y;

		bullet.position.x += (dx * Gdx.graphics.getDeltaTime()) * bulletSpeed;
		bullet.position.y += (dy * Gdx.graphics.getDeltaTime()) * bulletSpeed;

		if (Math.abs(dx) < 10 && Math.abs(dy) < 10) {
			GameMap.sprites.remove(bullet);

			EnemyManager.kill(enemy);
			GameMap.sprites.remove(enemy);

			shouldShoot = false;
			didDrawBullet = false;
		}

	}
}
