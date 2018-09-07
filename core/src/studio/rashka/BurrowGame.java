package studio.rashka;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Map;
import java.util.TreeMap;

import studio.rashka.Lib.FontTTF;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.Language;
import studio.rashka.Lib.Monitor;
import studio.rashka.Lib.MusicSound;
import studio.rashka.Lib.Preference;
import studio.rashka.Models.Caterpillar;
import studio.rashka.Models.Cloud;
import studio.rashka.Models.Ladybug;
import studio.rashka.Models.TopTop;
import studio.rashka.Screen.LoadScreen;
import studio.rashka.Lib.Textures;

public class BurrowGame extends ApplicationAdapter {

    public static final int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static String TITLE = "Burrow";

	private SpriteBatch batch;
	private static FPSLogger fps;
	private static Game game;

	private OrthographicCamera camera;
	private static Preference preference; // lib
	private static MusicSound musicSound;
	private static Language language;
	private static Textures textures;
	private static FontTTF fontTTF;
	private static Monitor monitor;

	private static Caterpillar caterpillar; // models
	private static Ladybug ladybug;
	private static TopTop topTop;
	private static Cloud cloud;

	private static float ratioMonitorW, ratioMonitorH;

	private static Map<String, Texture> textureMap;
	private static byte LoadLanguage = 1, LoadMusic = 1, LoadVolume = 1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fps = new FPSLogger();
		game = new Game();

		musicSound = new MusicSound(); // load lib
		preference = new Preference();
		textures = new Textures();
		fontTTF = new FontTTF();
		language = new Language();
		monitor = new Monitor();

		musicSound.MusicSound(); // load music file

		cloud = new Cloud(); // загрузка облака с передачей параметров
		cloud.Cloud(BurrowGame.WIDTH, BurrowGame.HEIGHT);

		caterpillar = new Caterpillar(); // загрузка гусеницы
		caterpillar.Caterpillar();
		ladybug = new Ladybug(); // загрузка коровки
		ladybug.Ladybug();
		topTop = new TopTop(); // загрузка ТопТопов для игры

		textureMap = new TreeMap<String, Texture>();
		textureMap.put("fon", new Texture("fon.jpg"));
		textureMap.put("toptop_main", new Texture("toptop_main.png"));
		textureMap.put("desk_records", new Texture("desk_records.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		ratioMonitorW = monitor.getRatioMonitorW();
		ratioMonitorH = monitor.getRatioMonitorH();

		Gdx.gl.glClearColor(0, 0, 0, 1); // делаем экран чёрным
		game.push(new LoadScreen(game));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined); // режим камеры

		game.update(Gdx.graphics.getDeltaTime()); // обновляет активный экран
		game.render(batch); // отрисовываем экран

		if (LoadLanguage == 1) { // устанавливаем язык интерфейса
			language = new Language();
			LoadLanguage = 0;
		}

		if (LoadMusic == 1) { // старт/стоп музыки
			if (preference.loadMusic() == 1) musicSound.StartFon();
			if (preference.loadMusic() == 0) musicSound.getMusic().pause();
			LoadMusic = 0;
		}

		if (LoadVolume == 1) { // установка уровня громкости музыки
            musicSound.getMusic().setVolume(getPreference().loadVolume());
            LoadVolume = 0;
        }

		fps.log(); // показывает fps игры
	}

	public static Game getGame() {
		return game;
	}

	public static Preference getPreference() {
		return preference;
	}

	public static Language getLanguage() {
		return language;
	}

	public static Textures getTextures() {
		return textures;
	}

	public static FontTTF getFontTTF() {
		return fontTTF;
	}

	public static MusicSound getMusicSound() {
		return musicSound;
	}

	public static void setHEIGHT(int HEIGHT) {
		BurrowGame.HEIGHT = HEIGHT;
	}

	// коэффициенты для масштабирования разных экранов для масштабирования графики на экране
	public static float getRatioMonitorW() {
		return ratioMonitorW;
	}

	public static float getRatioMonitorH() {
		return ratioMonitorH;
	}

	public static Cloud getCloud() {
		return cloud;
	}

	public static Caterpillar getCaterpillar() {
		return caterpillar;
	}

	public static Ladybug getLadybug() {
		return ladybug;
	}

	public static TopTop getTopTop() {
		return topTop;
	}

	public static Map<String, Texture> getTextureMap() {
		return textureMap;
	}

	/****************** ИЗМЕНЕНИЯ ПАРАМЕТРОВ ИГРЫ **********************/

	public static void setLoadLanguage(byte loadLanguage) {
		LoadLanguage = loadLanguage;
	}

	public static void setLoadMusic(byte loadMusic) {
		LoadMusic = loadMusic;
	}

    public static void setLoadVolume(byte loadVolume) {
        LoadVolume = loadVolume;
    }

	@Override
	public void resume() {
        if (preference.loadMusic() == 1) musicSound.StartFon();
	}

	@Override
	public void pause() {
		if(musicSound.getMusic().isPlaying()) musicSound.getMusic().pause();
		BurrowGame.getTopTop().setSTART_GAME((byte)0);
	}

	@Override
	public void dispose () {
		batch.dispose();

		musicSound.dispose();
		textures.dispose();
		language.dispose();
		fontTTF.dispose();

		caterpillar.dispose();
		ladybug.dispose();
		cloud.dispose();

		textureMap.clear();
		System.exit(0);
	}
}