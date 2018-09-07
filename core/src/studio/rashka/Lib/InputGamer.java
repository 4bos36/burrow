package studio.rashka.Lib;

import com.badlogic.gdx.Input.TextInputListener;

import studio.rashka.BurrowGame;
import studio.rashka.Screen.RecordScreen;

public class InputGamer implements TextInputListener {

    @Override
    public void input(String text) {
        if (text.equals("")) BurrowGame.getPreference().addScore("TopTop", BurrowGame.getTopTop().getScore());
        else BurrowGame.getPreference().addScore(text, BurrowGame.getTopTop().getScore());
        BurrowGame.getPreference().setShowRecords(true);
    }

    @Override
    public void canceled() {
        BurrowGame.getPreference().addScore("TopTop", BurrowGame.getTopTop().getScore());
        BurrowGame.getPreference().setShowRecords(true);
    }
}