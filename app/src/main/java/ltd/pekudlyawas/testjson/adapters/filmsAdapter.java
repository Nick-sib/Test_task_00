package ltd.test_task.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ltd.test_task.R;
import ltd.test_task.jsonData.FILMDATA;

/**Достаточно стандврный Адаптер для отображения списка фильмов
 * для загрузки картинов используется Picasso
 * implementation 'com.squareup.picasso:picasso:2.5.2'
 *  при развитии проекта загружение картинки надо сохранять
 * ***/

public class filmsAdapter extends RecyclerView.Adapter<filmsAdapter.ExampleViewHolder> {
    private Context mContext;

    private static ArrayList<FILMDATA.Film> filmsList;
    private ArrayList<FILMDATA.Film> filmsListFull;

    private filmsAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int filmID);
    }

    public void setOnItemClickListener(filmsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;


        public ExampleViewHolder(View itemView, final filmsAdapter.OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_film);
            mImageView = itemView.findViewById(R.id.iv_film);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(filmsList.get(position).filmId);
                        }
                    }
                }
            });
        }
    }

    public filmsAdapter() {
        filmsList = new ArrayList<>();
        filmsListFull = new ArrayList<>();
    }

    public void setContext(Context contex){
        mContext = contex;
    }

    public void setData(ArrayList<FILMDATA.Film> value){
        filmsList = new ArrayList<>(value);
        filmsListFull = value;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.film_item, parent, false);
        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {


        FILMDATA.Film currentFilm = filmsList.get(position);
        holder.mTextView.setText(currentFilm.filmLocalized_Name);

        String imageUrl = currentFilm.filmImage_url;
        //Log.d(MainActivity.LOG, "imageUrl = " + imageUrl);

        if (imageUrl != null && !imageUrl.trim().isEmpty())
            Picasso.with(mContext).load(imageUrl).fit().centerCrop()
                    .error(R.drawable.ic_filter)
                    .into(holder.mImageView);/*For seek err*, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Log.d(MainActivity.LOG,"ERR");
                    }
                });*/
        else holder.mImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_filter));
    }

    @Override
    public int getItemCount() {
        return filmsList.size();
    }

    public void applyFilter(ArrayList<Integer> filteredList) {
        filmsList.clear();
        if (filteredList == null) filmsList.addAll(filmsListFull); else {
            for (FILMDATA.Film film : filmsListFull)
                if (filteredList.contains(film.filmId))
                    filmsList.add(film);}
        notifyDataSetChanged();
    }
}
