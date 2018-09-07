package studio.rashka.Lib;

import studio.rashka.BurrowGame;

public class Monitor {

    private float ratioMonitorW = 0, ratioMonitorH = 0;
    private static int mWIDTH = BurrowGame.WIDTH, mHEIGHT = BurrowGame.HEIGHT;

    public Monitor() {
        onWidth();
        onHeight();
        if (ratioMonitorW != ratioMonitorH) {
            BurrowGame.setHEIGHT(1200);
            mHEIGHT = BurrowGame.HEIGHT;
            ratioMonitorW = 0;
            ratioMonitorH = 0;
            onWidth();
            onHeight();
        }
    }

    private void onWidth() {
        if (BurrowGame.getPreference().width() == 0) ratioMonitorW = (float) (BurrowGame.WIDTH / 2) / (float) mWIDTH;
        else ratioMonitorW = (float) BurrowGame.getPreference().width() / (float) mWIDTH;
    }

    private void onHeight() {
        if (BurrowGame.getPreference().height() == 0) ratioMonitorH = (float) (BurrowGame.HEIGHT / 2) / (float) mHEIGHT;
        else ratioMonitorH = (float) BurrowGame.getPreference().height() / (float) mHEIGHT;
    }

    public float getRatioMonitorW() {
        return ratioMonitorW;
    }

    public float getRatioMonitorH() {
        return ratioMonitorH;
    }
}