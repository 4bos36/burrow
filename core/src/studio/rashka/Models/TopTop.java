package studio.rashka.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import studio.rashka.BurrowGame;
import studio.rashka.Lib.InputGamer;
import studio.rashka.Screen.MenuScreen;

public class TopTop {

    private Stage stage;
    private InputGamer inputGamer; // вводим имя и сохраняем очки

    private ImageButton
            click_toptop_l, click_toptop_r,
            btn_home, btn_sp_game;
    private ImageButtonStyle
            click_toptop_style_l, click_toptop_style_r,
            btn_home_style, btn_sp_game_style;

    private byte START_GAME = 0;

    private byte statusL = 0, statusR = 0, // разрешить/запретить выпрыгивание из норки
                statusTapL = 0, statusTapR = 0, // определяем успели ли нажать
                statusClickL = 0, statusClickR = 0,
                upL = 0, up_fall_L = 0, // уже выгрыгнул или нет
                upR = 0, up_fall_R = 0;

    private static final byte SCORE_ADD = 1;
    private static float TIME_START_L, TIME_START_R; // время выскакивыния
    private int scoreUp_L = 0, scoreUp_R = 0; // для увеличения скорости игры
    private static float TIME_UP;

    private boolean GameOver = false, MusOver = true, scoreUpdate = false;
    private int time_save = 0; // задержка для внесения в рейтинг
    private byte live = 3; // стартовое количество жизней
    private int score = 0; // сколько натыкали

    private Random random_l, random_r;
    private byte rand_l, rand_r, // где выскакиваем
            rand_l_null = 1, rand_r_null = 1; // запускаем один раз
    private int time_up_l = 0, time_click_l = 0, time_tap_fall_l = 0,
            time_up_r = 0, time_click_r = 0, time_tap_fall_r = 0;

    private TextureRegion toptop_l_1, toptop_l_2, toptop_l_3, toptop_l_4,
            toptop_r_1, toptop_r_2, toptop_r_3, toptop_r_4;

    public void TopTop() {

        stage = new Stage();
        inputGamer = new InputGamer();

        TIME_START_L = 1.5f * 60; // время старта игры
        TIME_START_R = 1.5f * 60;
        TIME_UP = 0.1f * 40; // время ускорения игры

        TopTopOff_L();
        TopTopOff_R();

        live = 3;
        score = 0;
        GameOver = false;
        MusOver = true;
        time_save = 0;
        START_GAME = 1;
        scoreUpdate = false;

        random_l = new Random(); // случайное выпрыгивание по левой стороне
        random_r = new Random(); // случайное выпрыгивание по правой стороне

        click_toptop_style_l = new ImageButton.ImageButtonStyle(); // свойства кнопок
        click_toptop_style_l.down = BurrowGame.getTextures().getTextureNullSkin().getDrawable("null");
        click_toptop_l = new ImageButton(click_toptop_style_l);
        click_toptop_l.setSize(click_toptop_l.getWidth() * BurrowGame.getRatioMonitorW(), click_toptop_l.getHeight() * BurrowGame.getRatioMonitorH());
        click_toptop_l.setPosition(BurrowGame.WIDTH + 100, BurrowGame.HEIGHT + 100);
        click_toptop_l.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (START_GAME == 1 && statusClickL == 1) {
                    statusTapL = 1;
                    statusClickL = 0;
                    BurrowGame.getMusicSound().getTap().play();
                    //Gdx.input.vibrate(50);
                    score += SCORE_ADD;
                    scoreUp_L++;
                    scoreUpdate = true;
                    if (rand_l == 0) toptop_l_1 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                    else if (rand_l == 1) toptop_l_2 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                    else if (rand_l == 2) toptop_l_3 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                    else if (rand_l == 3) toptop_l_4 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                }
            }
        });

        click_toptop_style_r = new ImageButton.ImageButtonStyle(); // свойства кнопок
        click_toptop_style_r.down = BurrowGame.getTextures().getTextureNullSkin().getDrawable("null");
        click_toptop_r = new ImageButton(click_toptop_style_r);
        click_toptop_r.setSize(click_toptop_r.getWidth() * BurrowGame.getRatioMonitorW(), click_toptop_r.getHeight() * BurrowGame.getRatioMonitorH());
        click_toptop_r.setPosition(BurrowGame.WIDTH + 100, BurrowGame.HEIGHT + 100);
        click_toptop_r.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (START_GAME == 1 && statusClickR == 1) {
                    statusTapR = 1;
                    statusClickR = 0;
                    BurrowGame.getMusicSound().getTap().play();
                    //Gdx.input.vibrate(50);
                    score += SCORE_ADD;
                    scoreUp_R++;
                    scoreUpdate = true;
                    if (rand_r == 0) toptop_r_1 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                    else if (rand_r == 1) toptop_r_2 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                    else if (rand_r == 2) toptop_r_3 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                    else if (rand_r == 3) toptop_r_4 = BurrowGame.getTextures().textureRegions.get("toptop_tap");
                }
            }
        });

        /********** КНОПКА ДОМОЙ **********/

        btn_home_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        btn_home_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("home");
        btn_home_style.down = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("home_click");

        btn_home = new ImageButton(btn_home_style);
        btn_home.setSize(btn_home.getWidth() * BurrowGame.getRatioMonitorW(), btn_home.getHeight() * BurrowGame.getRatioMonitorH());
        btn_home.setPosition(64 * BurrowGame.getRatioMonitorW(), (BurrowGame.HEIGHT - 128) * BurrowGame.getRatioMonitorH());
        btn_home.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                BurrowGame.getGame().set(new MenuScreen(BurrowGame.getGame()));
                //Gdx.input.vibrate(50);
            }
        });

        /********** КНОПКА СТАРТ/ПАУЗА ИГРЫ **********/

        btn_sp_game_style = new ImageButton.ImageButtonStyle(); // свойства кнопок
        if (START_GAME == 1) btn_sp_game_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("start");
        if (START_GAME == 0) btn_sp_game_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("pause");

        btn_sp_game = new ImageButton(btn_sp_game_style);
        btn_sp_game.setSize(btn_sp_game.getWidth() * BurrowGame.getRatioMonitorW(), btn_sp_game.getHeight() * BurrowGame.getRatioMonitorH());
        btn_sp_game.setPosition(188 * BurrowGame.getRatioMonitorW(), (BurrowGame.HEIGHT - 128) * BurrowGame.getRatioMonitorH());
        btn_sp_game.addListener(new ClickListener(){
            public void clicked (InputEvent event, float x, float y) {
                if (BurrowGame.getPreference().loadSound() == 1) BurrowGame.getMusicSound().getClick().play();
                switch (START_GAME) {
                    case 1:
                        START_GAME = 0;
                        btn_sp_game_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("pause");
                        //Gdx.input.vibrate(50);
                        break;
                    case 0:
                        START_GAME = 1;
                        btn_sp_game_style.up = BurrowGame.getTextures().getAtlasTextureSkin().getDrawable("start");
                        //Gdx.input.vibrate(50);
                        break;
                }
            }
        });

        stage.addActor(btn_home);
        stage.addActor(btn_sp_game);
        stage.addActor(click_toptop_l);
        stage.addActor(click_toptop_r);
        Gdx.input.setInputProcessor(stage);
    }

    public void update(float deltaTime) {
        if (START_GAME == 1) {
            if (live <= 0) { // если все жизни потрачены
                if (MusOver) {
                    MusOver = false;
                    GameOver = true;
                    BurrowGame.getMusicSound().getGameover().play();
                    TopTopOff_L();
                    TopTopOff_R();
                }
                if (GameOver) {
                    if (time_save < 60) time_save++; // задержка вывода окна
                    else if (time_save == 60) {
                        time_save = 61;
                        if (BurrowGame.getPreference().loadLanguage() == 1)
                            Gdx.input.getTextInput(inputGamer, "Введите своё имя", "", "Только латинские и цифры");
                        else if (BurrowGame.getPreference().loadLanguage() == 2)
                            Gdx.input.getTextInput(inputGamer, "Enter your name", "", "Your name here");
                    }
                }
            }
            if (!GameOver) {
                if (time_up_l < TIME_START_L) { // запускаем отсчёт
                    time_up_l++;
                    statusL = 1; // разрешаем выпрыгнуть
                    upL = 1; // говорим выпрыгнуть можно только одному
                    up_fall_L = 1; // говорим хихикать можно только одному
                }
                if (time_up_l == TIME_START_L) {
                    if (rand_l_null == 1) {
                        rand_l = (byte)random_l.nextInt(4); // определяем кто будет выпрыгивать
                        rand_l_null = 0;
                    }
                    if (time_click_l < TIME_START_L) time_click_l++; // запуск отчёта для прятания
                    if (statusL == 1) {
                        TopTop_L();
                        if (statusTapL == 1) {
                            if (time_tap_fall_l <= TIME_START_L) time_tap_fall_l++;
                            if (time_tap_fall_l == TIME_START_L - 1) {
                                TopTopOff_L();
                                return;
                            }
                        }
                        if (time_click_l == TIME_START_L && statusTapL == 0) {
                            click_toptop_l.setPosition(BurrowGame.WIDTH + 100, BurrowGame.HEIGHT + 100);
                            TopTopFall_L();
                            if (time_tap_fall_l < TIME_START_L) time_tap_fall_l++;
                            if (time_tap_fall_l == TIME_START_L) TopTopOff_L();
                        }
                    }
                }

                /******************************************************/

                if (time_up_r < TIME_START_R) { // запускаем отсчёт
                    time_up_r++;
                    statusR = 1; // разрешаем выпрыгнуть
                    upR = 1; // говорим выпрыгнуть можно только одному
                    up_fall_R = 1; // говорим хихикать можно только одному
                }
                if (time_up_r == TIME_START_R) {
                    if (rand_r_null == 1) {
                        rand_r = (byte)random_r.nextInt(4); // определяем кто будет выпрыгивать
                        rand_r_null = 0;
                    }
                    if (time_click_r < TIME_START_R) time_click_r++; // запуск отчёта для прятания
                    if (statusR == 1) {
                        TopTop_R();
                        if (statusTapR == 1) {
                            if (time_tap_fall_r <= TIME_START_R) time_tap_fall_r++;
                            if (time_tap_fall_r == TIME_START_R - 1) {
                                TopTopOff_R();
                                return;
                            }
                        }
                        if (time_click_r == TIME_START_R && statusTapR == 0) {
                            click_toptop_r.setPosition(BurrowGame.WIDTH + 100, BurrowGame.HEIGHT + 100);
                            TopTopFall_R();
                            if (time_tap_fall_r < TIME_START_R) time_tap_fall_r++;
                            if (time_tap_fall_r == TIME_START_R) TopTopOff_R();
                        }
                    }
                }
            }
        }
    }

    /********** ЛЕВАЯ ЧАСТЬ *********/

    private void TopTop_L() {
        if (upL == 1) {
            upL = 0;
            statusClickL = 1;
            BurrowGame.getMusicSound().getUp().setPan(0, -1, BurrowGame.getPreference().loadVolume());
            BurrowGame.getMusicSound().getUp().play();
            if (rand_l == 0) {
                toptop_l_1 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_l.setPosition(64 * BurrowGame.getRatioMonitorW(), 32 * BurrowGame.getRatioMonitorH());
            } else if (rand_l == 1) {
                toptop_l_2 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_l.setPosition(64 * BurrowGame.getRatioMonitorW(), 416 * BurrowGame.getRatioMonitorH());
            } else if (rand_l == 2) {
                toptop_l_3 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_l.setPosition(448 * BurrowGame.getRatioMonitorW(), 96 * BurrowGame.getRatioMonitorH());
            } else if (rand_l == 3) {
                toptop_l_4 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_l.setPosition(448 * BurrowGame.getRatioMonitorW(), 480 * BurrowGame.getRatioMonitorH());
            }
        }
    }

    private void TopTopFall_L() {
        if (up_fall_L == 1) {
            statusClickL = 0;
            up_fall_L = 0;
            live--;
            BurrowGame.getMusicSound().getHihi().play();
            if (rand_l == 0) toptop_l_1 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
            else if (rand_l == 1) toptop_l_2 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
            else if (rand_l == 2) toptop_l_3 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
            else if (rand_l == 3) toptop_l_4 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
        }
    }

    private void TopTopOff_L() {
        Faster_L();
        if (START_GAME == 0) {
            toptop_l_1 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            toptop_l_2 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            toptop_l_3 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            toptop_l_4 = BurrowGame.getTextures().textureRegions.get("toptop_off");
        } else if (START_GAME == 1) {
            click_toptop_l.setPosition(BurrowGame.WIDTH + 100, BurrowGame.HEIGHT + 100);
            if (rand_l == 0) toptop_l_1 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            else if (rand_l == 1) toptop_l_2 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            else if (rand_l == 2) toptop_l_3 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            else if (rand_l == 3) toptop_l_4 = BurrowGame.getTextures().textureRegions.get("toptop_off");
        }
        time_up_l = 0;
        time_click_l = 0;
        statusL = 0;
        statusTapL = 0;
        time_tap_fall_l = 0;
        rand_l_null = 1;
    }

    /********** ПРАВАЯ ЧАСТЬ *********/

    private void TopTop_R() {
        if (upR == 1) {
            upR = 0;
            statusClickR = 1;
            BurrowGame.getMusicSound().getUp().setPan(0, 1, BurrowGame.getPreference().loadVolume());
            BurrowGame.getMusicSound().getUp().play();
            if (rand_r == 0) {
                toptop_r_1 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_r.setPosition(1152 * BurrowGame.getRatioMonitorW(), 96 * BurrowGame.getRatioMonitorH());
            } else if (rand_r == 1) {
                toptop_r_2 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_r.setPosition(1152 * BurrowGame.getRatioMonitorW(), 480 * BurrowGame.getRatioMonitorH());
            } else if (rand_r == 2) {
                toptop_r_3 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_r.setPosition(1536 * BurrowGame.getRatioMonitorW(), 32 * BurrowGame.getRatioMonitorH());
            } else if (rand_r == 3) {
                toptop_r_4 = BurrowGame.getTextures().textureRegions.get("toptop_on");
                click_toptop_r.setPosition(1536 * BurrowGame.getRatioMonitorW(), 416 * BurrowGame.getRatioMonitorH());
            }
        }
    }

    private void TopTopFall_R() {
        if (up_fall_R == 1) {
            statusClickR = 0;
            up_fall_R = 0;
            live--;
            BurrowGame.getMusicSound().getHihi().play();
            if (rand_r == 0) toptop_r_1 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
            else if (rand_r == 1) toptop_r_2 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
            else if (rand_r == 2) toptop_r_3 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
            else if (rand_r == 3) toptop_r_4 = BurrowGame.getTextures().textureRegions.get("toptop_fall");
        }
    }

    private void TopTopOff_R() {
        Faster_R();
        if (START_GAME == 0) {
            toptop_r_1 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            toptop_r_2 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            toptop_r_3 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            toptop_r_4 = BurrowGame.getTextures().textureRegions.get("toptop_off");
        } else if (START_GAME == 1) {
            click_toptop_r.setPosition(BurrowGame.WIDTH + 100, BurrowGame.HEIGHT + 100);
            if (rand_r == 0) toptop_r_1 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            else if (rand_r == 1) toptop_r_2 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            else if (rand_r == 2) toptop_r_3 = BurrowGame.getTextures().textureRegions.get("toptop_off");
            else if (rand_r == 3) toptop_r_4 = BurrowGame.getTextures().textureRegions.get("toptop_off");
        }
        time_up_r = 0;
        time_click_r = 0;
        statusR = 0;
        statusTapR = 0;
        time_tap_fall_r = 0;
        rand_r_null = 1;
    }

    /********** УВЕЛИЧЕНИЕ СКОРОСТИ ИГРЫ **********/

    private void Faster_L() {
        if (scoreUp_L >= 10) {
            scoreUp_L = 0;
            if (TIME_START_L > 20) TIME_START_L -= TIME_UP; // устанавливаем ограничение до 20 кадров
        }
    }

    private void Faster_R() {
        if (scoreUp_R >= 10) {
            scoreUp_R = 0;
            if (TIME_START_R > 20) TIME_START_R -= TIME_UP; // устанавливаем ограничение до 20 кадров
        }
    }

    /********** ПЕРЕДАЧА ПАРАМЕТРОВ **********/

    public TextureRegion getToptop_l_1() {
        return toptop_l_1;
    }

    public TextureRegion getToptop_l_2() {
        return toptop_l_2;
    }

    public TextureRegion getToptop_l_3() {
        return toptop_l_3;
    }

    public TextureRegion getToptop_l_4() {
        return toptop_l_4;
    }

    public TextureRegion getToptop_r_1() {
        return toptop_r_1;
    }

    public TextureRegion getToptop_r_2() {
        return toptop_r_2;
    }

    public TextureRegion getToptop_r_3() {
        return toptop_r_3;
    }

    public TextureRegion getToptop_r_4() {
        return toptop_r_4;
    }

    public int getLive() {
        return live;
    }

    public Stage getStage() {
        return stage;
    }

    public void setSTART_GAME(byte START_GAME) { // при сворачивании игры становится на паузу
        this.START_GAME = START_GAME;
    }

    public int getScore() {
        return score;
    }

    public void dispose() {
        try {
            btn_home.remove();
            btn_sp_game.remove();
            click_toptop_l.remove();
            click_toptop_r.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}