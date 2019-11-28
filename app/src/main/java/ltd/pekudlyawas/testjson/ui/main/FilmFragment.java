package ltd.test_task.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import ltd.test_task.R;
import ltd.test_task.jsonData.FILMDATA;

/**Сформированный системой фрагмент с ViewModel
 * добавоена переменная filmId получаемая по Instance для поиска и
 * отображения данных фильма т.к. в задаении небыло свайпов и перелистыванию
 * фильмов внутири фрейма работа переменной развития не получила
 *
 * ****/

public class FilmFragment extends Fragment {

    private static final String FILMID = "ltd.test_task.FILMID";
    private int filmId = -1;
    private FILMDATA.Film filmData;

    private MainViewModel mViewModel;


    public static FilmFragment newInstance(int filmId) {
        FilmFragment fragment = new FilmFragment();
        Bundle args = new Bundle();
        args.putInt(FILMID, filmId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            filmId = getArguments().getInt(FILMID);
            filmData = MainViewModel.GetFilmData(filmId);
        }

        View v = inflater.inflate(R.layout.film_fragment, container, false);


        if (filmData != null) {
            ((TextView) v.findViewById(R.id.tv_film)).setText(filmData.filmName);
            ((TextView) v.findViewById(R.id.tv_filmYear)).setText("Год: " + filmData.filmYear);





/*          Так и не понял почему не сработало
            String text = "Рейтинг: " + filmData.filmRating;
            SpannableString ss = new SpannableString(text);
            ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
            ss.setSpan(fcsGreen, 9, text.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((TextView) v.findViewById(R.id.tv_filmRating)).setText(text);*/

            TextView tv_filmRating = v.findViewById(R.id.tv_filmRating_2);
            tv_filmRating.setText(String.valueOf(filmData.filmRating));

            if (filmData.filmRating < 5) tv_filmRating.setTextColor(getResources().getColor(R.color.colorAccent));

            ((TextView) v.findViewById(R.id.tv_filmDescription)).setText(filmData.filmDescription);
            ImageView iv_film =  v.findViewById(R.id.iv_film);
            if (filmData.filmImage_url != null && !filmData.filmImage_url.trim().isEmpty())
                Picasso.with(getActivity()).load(filmData.filmImage_url).fit().centerInside()
                        .error(R.drawable.ic_filter)
                        .into(iv_film);
            else iv_film.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_filter));

        }return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getActivity().setTitle(filmData.filmLocalized_Name);
        // TODO: Use the ViewModel
    }



}
