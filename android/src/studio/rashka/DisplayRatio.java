package studio.rashka;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.badlogic.gdx.Preferences;

import java.lang.reflect.Method;

public class DisplayRatio {

    public DisplayRatio(WindowManager windowManager, Preferences setting) {
        Display display = windowManager.getDefaultDisplay();
        int realWidth, realHeight;

        if (Build.VERSION.SDK_INT >= 17){ //new pleasant way to get real metrics
            DisplayMetrics realMetrics = new DisplayMetrics();
            display.getRealMetrics(realMetrics);
            realWidth = realMetrics.widthPixels;
            realHeight = realMetrics.heightPixels;

        } else if (Build.VERSION.SDK_INT >= 14) { //reflection for this weird in-between time
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (Exception e) { //this may not be 100% accurate, but it's all we've got
                realWidth = display.getWidth();
                realHeight = display.getHeight();
            }

        } else { //This should be close, as lower API devices should not have window navigation bars
            realWidth = display.getWidth();
            realHeight = display.getHeight();
        }
        setting.putInteger("Width", realWidth);
        setting.putInteger("Height", realHeight);
        setting.flush();
    }
}