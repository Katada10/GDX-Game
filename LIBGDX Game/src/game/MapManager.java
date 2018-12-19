package game;

import structs.Sprite;

public class MapManager extends GameMap{
	private TowerManager towerManager;
	private EnemyManager enemyManager;
	
	public MapManager(String backgroundName) {
		super(backgroundName);
		enemyManager = new EnemyManager();
		towerManager = new TowerManager(enemyManager.aliveEnemies);
	}
	
	public void update() {
		enemyManager.update();
		towerManager.update();
	}

	public void addObjects() {
		addObject("tower.png", 4, 0, false, "modelTower");
	}

}
