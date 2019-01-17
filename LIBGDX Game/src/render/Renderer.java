package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import map.GameMap;
import sprites.Sprite;
import structs.Grid;
import ui.FontRender;

public class Renderer{
	
	/*
	 *This class links the map, fonts, and sprites and allows them to perform the 
	 *needed actions, it also handles the update order. 
	 * 
	 */
	SpriteRenderer spriterender;
	FontRender fontrender;
	GameMap gameMap;
	
	/**
	 * 
	 * Initializes map and renderers.
	 * 
	 */
	public void init()
	{
		fontrender = new FontRender();
		spriterender = new SpriteRenderer();
		gameMap = new GameMap();

		
		Gdx.gl.glClearColor(0, 0, 0, 0);
	}
	
	/**
	 * 
	 * Renders the list of sprites found in the list of sprites in "GameMap".
	 * 
	 */
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//Allows logic updates (shooting, movement, etc...) before rendering.
		gameMap.update();
		
		//This is where the list of sprites is looped through and rendered, contains every single image on screen.
		for (Sprite sprite : GameMap.sprites) {
			spriterender.render(sprite);
		}
		
		//Draws text above everything else.
		fontrender.render();
	}
	
	
	/**
	 * Disposes resources used during game.
	 */
	public void cleanUp()
	{
		fontrender.dispose();
		spriterender.dispose();
		gameMap.dispose();
	}
}
