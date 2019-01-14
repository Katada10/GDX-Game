package managers;

import java.util.*;

import com.badlogic.gdx.Gdx;

import core.Main;
import map.GameMap;
import sprites.Enemy;
import sprites.Sprite;
import structs.Grid;
import structs.Path;

public class EnemyManager extends IManager<Enemy>{
	boolean waveStarted = false, canSpawn = false;
	private static int waveSize = 10, spacing = 1, counter = 0;
	
	private Timer timer;

	public EnemyManager() {
		super();
		timer = new Timer();
	}

	public void update() {
		if (!waveStarted) {
			waveStarted = true;
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					canSpawn = true;
				}

			}, 0, spacing * 1000);

		} else {
			if (canSpawn && counter < waveSize) {
				counter++;
				Enemy s = (Enemy) GameMap.addObject(new Enemy(0, 1));
				list.add(s);
				canSpawn = false;
			}

			for (Enemy sprite : list) {
				lead(sprite);
			}
		}
	}

	public void lead(Enemy sprite) {
		try {
			if(sprite.position.x < Path.firstSizeX * Grid.tileSize)
			{
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			}
			else if (sprite.position.y > (Path.sizeY + 2) * Grid.tileSize) {
				sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
			}
			else if (sprite.position.x < (Path.firstSizeX + Path.secondSizeX + 1) * Grid.tileSize) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			}
			else if (sprite.position.y > 0) {
				sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
			}
			else if (sprite.position.x < (9 - (Path.firstSizeX + Path.secondSizeX - 1)) * Grid.tileSize) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
