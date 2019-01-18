package managers;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import core.Input;
import core.Main;
import map.GameMap;
import sprites.Enemy;
import sprites.Sprite;
import structs.Grid;
import structs.Path;
import ui.FontRender;

public class EnemyManager extends IManager<Enemy> {
	/*
	 * 
	 * This class manages enemy waves and movement to ensure they follow the path
	 * and increases wave size and speed.
	 * 
	 */
	boolean waveStarted = false, canSpawn = false;
	boolean canDeductFunds = false;

	static int waveNumber = 0;
	private long spacing = 2000;
	private static int waveSize = 3, enemyCounter = 0;

	public Label instructLabel;
	private Timer timer;

	public EnemyManager() {
		super();
		timer = new Timer();

		instructLabel = FontRender.addFont("Press e to start first wave", Grid.tileSize * ((Grid.length / 2) - 3.5f),
				750);
	}

	/**
	 * Handles enemy spawning, wave count, and difficulty. Leads spawned sprites
	 * through the path.
	 */
	public void update() {
		if (!waveStarted && Input.shouldStartWave) {
			waveStarted = true;
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					canSpawn = true;
				}

			}, 0, spacing);

		} else {
			if (enemyCounter < waveSize && Input.shouldStartWave) {
				if (canSpawn) {
					enemyCounter++;
					Enemy s = (Enemy) GameMap.addObject(new Enemy(0, 1), false);
					list.add(s);
					instructLabel.setText("Wave Number: " + (waveNumber + 1));
					canSpawn = false;
				}
			} else if (enemyCounter == waveSize && list.size() == 0) {
				waveNumber++;
				instructLabel.setText("Press e to start wave " + (waveNumber + 1));
				waveSize = (waveNumber + 1) * 2;
				spacing -= 50;
				Input.shouldStartWave = false;
				enemyCounter = 0;
			}
			for (Enemy sprite : list) {
				lead(sprite);
			}
		}
	}

	/**
	 * This method takes sprite and moves it through the path.
	 * If the sprite reaches the end of the path, money is deducted from the player.
	 * 
	 * @param sprite
	 */
	public void lead(Enemy sprite) {
		try {
			if (sprite.position.x < Path.firstSizeX * Grid.tileSize) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (sprite.position.y > (Path.sizeY + 2) * Grid.tileSize) {
				sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (sprite.position.x < (Path.firstSizeX + Path.secondSizeX + 1) * Grid.tileSize) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (sprite.position.y > 0) {
				sprite.position.y -= (sprite.speed / Gdx.graphics.getDeltaTime());
			} else if (sprite.position.x < (9 - (Path.firstSizeX + Path.secondSizeX - 1)) * Grid.tileSize) {
				sprite.position.x += (sprite.speed / Gdx.graphics.getDeltaTime());
			} else {
				if (!sprite.reachedEnd) {
					sprite.reachedEnd = true;
					canDeductFunds = true;
				}
			}
			
			if(sprite.reachedEnd && canDeductFunds)
			{
				canDeductFunds = false;
				TowerManager.currentMoney -= 10;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
