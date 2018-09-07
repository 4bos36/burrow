package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import studio.rashka.BurrowGame;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;

public class LoadScreen extends State {

    private Texture logo;
    private static final int max = 120; // полная загрузка
    private int i=0; // индекс процесса загрузки
    private int angle = 0; // градус поворота индикатора загрузки
    private boolean RUN = false; // статус загрузки

    private Stage stage;

    private ImageButton btn_ru, btn_en;

    public LoadScreen(Game game) {
        super(game);

        stage = new Stage();
        logo = new Texture("logo.png");

        if (BurrowGame.getPreference().loadLanguage() == 0) {
            btn_ru = new ImageButton(BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("ru"));
            btn_ru.setSize(btn_ru.getWidth() * BurrowGame.getRatioMonitorW(), btn_ru.getHeight() * BurrowGame.getRatioMonitorH());
            btn_ru.setPosition(1088 * BurrowGame.getRatioMonitorW(), 192 * BurrowGame.getRatioMonitorH());
            btn_ru.addListener(new ClickListener(){
                public void clicked (InputEvent event, float x, float y) {
                    if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                    BurrowGame.getPreference().saveLanguage(1);
                    BurrowGame.setLoadLanguage((byte)1);
                    //Gdx.input.vibrate(50);
                    btn_ru.remove(); // удаляем флаги
                    btn_en.remove();
                    RUN = true;
                }
            });

            btn_en = new ImageButton(BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("en"));
            btn_en.setSize(btn_en.getWidth() * BurrowGame.getRatioMonitorW(), btn_en.getHeight() * BurrowGame.getRatioMonitorW());
            btn_en.setPosition(640 * BurrowGame.getRatioMonitorW(), 192 * BurrowGame.getRatioMonitorH());
            btn_en.addListener(new ClickListener(){
                public void clicked (InputEvent event, float x, float y) {
                    if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                    BurrowGame.getPreference().saveLanguage(2);
                    BurrowGame.setLoadLanguage((byte)1);
                    //Gdx.input.vibrate(50);
                    btn_ru.remove(); // удаляем флаги
                    btn_en.remove();
                    RUN = true;
                }
            });

            //stage.addActor(BurrowGame.getLanguage().getVersion_txt(16 * BurrowGame.getRatioMonitorW(), 16 * BurrowGame.getRatioMonitorH()));
            stage.addActor(btn_ru);
            stage.addActor(btn_en);
            Gdx.input.setInputProcessor(stage);
        } else {
            RUN = true;
        }
        stage.addActor(BurrowGame.getLanguage().getVersion_txt(16 * BurrowGame.getRatioMonitorW(), 16 * BurrowGame.getRatioMonitorH()));
    }

    @Override
    public void update(float deltaTime) {
        if (RUN) {
            angle += 6;
            i++;
            if (i == max) {
                RUN = false;
                game.set(new MenuScreen(game));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(logo, BurrowGame.WIDTH / 2 - logo.getWidth() / 4, BurrowGame.HEIGHT / 2 - logo.getHeight() / 4 + 256, logo.getWidth() / 2, logo.getHeight() / 2);
        if (RUN) sb.draw(BurrowGame.getTextures().textureRegions.get("loader"), 832, 96,
                BurrowGame.getTextures().textureRegions.get("loader").getRegionWidth() / 2, BurrowGame.getTextures().textureRegions.get("loader").getRegionHeight() / 2,
                BurrowGame.getTextures().textureRegions.get("loader").getRegionWidth(), BurrowGame.getTextures().textureRegions.get("loader").getRegionHeight(),
                1, 1, angle);
        sb.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        try {
            if (BurrowGame.getPreference().loadLanguage() == 0) {
                btn_ru.clear();
                btn_en.clear();
            }
            logo.dispose();
            stage.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}