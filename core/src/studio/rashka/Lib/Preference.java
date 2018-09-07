package studio.rashka.Lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.util.Map;
import java.util.TreeMap;

public class Preference {

    private Preferences setting;
    private String[] CacheName = new String[]{"", "", "", "", "", "", "", "", "", "", ""};
    private String[] CacheRecords = new String[]{"", "", "", "", "", "", "", "", "", "", ""};

    public FileHandle fileRecords; // файл рекордов

    private Map<String, String> nameRecord; // массив для временного хранения рекордов
    private int nomer = 1; // номер занятого места в рейтинге
    private char character; // для обработки данных посимвольно
    private StringBuilder name = new StringBuilder(""); // массив для структурированной обработки данных из файла рекордов
    //private StringBuilder nameX;
    private boolean isShowRecords = false;

    public Preference() {
        setting = Gdx.app.getPreferences("Pref_app"); // настройки приложения
        //setting.clear(); // если изменили тип данных, то разкомментироветь, запускаем и комментируем обратно, т.е. обнуляем всё

        Boolean start_pref = setting.getBoolean("Start_Pref");
        if (!start_pref) { // если запускается в первые, то запускаем параметры по умолчанию
            Gdx.files.internal("records.bin").copyTo(Gdx.files.local("records/records.bin"));
            saveSound(1);
            saveMusic(1);
            saveGrafics(1);
            saveVolume(5);
            saveLanguage(0);
            setting.putBoolean("Start_Pref", true); // сообщаем, что приложение уже было запущено
            setting.flush();
        }

        nameRecord = new TreeMap<String, String>();
        fileRecords = Gdx.files.local("records/records.bin"); // таблица рекордов игроков

        //Gdx.app.log("pref ????????????", "" + Gdx.files.getLocalStoragePath());
        loadRecords(fileRecords.readString().length());
    }

    public int width() {
        return setting.getInteger("Width");
    }

    public int height() {
        return setting.getInteger("Height");
    }

    public void loadRecords(long len) {
        nomer = 1;
        for (int i = 0; i < len; i++) {
            character = fileRecords.readString().charAt(i);
            StringBuilder nameX = new StringBuilder("Name_");
            nameX.append(nomer);
            if (character == '~') {
                name.append(" * ");
                for (int j = i+1; j < len; j++) {
                    character = fileRecords.readString().charAt(j);
                    if (character == '.') {
                        nameRecord.put(nameX.toString(), name.toString());
                        name.append("\n");
                        i = j;
                        nomer++;
                        break;
                    }
                    else {
                        name.append(fileRecords.readString().charAt(j));
                        CacheRecords[nomer - 1] += fileRecords.readString().charAt(j);
                    }
                }
            }
            else {
                name.append(fileRecords.readString().charAt(i));
                CacheName[nomer - 1] += fileRecords.readString().charAt(i);
            }
        }
    }

    public void saveRecords() {
        fileRecords.writeString("", false); // очищаем
        for (int i = 0; i < nomer - 1; i++)
            fileRecords.writeString(CacheName[i] + "~" + CacheRecords[i] + ".", true);
    }

    public void addScore(String NameGamer, int score) {
        CacheName[10] = NameGamer;
        CacheRecords[10] = String.valueOf(score);
        int i = 1;
        while (i < CacheRecords.length) {
            if (i == 0 || Integer.parseInt(CacheRecords[i - 1]) >= Integer.parseInt(CacheRecords[i])) i++;
            else {
                String tempName = CacheName[i];
                CacheName[i] = CacheName[i - 1];
                CacheName[i - 1] = tempName;

                int tempRec = Integer.parseInt(CacheRecords[i]);
                CacheRecords[i] = CacheRecords[i - 1];
                CacheRecords[i - 1] = String.valueOf(tempRec);
                i--;
            }
        }
        saveRecords();
    }

    public String getCacheRecords(int i) { // получаем определённого рекордсмена или всех по циклу
        return CacheRecords[i];
    }

    public String getCacheName(int i) {
        return CacheName[i];
    }

    public void saveSound(int on_off) { // сохраняем настройки звуков
        setting.putInteger("Sound", on_off);
        setting.flush();
    }

    public int loadSound() { // загружаем настройки звуков
        return setting.getInteger("Sound");
    }

    public void saveMusic(int on_off) { // сохраняем настройки музыки
        setting.putInteger("Music", on_off);
        setting.flush();
    }

    public int loadMusic() { // загружаем настройки музыки
        return setting.getInteger("Music");
    }

    public void saveVolume(int volume) { // сохраняем уровень громкости музыки
        setting.putInteger("Volume", volume);
        setting.flush();
    }

    public int lVolume() {
        return setting.getInteger("Volume");
    }

    public float loadVolume() { // загружаем уровень громкости музыки
        int volume = setting.getInteger("Volume");
        float lv_volume = 0.0f;
        switch (volume) {
            case 1:
                lv_volume = 0.0f; // выключаем
                break;
            case 2:
                lv_volume = 0.25f; // делаем громкость на 25%
                break;
            case 3:
                lv_volume = 0.5f; // делаем громкость на 50%
                break;
            case 4:
                lv_volume = 0.75f; // делаем громкость на 75%
                break;
            case 0:
            case 5:
                lv_volume = 1.0f; // делаем на полную мощь
                break;
        }
        return lv_volume;
    }

    public void saveLanguage(int lng) { // сохраняем язык интерфейса
        setting.putInteger("Language", lng);
        setting.flush();
    }

    public int loadLanguage() { // загружаем язык интерфейса
        return setting.getInteger("Language");
    }

    public void saveGrafics(int graf) { // сохраняем параметры включения дополнительной графики
        setting.putInteger("Grafics", graf);
        setting.flush();
    }

    public int loadGrafics() { // загружаем дополнительную графику
        return setting.getInteger("Grafics");
    }

    public boolean isShowRecords() {
        return isShowRecords;
    }

    public void setShowRecords(boolean showRecords) {
        isShowRecords = showRecords;
    }
}