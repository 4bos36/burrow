package studio.rashka.Lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import studio.rashka.BurrowGame;

public class MusicSound {

    private Music music;
    private Sound click, up, tap, hihi, gameover;

    public void MusicSound() {

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/fon_play.ogg"));

        click = Gdx.audio.newSound(Gdx.files.internal("sounds/button.ogg"));
        up = Gdx.audio.newSound(Gdx.files.internal("sounds/up.ogg"));
        tap = Gdx.audio.newSound(Gdx.files.internal("sounds/tap.ogg"));
        hihi = Gdx.audio.newSound(Gdx.files.internal("sounds/hihi.ogg"));
        gameover = Gdx.audio.newSound(Gdx.files.internal("sounds/gameover.ogg"));
    }

    public void StartFon(){
        if (!music.isPlaying()) {
            music.setLooping(true);
            music.setVolume(BurrowGame.getPreference().loadVolume());
            music.play();
        }
    }

    public void releaseMP() {
        if (music != null) { // если запущено, то выключаем
            try {
                music.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Music getMusic() {
        return music;
    }

    public Sound getClick() {
        return click;
    }

    public Sound getUp() {
        return up;
    }

    public Sound getTap() {
        return tap;
    }

    public Sound getHihi() {
        return hihi;
    }

    public Sound getGameover() {
        return gameover;
    }

    public void dispose() {
        releaseMP();
        click.dispose();
        up.dispose();
        tap.dispose();
        hihi.dispose();
        gameover.dispose();
    }
}