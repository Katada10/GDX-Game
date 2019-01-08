package game;

import core.Main;
import sprites.Sprite;
import sprites.Tower;

public class MapManager extends GameMap{
	private TowerManager towerManager;
	private EnemyManager enemyManager;
	
	public MapManager() {
		super("background.jpg");
		enemyManager = new EnemyManager();
		towerManager = new TowerManager();
	}
	
	public void update() {
		enemyManager.update();
		try {
			towerManager.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addObjects() {
		Sprite s = addObject(new Tower(4, 0, Sprite.MODEL), false);
	}

}
