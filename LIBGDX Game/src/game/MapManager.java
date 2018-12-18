package game;

import structs.Sprite;

public class MapManager extends GameMap{
	private TowerManager towerManager;
	private EnemyManager enemyManager;
	
	public MapManager(String backgroundName) {
		super(backgroundName);
		towerManager = new TowerManager();
		enemyManager = new EnemyManager();
	}
	
	public void update() {
		enemyManager.update();
		towerManager.update();
	}

	public void addObjects() {
		addObject("tower.png", 4, 0, false, "modelTower");
	}

}
