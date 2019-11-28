package ltd.test_task.ui.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ltd.test_task.MainActivity;
import ltd.test_task.R;
import ltd.test_task.adapters.AutoFitGridLayoutManager;
import ltd.test_task.adapters.genresAdapter;

/**
 * Первый фрагмент отображения списков жанров и предпросмотра фильмов
 * */

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static RecyclerView gRecyclerView;
    public static RecyclerView fRecyclerView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        gRecyclerView = v.findViewById(R.id.rv_Genres);
        gRecyclerView.setHasFixedSize(true);
       // MainActivity.gAdapter = new genresAdapter();
        gRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gRecyclerView.setAdapter(MainActivity.gAdapter);

        MainActivity.gAdapter.setOnItemClickListener(new genresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ArrayList<Integer> ids) {
                MainActivity.gAdapter.notifyDataSetChanged();
                if (MainActivity.fAdapter != null)
                    MainActivity.fAdapter.applyFilter(ids);
            }
        });


        fRecyclerView = v.findViewById(R.id.rv_Films);
        fRecyclerView.setHasFixedSize(true);
        //MainActivity.fAdapter = new filmsAdapter(getActivity());
        MainActivity.fAdapter.setContext(getActivity());

        int width = (Resources.getSystem().getDisplayMetrics().widthPixels) / 2;
        fRecyclerView.setLayoutManager(new AutoFitGridLayoutManager(getActivity(), width - 10));
        fRecyclerView.setAdapter(MainActivity.fAdapter);



        if (savedInstanceState != null)  {
            MainActivity.gAdapter.setData(MainViewModel.FilmsList);
            MainActivity.fAdapter.setData(MainViewModel.FilmsList);
            MainActivity.fAdapter.applyFilter(MainActivity.gAdapter.getSelectedIds());
        }


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getActivity().setTitle("Фильмы");


       /* new Thread(new Runnable() {
            public void run() {
                while (mViewModel.FilmsList == null)
                    SystemClock.sleep(100);
            }
        }).start();*/

    }

}
