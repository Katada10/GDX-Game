package managers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import core.Input;
import map.GameMap;
import sprites.Sprite;
import sprites.Tower;
import structs.Grid;
import structs.Tile;
import ui.FontRender;

public class TowerManager extends IManager<Tower> {
	/*
	 * 
	 * This class handles tower placement and checking for sufficient funds to buy a tower.
	 */
	boolean towerSelected = false;
	Label moneyLabel;
	int currentMoney = 150, cost = 20;
	Tower toDrag = null;

	public TowerManager() {
		super();
		moneyLabel = FontRender.addFont("Money: " + currentMoney, 700, 750);
	}

	/**
	 * Checks for sufficient money to place a tower then places it and replaces the original tower,
	 * also updates the money as towers are placed.
	 * 
	 */
	public void update() {
		Tile t = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));

		if (Input.dragging && !towerSelected) {
			if (t.xCoord == GameMap.sprites.get(1).getGridX() && t.yCoord == GameMap.sprites.get(1).getGridY() && currentMoney >= cost) {
				toDrag = (Tower) GameMap.addObject(new Tower(4, 0, Sprite.TOWER), false);
				list.add(toDrag);
				towerSelected = true;

				currentMoney-=cost;
			}
		}

		if (towerSelected && Input.dragging) {
			drag(toDrag);
		} else if (towerSelected && !Input.dragging) {
			toDrag.ammoLabel = FontRender.addFont("", toDrag.position.x + 10, toDrag.position.y + toDrag.scale + 10);
			Grid.tiles[toDrag.getGridY()][toDrag.getGridX()].isEmpty = false;
		}

		if (!Input.dragging) {
			towerSelected = false;
		}

		moneyLabel.setText("Money: " + currentMoney);
	}

	
	/**
	 * Responsible for moving a tower from the original position onto the correct grid position
	 * when dragged by the mouse. Checks that the new tile it will be dragged to is empty.(No other
	 * towers or path)
	 * @param tower
	 */
	public void drag(Tower tower) {
		Tile newTile = Grid.getTile(new Vector2(Input.mouseX, Input.mouseY));
		Tile currentTile = Grid.tiles[(int) toDrag.getGridY()][(int) toDrag.getGridX()];

		if (newTile.xCoord == currentTile.xCoord && newTile.yCoord == currentTile.yCoord) {
			return;
		} else {
			if (newTile.isEmpty) {
				if (newTile.xCoord != currentTile.xCoord) {
					toDrag.setPos(newTile.xCoord, (int) toDrag.getGridY());
				}
				if (newTile.yCoord != currentTile.yCoord) {
					toDrag.setPos((int) toDrag.getGridX(), newTile.yCoord);
				}
			}
		}
	}
}
