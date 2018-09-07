package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import studio.rashka.BurrowGame;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;

public class SettingScreen extends State {

    private Stage stage, stageText;
    private int updatetext = 0;
    private int updatetextstatus = 0;

    private ImageButton btn_lng, btn_home, btn_check_music, btn_check_sound, btn_check_grafics,
            btn_volume_down, btn_volume_up;
    private ImageButtonStyle btn_lng_style, btn_home_style, btn_check_music_style, btn_check_sound_style, btn_check_grafics_style;

    public SettingScreen(final Game game) {
        super(game);

        stage = new Stage();
        stageText = new Stage();

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

        /********** ПЕРЕКЛЮЧАТЕЛЬ МУЗЫКИ ВКЛ/ВЫКЛ **********/
        btn_check_music_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        if (BurrowGame.getPreference().loadMusic() == 0) btn_check_music_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_off");
        if (BurrowGame.getPreference().loadMusic() == 1) btn_check_music_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_on");

        btn_check_music = new ImageButton(btn_check_music_style);
        btn_check_music.setSize(btn_check_music.getWidth() * BurrowGame.getRatioMonitorW(), btn_check_music.getHeight() * BurrowGame.getRatioMonitorH());
        btn_check_music.setPosition((1308 + 208) * BurrowGame.getRatioMonitorW(), 640 * BurrowGame.getRatioMonitorH());
        btn_check_music.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                switch (BurrowGame.getPreference().loadMusic()) {
                    case 0:
                        BurrowGame.getPreference().saveMusic(1);
                        btn_check_music_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_on");
                        BurrowGame.setLoadMusic((byte)1);
                        //Gdx.input.vibrate(50);
                        break;
                    case 1:
                        BurrowGame.getPreference().saveMusic(0);
                        btn_check_music_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_off");
                        BurrowGame.setLoadMusic((byte)1);
                        //Gdx.input.vibrate(50);
                        break;
                }
            }
        });

        /********** РЕГУЛИРОВКА ГРОМКОСТИ МУЗЫКИ **********/
        btn_volume_down = new ImageButton(BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("volume_down"));
        btn_volume_down.setSize(btn_volume_down.getWidth() * BurrowGame.getRatioMonitorW(), btn_volume_down.getHeight() * BurrowGame.getRatioMonitorH());
        btn_volume_down.setPosition(1212 * BurrowGame.getRatioMonitorW(), 542 * BurrowGame.getRatioMonitorH());
        btn_volume_down.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if (BurrowGame.getPreference().lVolume() > 1) {
                    if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                    BurrowGame.getPreference().saveVolume(BurrowGame.getPreference().lVolume() - 1);
                    BurrowGame.setLoadVolume((byte)1);
                    //Gdx.input.vibrate(50);
                }
            }
        });

        btn_volume_up = new ImageButton(BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("volume_up"));
        btn_volume_up.setSize(btn_volume_up.getWidth() * BurrowGame.getRatioMonitorW(), btn_volume_up.getHeight() * BurrowGame.getRatioMonitorH());
        btn_volume_up.setPosition((1212 + 384) * BurrowGame.getRatioMonitorW(), 542 * BurrowGame.getRatioMonitorH());
        btn_volume_up.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if (BurrowGame.getPreference().lVolume() < 5) {
                    if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                    BurrowGame.getPreference().saveVolume(BurrowGame.getPreference().lVolume() + 1);
                    BurrowGame.setLoadVolume((byte)1);
                    //Gdx.input.vibrate(50);
                }
            }
        });

        /********** ПЕРЕКЛЮЧАТЕЛЬ ЗВУКОВ ВКЛ/ВЫКЛ **********/
        btn_check_sound_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        if (BurrowGame.getPreference().loadSound() == 0) btn_check_sound_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_off");
        if (BurrowGame.getPreference().loadSound() == 1) btn_check_sound_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_on");

        btn_check_sound = new ImageButton(btn_check_sound_style);
        btn_check_sound.setSize(btn_check_sound.getWidth() * BurrowGame.getRatioMonitorW(), btn_check_sound.getHeight() * BurrowGame.getRatioMonitorH());
        btn_check_sound.setPosition((1308 + 208) * BurrowGame.getRatioMonitorW(), 448 * BurrowGame.getRatioMonitorH());
        btn_check_sound.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                switch (BurrowGame.getPreference().loadSound()) {
                    case 0:
                        BurrowGame.getPreference().saveSound(1);
                        btn_check_sound_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_on");
                        BurrowGame.getMusicSound().getClick().play();
                        //Gdx.input.vibrate(50);
                        break;
                    case 1:
                        BurrowGame.getPreference().saveSound(0);
                        btn_check_sound_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_off");
                        //Gdx.input.vibrate(50);
                        break;
                }
            }
        });

        /********** ПЕРЕКЛЮЧАТЕЛЬ ГРАФИКИ ВКЛ/ВЫКЛ **********/
        btn_check_grafics_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        if (BurrowGame.getPreference().loadGrafics() == 0) btn_check_grafics_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_off");
        if (BurrowGame.getPreference().loadGrafics() == 1) btn_check_grafics_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_on");

        btn_check_grafics = new ImageButton(btn_check_grafics_style);
        btn_check_grafics.setSize(btn_check_grafics.getWidth() * BurrowGame.getRatioMonitorW(), btn_check_grafics.getHeight() * BurrowGame.getRatioMonitorH());
        btn_check_grafics.setPosition((1308 + 208) * BurrowGame.getRatioMonitorW(), 368 * BurrowGame.getRatioMonitorH());
        btn_check_grafics.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                switch (BurrowGame.getPreference().loadGrafics()) {
                    case 0:
                        BurrowGame.getPreference().saveGrafics(1);
                        btn_check_grafics_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_on");
                        //Gdx.input.vibrate(50);
                        break;
                    case 1:
                        BurrowGame.getPreference().saveGrafics(0);
                        btn_check_grafics_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("check_off");
                        //Gdx.input.vibrate(50);
                        break;
                }
            }
        });

        /********** ПЕРЕКЛЮЧАТЕЛЬ ЯЗЫКА **********/
        btn_lng_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        if (BurrowGame.getPreference().loadLanguage() == 1) btn_lng_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("ru");
        if (BurrowGame.getPreference().loadLanguage() == 2) btn_lng_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("en");

        btn_lng = new ImageButton(btn_lng_style);
        btn_lng.setSize(btn_lng.getWidth() * BurrowGame.getRatioMonitorW() / 1.5f, btn_lng.getHeight() * BurrowGame.getRatioMonitorH() / 1.5f);
        btn_lng.setPosition((1308 + 64) * BurrowGame.getRatioMonitorW(), 208 * BurrowGame.getRatioMonitorH());
        btn_lng.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                switch (BurrowGame.getPreference().loadLanguage()) {
                    case 1:
                        BurrowGame.getPreference().saveLanguage(2);
                        btn_lng_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("en");
                        BurrowGame.setLoadLanguage((byte)1);
                        updatetextstatus = 1;
                        //Gdx.input.vibrate(50);
                        break;
                    case 2:
                        BurrowGame.getPreference().saveLanguage(1);
                        btn_lng_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("ru");
                        BurrowGame.setLoadLanguage((byte)1);
                        updatetextstatus = 1;
                        //Gdx.input.vibrate(50);
                        break;
                }
            }
        });

        stageText.addActor(BurrowGame.getLanguage().getMusic_txt((1308 - BurrowGame.getTextures().textureRegions.get("volume_up").getRegionWidth() - 16) * BurrowGame.getRatioMonitorW(), 640 * BurrowGame.getRatioMonitorH()));
        stageText.addActor(BurrowGame.getLanguage().getSound_txt((1308 - BurrowGame.getTextures().textureRegions.get("volume_up").getRegionWidth() - 16) * BurrowGame.getRatioMonitorW(), 448 * BurrowGame.getRatioMonitorH()));
        stageText.addActor(BurrowGame.getLanguage().getAnimation_txt((1308 - BurrowGame.getTextures().textureRegions.get("volume_up").getRegionWidth() - 16) * BurrowGame.getRatioMonitorW(), 360 * BurrowGame.getRatioMonitorH()));

        stage.addActor(btn_check_music);
        stage.addActor(btn_check_sound);
        stage.addActor(btn_check_grafics);
        stage.addActor(btn_home);
        stage.addActor(btn_lng);
        stage.addActor(btn_volume_up);
        stage.addActor(btn_volume_down);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float deltaTime) {
        if (BurrowGame.getPreference().loadGrafics() == 1) {
            BurrowGame.getCloud().update(deltaTime);
            BurrowGame.getCaterpillar().update(deltaTime);
            BurrowGame.getLadybug().update(deltaTime);
        }
        if (updatetextstatus == 1) { // обновляем язык интерфейса
            if (updatetext < 2) updatetext++; // делаем задержку 2мс, чтобы изменения успели вступить в силу
            if (updatetext == 2) {
                updatetext = 0;
                updatetextstatus = 0;
                stageText.clear();

                stageText.addActor(BurrowGame.getLanguage().getMusic_txt((1308 - BurrowGame.getTextures().textureRegions.get("volume_up").getRegionWidth() - 16) * BurrowGame.getRatioMonitorW(), 640 * BurrowGame.getRatioMonitorH()));
                stageText.addActor(BurrowGame.getLanguage().getSound_txt((1308 - BurrowGame.getTextures().textureRegions.get("volume_up").getRegionWidth() - 16) * BurrowGame.getRatioMonitorW(), 448 * BurrowGame.getRatioMonitorH()));
                stageText.addActor(BurrowGame.getLanguage().getAnimation_txt((1308 - BurrowGame.getTextures().textureRegions.get("volume_up").getRegionWidth() - 16) * BurrowGame.getRatioMonitorW(), 360 * BurrowGame.getRatioMonitorH()));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(BurrowGame.getTextureMap().get("fon"), 0, 0, BurrowGame.WIDTH, BurrowGame.HEIGHT);
        if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getCaterpillar().getCaterpillar(), BurrowGame.getCaterpillar().getPosition().x, BurrowGame.getCaterpillar().getPosition().y,
                BurrowGame.getCaterpillar().getWidth() / 2, BurrowGame.getCaterpillar().getHeight() / 2, BurrowGame.getCaterpillar().getWidth(), BurrowGame.getCaterpillar().getHeight(),
                1, 1, BurrowGame.getCaterpillar().getAngle());
        sb.draw(BurrowGame.getTextureMap().get("toptop_main"), 64, 0);
        if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getLadybug().getLadybug(), BurrowGame.getLadybug().getPosition().x, BurrowGame.getLadybug().getPosition().y,
                BurrowGame.getLadybug().getWidth() / 2, BurrowGame.getLadybug().getHeight() / 2, BurrowGame.getLadybug().getWidth(), BurrowGame.getLadybug().getHeight(),
                1, 1, BurrowGame.getLadybug().getAngle());
        sb.draw(BurrowGame.getCloud().getCloud(), BurrowGame.getCloud().getPosition().x, BurrowGame.getCloud().getPosition().y);
        if (BurrowGame.getPreference().loadLanguage() == 1) sb.draw(BurrowGame.getTextures().textureRegions.get("app_setting_ru"), 1184, BurrowGame.HEIGHT - 320);
        else if (BurrowGame.getPreference().loadLanguage() == 2) sb.draw(BurrowGame.getTextures().textureRegions.get("app_setting_en"), 1184, BurrowGame.HEIGHT - 320);
        if (BurrowGame.getPreference().lVolume() == 1) sb.draw(BurrowGame.getTextures().textureRegions.get("volume_00"), 1304, 542);
        else if (BurrowGame.getPreference().lVolume() == 2) sb.draw(BurrowGame.getTextures().textureRegions.get("volume_25"), 1304, 542);
        else if (BurrowGame.getPreference().lVolume() == 3) sb.draw(BurrowGame.getTextures().textureRegions.get("volume_50"), 1304, 542);
        else if (BurrowGame.getPreference().lVolume() == 4) sb.draw(BurrowGame.getTextures().textureRegions.get("volume_75"), 1304, 542);
        else if (BurrowGame.getPreference().lVolume() == 5 || BurrowGame.getPreference().lVolume() == 0) sb.draw(BurrowGame.getTextures().textureRegions.get("volume_100"), 1304, 542);
        sb.end();

        stage.draw();
        stage.act();

        stageText.draw();
        stageText.act();
    }

    @Override
    public void dispose() {
        try {
            btn_lng.clear();
            btn_home.clear();
            btn_check_music.clear();
            btn_check_sound.clear();
            btn_check_grafics.clear();
            btn_volume_down.clear();
            btn_volume_up.clear();

            stage.dispose();
            stageText.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}