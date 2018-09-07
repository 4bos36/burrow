package studio.rashka.Models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import studio.rashka.BurrowGame;

/**
 * Облачко
 */

public class Cloud {

    private static final byte FLY_LEFT = -1; // на сколько летим влево
    private static final byte FLY_RIGHT = 1; // на сколько летим вправо
    private byte status = FLY_LEFT; // направление полёта

    private TextureRegion cloud;
    private Vector3 position;
    private float x; // для определения выхода за границы
    private float Height, Width;

    public void Cloud(float x, float y) {
        this.x = x;

        cloud = BurrowGame.getTextures().textureRegions.get("cloud");

        Height = cloud.getRegionHeight() + 1; // размеры облака
        Width = cloud.getRegionWidth() + 1;

        position = new Vector3(x, y - Height, 0); // координаты полёта
    }

    public void update(float deltaTime) {
        if (status == FLY_LEFT) { // определяем куда летит облако
            position.add(FLY_LEFT, 0, 0);
            if (position.x < 0 - cloud.getRegionWidth()) status = FLY_RIGHT;
        }
        if (status == FLY_RIGHT) {
            position.add(FLY_RIGHT, 0, 0);
            if (position.x > x + cloud.getRegionWidth()) status = FLY_LEFT;
        }
    }

    public float getHeight() {
        return Height;
    }

    public float getWidth() {
        return Width;
    }

    public TextureRegion getCloud() {
        return cloud;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void dispose() {
        cloud = null;
    }
}