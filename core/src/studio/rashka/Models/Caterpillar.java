package studio.rashka.Models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import studio.rashka.BurrowGame;
import studio.rashka.Lib.AnimationModels;

/**
 * Гусеница
 */

public class Caterpillar {

    private static final byte RUN = 1; // скорость передвижения
    private static final byte RUN_BACK = -1;
    private byte status = 0; // статус поворота
    private byte type; // тип передвижения
    private int angle = -90; // градус поворота

    private AnimationModels animation;

    private Vector3 position;
    private float Height, Width; // размеры гусеницы

    private Random random;

    public void Caterpillar() {

        random = new Random();

        animation = new AnimationModels(new TextureRegion(BurrowGame.getTextures().getCaterpillar()), 4, 0.5f);

        Width = animation.getFrameWidth() + 1;
        Height = animation.getFrameHeight() + 1;

        run();
     }

    private void run() {
        type = (byte)random.nextInt(3);

        if (angle != -90) angle = -90; // обнуляем данные если были изменения
        if (status != 0) status = 0; // обнуляем данные если были изменения

        if (type == 0) position = new Vector3(0 - 192, 164 - 192, 0); // возвращаем исходное положение
        if (type == 1) position = new Vector3(0 - 192, 416, 0);
        if (type == 2) {
            position = new Vector3(BurrowGame.WIDTH - 50, 0 - 150, 0);
            angle = 45;
        }
    }

    public void update(float deltaTime) {
        animation.update(deltaTime);
        if (type == 0) type_1();
        if (type == 1) type_2();
        if (type == 2) type_3();
    }

    private void type_1() {
        if (status == 0) {
            if (position.x < 1760) {
                position.add(RUN, 0, 0);
            }
            if (position.x >= 1760) {
                if (angle >= -90 && angle < 0) angle++;
                if (angle == 0) position.add(0, RUN, 0);
            }
            if (position.y == 400) if (angle >= 0 && angle < 180) angle++;
            if (position.y <= 400 && angle == 180) {
                position.add(0, RUN_BACK, 0);
                if (position.y <= -28) status = 1;
            }
        }
        if (status == 1) {
            if (position.y <= -28) {
                if (angle <= 180 && angle > 90) angle--;
                if (angle == 90) position.add(RUN_BACK, 0, 0);
            }
            if (position.x < -192) run();
        }
    }

    private void type_2() {
        if (status == 0) {
            if (position.x < 1024) position.add(RUN, 0, 0);
            if (position.x >= 1024) {
                if (angle <= -90 && angle > -135) angle--;
                if (angle == -135) position.add(RUN, RUN_BACK, 0);
            }
            if (position.y <= -200) status = 1;
        }
        if (status == 1) run();
    }

    private void type_3() {
        if (status == 0) {
            if (position.x > 1220) position.add(RUN_BACK, RUN, 0);
            if (position.x <= 1220) {
                if (angle >= 45 && angle < 135) angle++;
                if (angle == 135) position.add(RUN_BACK, RUN_BACK, 0);
                if (position.y <= -150) run();
            }
        }
    }

    public TextureRegion getCaterpillar() {
        return animation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getHeight() {
        return Height;
    }

    public float getWidth() {
        return Width;
    }

    public int getAngle() {
        return angle;
    }

    public void dispose() {
        //status = 0;
    }
}