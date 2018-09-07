package studio.rashka.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import studio.rashka.BurrowGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = BurrowGame.TITLE;
		config.width = BurrowGame.WIDTH / 2;
		config.height = BurrowGame.HEIGHT / 2;
		new LwjglApplication(new BurrowGame(), config);
	}
}