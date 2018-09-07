package studio.rashka.Models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import studio.rashka.BurrowGame;

/**
 * Коровка
 */

public class Ladybug {

    private static final byte FLY = 1;
    private static final byte FLY_BACK = -1;
    private byte status = 0; // статус направления полёта
    private int angle = 0; // градус поворота
    private byte type; // тип передвижения

    private int Width, Height;

    private TextureRegion ladybug;
    private Vector3 position;
    private Random random;

    public void Ladybug() {
        ladybug = BurrowGame.getTextures().textureRegions.get("ladybug");

        random = new Random();

        Width = ladybug.getRegionWidth() + 1;
        Height = ladybug.getRegionHeight() + 1;

        run();
    }

    private void run() {
        type = (byte)random.nextInt(1);

        if (status != 0) status = 0;
        if (angle != 0) angle = 0;

        if (type == 0) position = new Vector3(400, 748, 0);
    }

    public void update(float deltaTime) {
        if (type == 0) type_1();
    }

    private void type_1() {
        if (status == 0) {
            angle--;
        }
    }

    public int getAngle() {
        return angle;
    }

    public TextureRegion getLadybug() {
        return ladybug;
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public void dispose() {
        ladybug = null;
    }
}