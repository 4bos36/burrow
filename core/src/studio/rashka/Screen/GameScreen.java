package studio.rashka.Screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import studio.rashka.BurrowGame;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;

public class GameScreen extends State {

    public GameScreen(final Game game) {
        super(game);

        BurrowGame.getTopTop().TopTop(); // запускаем игру
    }

    @Override
    public void update(float deltaTime) {
        if (BurrowGame.getPreference().loadGrafics() == 1) {
            BurrowGame.getCloud().update(deltaTime);
            BurrowGame.getCaterpillar().update(deltaTime);
        }
        BurrowGame.getTopTop().update(deltaTime);

        if (BurrowGame.getPreference().isShowRecords()) game.set(new RecordScreen(game));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(BurrowGame.getTextureMap().get("fon"), 0, 0, BurrowGame.WIDTH, BurrowGame.HEIGHT);
        if (BurrowGame.getPreference().loadGrafics() == 1) sb.draw(BurrowGame.getCaterpillar().getCaterpillar(), BurrowGame.getCaterpillar().getPosition().x, BurrowGame.getCaterpillar().getPosition().y,
                BurrowGame.getCaterpillar().getWidth() / 2, BurrowGame.getCaterpillar().getHeight() / 2, BurrowGame.getCaterpillar().getWidth(), BurrowGame.getCaterpillar().getHeight(),
                1, 1, BurrowGame.getCaterpillar().getAngle());

        if (BurrowGame.getTopTop().getLive() >= 1) {
            sb.draw(BurrowGame.getTopTop().getToptop_l_1(), 64, 32);
            sb.draw(BurrowGame.getTopTop().getToptop_l_2(), 64, 416);
            sb.draw(BurrowGame.getTopTop().getToptop_l_3(), 448, 96);
            sb.draw(BurrowGame.getTopTop().getToptop_l_4(), 448, 480);

            sb.draw(BurrowGame.getTopTop().getToptop_r_1(), 1152, 96);
            sb.draw(BurrowGame.getTopTop().getToptop_r_2(), 1152, 480);
            sb.draw(BurrowGame.getTopTop().getToptop_r_3(), 1536, 32);
            sb.draw(BurrowGame.getTopTop().getToptop_r_4(), 1536, 416);
        }

        if (BurrowGame.getTopTop().getLive() <= 0) {
            if (BurrowGame.getPreference().loadLanguage() == 1)
                sb.draw(BurrowGame.getTextures().textureRegions.get("gaveover_ru"),
                        BurrowGame.WIDTH / 2 - 480, BurrowGame.HEIGHT / 2 - 96, 320, 64, 320 * 3, 64 * 3, 1, 1, 0);
            else if (BurrowGame.getPreference().loadLanguage() == 2)
                sb.draw(BurrowGame.getTextures().textureRegions.get("gaveover_en"),
                        BurrowGame.WIDTH / 2 - 480, BurrowGame.HEIGHT / 2 - 96, 320, 64, 320 * 3, 64 * 3, 1, 1, 0);
        }

        if (BurrowGame.getTopTop().getLive() == 3) {
            sb.draw(BurrowGame.getTextures().textureRegions.get("live"), 768, BurrowGame.HEIGHT - 140);
            sb.draw(BurrowGame.getTextures().textureRegions.get("live"), 768 + BurrowGame.getTextures().textureRegions.get("live").getRegionWidth(), BurrowGame.HEIGHT - 140);
            sb.draw(BurrowGame.getTextures().textureRegions.get("live"), 768 + BurrowGame.getTextures().textureRegions.get("live").getRegionWidth() * 2, BurrowGame.HEIGHT - 140);
        } else if (BurrowGame.getTopTop().getLive() == 2) {
            sb.draw(BurrowGame.getTextures().textureRegions.get("live"), 768 + BurrowGame.getTextures().textureRegions.get("live").getRegionWidth() - 64, BurrowGame.HEIGHT - 140);
            sb.draw(BurrowGame.getTextures().textureRegions.get("live"), 768 + BurrowGame.getTextures().textureRegions.get("live").getRegionWidth() * 2 - 64, BurrowGame.HEIGHT - 140);
        } else if (BurrowGame.getTopTop().getLive() == 1) {
            sb.draw(BurrowGame.getTextures().textureRegions.get("live"), 768 + BurrowGame.getTextures().textureRegions.get("live").getRegionWidth(), BurrowGame.HEIGHT - 140);
        }

        BurrowGame.getFontTTF().getFontScore().draw(sb, "" + BurrowGame.getTopTop().getScore(), BurrowGame.WIDTH - 96, BurrowGame.HEIGHT - 32);

        sb.draw(BurrowGame.getCloud().getCloud(), BurrowGame.getCloud().getPosition().x, BurrowGame.getCloud().getPosition().y);
        sb.end();

        BurrowGame.getTopTop().getStage().draw();
        BurrowGame.getTopTop().getStage().act();
    }

    @Override
    public void dispose() {
        BurrowGame.getTopTop().getStage().dispose();
        BurrowGame.getTopTop().dispose();
    }
}