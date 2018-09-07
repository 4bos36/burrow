package studio.rashka.Lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import studio.rashka.BurrowGame;

public class FontTTF {

    private static final String FONT_NAME = "fonts/spirits.ttf"; // расположение шрифта
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private BitmapFont fontBlack, fontWhite, fontScore;

    private StringBuilder FONT_CHARS;
    private float RatioMonitor;
    private static final int SIZE = 64;

    public FontTTF() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        RatioMonitor = (float) Gdx.graphics.getWidth() / (float) BurrowGame.WIDTH; // коэффициент масштаба графики

        FONT_CHARS = new StringBuilder("");

        for (int i = 32; i < 127; i++) FONT_CHARS.append((char)i);
        for (int i = 1025; i < 1170; i++) FONT_CHARS.append((char)i); // русские символы

        parameter.characters = FONT_CHARS.toString(); // заполняем массив символами рус и остальные
        parameter.size = (int)(SIZE * RatioMonitor);
        parameter.color = Color.BLACK;
        fontBlack = generator.generateFont(parameter);

        parameter.color = Color.WHITE;
        fontWhite = generator.generateFont(parameter);

        parameter.size = SIZE;
        parameter.color = Color.BLACK;
        fontScore = generator.generateFont(parameter);
    }

    public BitmapFont getFontBlack() {
        return fontBlack;
    }

    public BitmapFont getFontWhite() {
        return fontWhite;
    }

    public BitmapFont getFontScore() {
        return fontScore;
    }

    public void dispose() {
      try {
          generator.dispose();
          fontBlack.dispose();
          fontWhite.dispose();
          fontScore.dispose();
          parameter = null;
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
}