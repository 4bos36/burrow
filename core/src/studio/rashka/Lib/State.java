package studio.rashka.Lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void update(float deltaTime); // обновление экрана
    public abstract void render(SpriteBatch sb); // рисование экрана
    public abstract void dispose(); // очистка
}
