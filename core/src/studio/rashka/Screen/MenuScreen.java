package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

import studio.rashka.BurrowGame;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;

public class MenuScreen extends State {

    private static boolean RUN = true;
    private Stage stage;

    private ImageButton btn_game, btn_record, btn_setting, btn_exit;
    private ImageButtonStyle btn_game_style, btn_record_style, btn_setting_style, btn_exit_style;

    public MenuScreen(final Game game) {
        super(game);

        stage = new Stage();

        /********** КНОПКА НАЧАЛО ИГРЫ **********/

        btn_game_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        btn_game_style.up = BurrowGame.getLanguage().getButtonSkin().getDrawable("StartOff");
        btn_game_style.down = BurrowGame.getLanguage().getButtonSkin().getDrawable("StartOn");

        btn_game = new ImageButton(btn_game_style);
        btn_game.setSize(576 * BurrowGame.getRatioMonitorW(), 128 * BurrowGame.getRatioMonitorH());
        btn_game.setPosition(1152 * BurrowGame.getRatioMonitorW(), (224 + 128 * 3) * BurrowGame.getRatioMonitorH());
        btn_game.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                game.set(new GameScreen(game));
                //Gdx.input.vibrate(50);
            }
        });

        /********** КНОПКА РЕКОРДОВ **********/

        btn_record_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        btn_record_style.up = BurrowGame.getLanguage().getButtonSkin().getDrawable("RecordsOff");
        btn_record_style.down = BurrowGame.getLanguage().getButtonSkin().getDrawable("RecordsOn");

        btn_record = new ImageButton(btn_record_style);
        btn_record.setSize(576 * BurrowGame.getRatioMonitorW(), 128 * BurrowGame.getRatioMonitorH());
        btn_record.setPosition(1152 * BurrowGame.getRatioMonitorW(), (224 + 128 * 2) * BurrowGame.getRatioMonitorH());
        btn_record.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                game.set(new RecordScreen(game));
                //Gdx.input.vibrate(50);
            }
        });

        /********** КНОПКА НАСТРОЕК ИГРЫ **********/

        btn_setting_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        btn_setting_style.up = BurrowGame.getLanguage().getButtonSkin().getDrawable("SettingOff");
        btn_setting_style.down = BurrowGame.getLanguage().getButtonSkin().getDrawable("SettingOn");

        btn_setting = new ImageButton(btn_setting_style);
        btn_setting.setSize(576 * BurrowGame.getRatioMonitorW(), 128 * BurrowGame.getRatioMonitorH());
        btn_setting.setPosition(1152 * BurrowGame.getRatioMonitorW(), (224 + 128) * BurrowGame.getRatioMonitorH());
        btn_setting.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                game.set(new SettingScreen(game));
                //Gdx.input.vibrate(50);
            }
        });

        /********** КНОПКА ВЫХОДА **********/

        btn_exit_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        btn_exit_style.up = BurrowGame.getLanguage().getButtonSkin().getDrawable("ExitOff");
        btn_exit_style.down = BurrowGame.getLanguage().getButtonSkin().getDrawable("ExitOn");

        btn_exit = new ImageButton(btn_exit_style);
        btn_exit.setSize(576 * BurrowGame.getRatioMonitorW(), 128 * BurrowGame.getRatioMonitorH());
        btn_exit.setPosition(1152 * BurrowGame.getRatioMonitorW(), 224 * BurrowGame.getRatioMonitorH());
        btn_exit.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                RUN = false;
                //Gdx.input.vibrate(50);
                //dispose();
                Gdx.app.exit();
                //System.exit(0);
            }
        });

        String cacheRecords = BurrowGame.getPreference().getCacheRecords(0);
        if (cacheRecords.equals("0")) stage.addActor(BurrowGame.getLanguage().getBest_name_no_txt(1376 * BurrowGame.getRatioMonitorW(), 128 * BurrowGame.getRatioMonitorH()));
        else stage.addActor(BurrowGame.getLanguage().getRecord(1376 * BurrowGame.getRatioMonitorW(), 128 * BurrowGame.getRatioMonitorH()));

        stage.addActor(btn_game);
        stage.addActor(btn_record);
        stage.addActor(btn_setting);
        stage.addActor(btn_exit);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float deltaTime) {
        if (BurrowGame.getPreference().loadGrafics() == 1) {
            BurrowGame.getCloud().update(deltaTime);
            BurrowGame.getCaterpillar().update(deltaTime);
            BurrowGame.getLadybug().update(deltaTime);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (RUN) {
            sb.begin();
            sb.draw(BurrowGame.getTextureMap().get("fon"), 0, 0, BurrowGame.WIDTH, BurrowGame.HEIGHT);
            sb.draw(BurrowGame.getCloud().getCloud(), BurrowGame.getCloud().getPosition().x, BurrowGame.getCloud().getPosition().y);
            if (BurrowGame.getPreference().loadLanguage() == 1) sb.draw(BurrowGame.getTextures().textureRegions.get("app_name_ru"), 1184, BurrowGame.HEIGHT - 320);
            else if (BurrowGame.getPreference().loadLanguage() == 2) sb.draw(BurrowGame.getTextures().textureRegions.get("app_name_en"), 1184, BurrowGame.HEIGHT - 320);
            if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getCaterpillar().getCaterpillar(), BurrowGame.getCaterpillar().getPosition().x, BurrowGame.getCaterpillar().getPosition().y,
                    BurrowGame.getCaterpillar().getWidth() / 2, BurrowGame.getCaterpillar().getHeight() / 2, BurrowGame.getCaterpillar().getWidth(), BurrowGame.getCaterpillar().getHeight(),
                    1, 1, BurrowGame.getCaterpillar().getAngle());
            sb.draw(BurrowGame.getTextureMap().get("toptop_main"), 64, 0);
            if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getLadybug().getLadybug(), BurrowGame.getLadybug().getPosition().x, BurrowGame.getLadybug().getPosition().y,
                    BurrowGame.getLadybug().getWidth() / 2, BurrowGame.getLadybug().getHeight() / 2, BurrowGame.getLadybug().getWidth(), BurrowGame.getLadybug().getHeight(),
                    1, 1, BurrowGame.getLadybug().getAngle());
            sb.draw(BurrowGame.getTextures().textureRegions.get("medal"), 1216, 128);
            sb.end();

            stage.act();
            stage.draw();
        }
    }

    @Override
    public void dispose() {
        try {
            btn_game.remove();
            btn_record.remove();
            btn_setting.remove();
            btn_exit.remove();

            stage.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}