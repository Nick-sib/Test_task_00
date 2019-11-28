package ltd.test_task.ui.main;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;
import ltd.test_task.jsonData.FILMDATA;

/**
 * Модель хранения данных сохраняемых после повороа экана
 * **/

public class MainViewModel extends ViewModel {


    public static ArrayList<FILMDATA.Film> FilmsList; //Данные полученые их JSON
    public static boolean showBack = false;         //Метка показа кнопки назад
    public static final String LOG = "myLOG";       //константа для логов

//по ИД ищем фильм
    public static FILMDATA.Film GetFilmData(int FilmId){
        for (FILMDATA.Film film : FilmsList)
            if (film.filmId == FilmId)
                return film;
        return null;
    }


    // TODO: Implement the ViewModel
}
