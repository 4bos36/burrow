package studio.rashka.Lib;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import studio.rashka.BurrowGame;

public class Language {

    private LabelStyle labelStyle, labelStyleWhite;
    private TextureAtlas buttonsAtlas;
    private Skin buttonSkin;

    private Label Version_txt, Music_txt, Sound_txt, Best_name_no_txt, Animation_txt, record;

    private int Lng;

    public Language() {
        this.Lng = BurrowGame.getPreference().loadLanguage();

        labelStyle = new LabelStyle();
        labelStyle.font = BurrowGame.getFontTTF().getFontBlack(); // заносим в память для использования

        labelStyleWhite = new LabelStyle();
        labelStyleWhite.font = BurrowGame.getFontTTF().getFontWhite();

        Version_txt = new Label("v.1.2.4", labelStyleWhite);

        if (Lng == 2) { // англ.интерфейс
            loadTexturesButton(2);
            Music_txt = new Label("Music", labelStyle);
            Sound_txt = new Label("Sound", labelStyle);
            Best_name_no_txt = new Label("No records", labelStyle);
            Animation_txt = new Label("Graphics +", labelStyle);
        }

        if (Lng == 1) { // рус.интерфейс
            loadTexturesButton(1);
            Music_txt = new Label("Музыка", labelStyle);
            Sound_txt = new Label("Звуки", labelStyle);
            Best_name_no_txt = new Label("Нету рекордов", labelStyle);
            Animation_txt = new Label("Графика +", labelStyle);
        }
    }

    public Label getRecord(float x, float y, int nomer) {
        record = new Label((nomer + 1) + ". " + BurrowGame.getPreference().getCacheName(nomer) + " ..... " + BurrowGame.getPreference().getCacheRecords(nomer), labelStyleWhite);
        record.setPosition(x, y);
        return record;
    }

    public Label getRecord(float x, float y) {
        record = new Label(BurrowGame.getPreference().getCacheName(0) + " - " + BurrowGame.getPreference().getCacheRecords(0), labelStyle);
        record.setPosition(x, y);
        return record;
    }

    private void loadTexturesButton(int lng) { // в зависимости от языка загружаем текстуру
        if (lng == 1) buttonsAtlas = new TextureAtlas("buttons.ru"); // настройки пакета изображений
        if (lng == 2) buttonsAtlas = new TextureAtlas("buttons.en");

        buttonSkin = new Skin(buttonsAtlas);
    }

    public Skin getButtonSkin() {
        return buttonSkin;
    }

    public Label getVersion_txt(float x, float y) {
        Version_txt.setPosition(x, y);
        return Version_txt;
    }

    public Label getMusic_txt(float x, float y) {
        Music_txt.setPosition(x, y);
        return Music_txt;
    }

    public Label getSound_txt(float x, float y) {
        Sound_txt.setPosition(x, y);
        return Sound_txt;
    }

    public Label getBest_name_no_txt(float x, float y) {
        Best_name_no_txt.setPosition(x, y);
        return Best_name_no_txt;
    }

    public Label getAnimation_txt(float x, float y) {
        Animation_txt.setPosition(x, y);
        return Animation_txt;
    }

    public void dispose() {
        try {
            buttonsAtlas.dispose();
            buttonSkin.dispose();

            Version_txt.remove();
            Music_txt.remove();
            Sound_txt.remove();
            Best_name_no_txt.remove();
            Animation_txt.remove();
            record.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}