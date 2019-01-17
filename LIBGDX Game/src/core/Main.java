package core;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author Katada
 *
 */
public class Main {
	
	/*
	 * This is the main class which starts the game logic and sets window preferences.
	 * 
	 */
	public static final int SCALE = 800;
	
	public static void main(String[] args)
	{
		LwjglApplicationConfiguration cfg =  new LwjglApplicationConfiguration();
		cfg.title = "Chicken Tower Defense V 1.0 Release";
		cfg.width = SCALE;
		cfg.height = SCALE;
		cfg.resizable = false;
		
		new LwjglApplication(new Game(), cfg);
	}
}
