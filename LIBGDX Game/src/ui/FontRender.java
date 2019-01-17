package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import structs.Grid;

public class FontRender {

	private static Stage stage;
	/*
	 * 
	 * This class is responsible for storing and drawing the text 
	 * that appears on the screen.
	 */
	
	public FontRender()
	{
		stage = new Stage();
	}
	
	/**
	 * Draws the stage containing all text.
	 */
	public void render()
	{
		stage.draw();
		stage.act();
	}
	
	/**
	 * Creates a label at the given x and y coordinates with the given text, and then adds it to the stage.
	 * @param text
	 * @param x
	 * @param y
	 * @return
	 */
	public static Label addFont(String text, float x, float y)
	{
		BitmapFont font = new BitmapFont();
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		Label label = new Label(text, style);
		label.setFontScale(1);
		label.setPosition(x, y);
		stage.addActor(label);
		return label;
	}
	
	/**
	 * Disposes of resources.
	 */
	public void dispose()
	{
		stage.dispose();
	}
}
