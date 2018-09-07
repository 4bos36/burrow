package studio.rashka.Lib;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Map;
import java.util.TreeMap;

public class Textures {

    private TextureAtlas AtlasTexture, textureNull;
    private Skin AtlasTextureSkin, textureNullSkin;

    private Texture atlas, AtlasSignboard, caterpillar;
    public Map<String, TextureRegion> textureRegions; //массив регионов

    public Textures() {

        textureNull = new TextureAtlas("null.atlas");
        textureNullSkin = new Skin(textureNull);

        caterpillar = new Texture("caterpillar.png"); // спрайт

        AtlasTexture = new TextureAtlas("texture.atlas");
        AtlasTextureSkin = new Skin(AtlasTexture);

        AtlasSignboard = new Texture("signboard.png");

        atlas = new Texture("texture_atlas.png");
        textureRegions = new TreeMap<String, TextureRegion>();

        textureRegions.put("volume_00", new TextureRegion(atlas, 0, 0, 256, 64));
        textureRegions.put("volume_25", new TextureRegion(atlas, 0, 64, 256, 64));
        textureRegions.put("volume_50", new TextureRegion(atlas, 0, 128, 256, 64));
        textureRegions.put("volume_75", new TextureRegion(atlas, 0, 192, 256, 64));
        textureRegions.put("volume_100", new TextureRegion(atlas, 0, 256, 256, 64));
        textureRegions.put("volume_up", new TextureRegion(atlas, 256, 0, 64, 64));
        //textureRegions.put("volume_down", new TextureRegion(atlas, 320, 0, 64, 64));
        textureRegions.put("live", new TextureRegion(atlas, 256, 192, 128, 128));
        textureRegions.put("start", new TextureRegion(atlas, 384, 0, 92, 128));
        textureRegions.put("pause", new TextureRegion(atlas, 480, 0, 92, 128));
        textureRegions.put("cloud", new TextureRegion(atlas, 192, 320, 352, 192));
        textureRegions.put("medal", new TextureRegion(atlas, 192, 512, 64, 64));
        textureRegions.put("toptop_off", new TextureRegion(atlas, 0, 640, 320, 384));
        textureRegions.put("toptop_on", new TextureRegion(atlas, 320, 640, 320, 384));
        textureRegions.put("toptop_tap", new TextureRegion(atlas, 640, 640, 320, 384));
        textureRegions.put("toptop_fall", new TextureRegion(atlas, 576, 256, 320, 384));
        textureRegions.put("loader", new TextureRegion(atlas, 576, 0, 256, 256));
        textureRegions.put("gaveover_ru", new TextureRegion(atlas, 256, 512, 320, 64));
        textureRegions.put("gaveover_en", new TextureRegion(atlas, 256, 576, 320, 64));
        textureRegions.put("ladybug", new TextureRegion(atlas, 832, 0, 96, 96));

        textureRegions.put("app_name_en", new TextureRegion(AtlasSignboard, 0, 0, 500, 320));
        textureRegions.put("app_name_ru", new TextureRegion(AtlasSignboard, 0, 320, 500, 320));
        textureRegions.put("app_setting_en", new TextureRegion(AtlasSignboard, 512, 0, 500, 320));
        textureRegions.put("app_setting_ru", new TextureRegion(AtlasSignboard, 512, 320, 500, 320));

    }

    public Skin getAtlasTextureSkin() {
        return AtlasTextureSkin;
    }

    public Skin getTextureNullSkin() {
        return textureNullSkin;
    }

    public Texture getCaterpillar() {
        return caterpillar;
    }

    public void dispose() {
        atlas.dispose();
        AtlasSignboard.dispose();
        caterpillar.dispose();

        textureNull.dispose();
        textureNullSkin.dispose();

        AtlasTexture.dispose();
        AtlasTextureSkin.dispose();
        textureRegions.clear();
    }
}