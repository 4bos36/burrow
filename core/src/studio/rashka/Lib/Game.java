package studio.rashka.Lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;

public class Game {

    private ArrayDeque<State> states;

    public Game() {
        states = new ArrayDeque<State>(); // массив для состояний экранов игры
    }

    public void push(State state) {
        states.push(state); // помещает экран игры
    }

    public void pop() {
        states.pop().dispose(); // очищает из массива
    }

    public void set(State state) { // очищает и создаёт новый
        states.pop().dispose();
        states.push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}