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

public class EnemyManager extends IManager<Enemy>{
	boolean waveStarted = false, canSpawn = false;

	static int waveNumber = 0;
	private long  spacing = 2000;//1000 ms =  1s spacing
	private static int waveSize = 3, counter = 0;
	
	
	public Label instructLabel;
	private Timer timer;

	public EnemyManager() {
		super();
		timer = new Timer();
		
		instructLabel = FontRender.addFont( "Press e to start first wave.", Grid.tileSize * ((Grid.len / 2) - 3.5f), 750);
	}

	public void update() {
		if (!waveStarted) {
			waveStarted = true;
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					canSpawn = true;
				}

			}, 0, spacing);

		} else {
			if (canSpawn && counter < waveSize && Input.shouldStartWave) {
				counter++;
				Enemy s = (Enemy) GameMap.addObject(new Enemy(0, 1));
				list.add(s);
				instructLabel.setText("Wave Size: " + counter);
				canSpawn = false;
			}
			else if(counter == waveSize && list.size() == 0)
			{
				instructLabel.setText("Press e to start next wave");
				waveNumber++;
				waveSize = (waveNumber + 1) * 2;
				spacing -= 50;
				Input.shouldStartWave = false;
				counter = 0;
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
