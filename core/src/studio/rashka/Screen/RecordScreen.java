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

public class RecordScreen extends State {

    private ImageButton btn_home;
    private ImageButtonStyle btn_home_style;

    private Stage stage;

    public RecordScreen(final Game game) {
        super(game);

        stage = new Stage();
        BurrowGame.getPreference().setShowRecords(false);

        /********** КНОПКА ДОМОЙ **********/
        btn_home_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        btn_home_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("home");
        btn_home_style.down = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("home_click");

        btn_home = new ImageButton(btn_home_style);
        btn_home.setSize(btn_home.getWidth() * BurrowGame.getRatioMonitorW(), btn_home.getHeight() * BurrowGame.getRatioMonitorH());
        btn_home.setPosition(64 * BurrowGame.getRatioMonitorW(), (BurrowGame.HEIGHT - 128) * BurrowGame.getRatioMonitorH());
        btn_home.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                game.set(new MenuScreen(game));
                //Gdx.input.vibrate(50);
            }
        });


        for (int i = 0; i < 10; i++) { // формируем список рекордсменов
            int x = 80 * i;
            stage.addActor(BurrowGame.getLanguage().getRecord(1280 * BurrowGame.getRatioMonitorW(), (832 - x) * BurrowGame.getRatioMonitorH(), i));
        }
        stage.addActor(btn_home);
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
        sb.begin();
        sb.draw(BurrowGame.getTextureMap().get("fon"), 0, 0, BurrowGame.WIDTH, BurrowGame.HEIGHT);
        sb.draw(BurrowGame.getTextureMap().get("desk_records"), 1088, 0);
        sb.draw(BurrowGame.getCloud().getCloud(), BurrowGame.getCloud().getPosition().x, BurrowGame.getCloud().getPosition().y);
        sb.end();

        stage.draw();
        stage.act();

        sb.begin();
        if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getCaterpillar().getCaterpillar(), BurrowGame.getCaterpillar().getPosition().x, BurrowGame.getCaterpillar().getPosition().y,
                BurrowGame.getCaterpillar().getWidth() / 2, BurrowGame.getCaterpillar().getHeight() / 2, BurrowGame.getCaterpillar().getWidth(), BurrowGame.getCaterpillar().getHeight(),
                1, 1, BurrowGame.getCaterpillar().getAngle());
        sb.draw(BurrowGame.getTextureMap().get("toptop_main"), 64, 0);
        if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getLadybug().getLadybug(), BurrowGame.getLadybug().getPosition().x, BurrowGame.getLadybug().getPosition().y,
                BurrowGame.getLadybug().getWidth() / 2, BurrowGame.getLadybug().getHeight() / 2, BurrowGame.getLadybug().getWidth(), BurrowGame.getLadybug().getHeight(),
                1, 1, BurrowGame.getLadybug().getAngle());
        sb.end();
    }

    @Override
    public void dispose() {
        try {
            stage.dispose();
            btn_home.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
