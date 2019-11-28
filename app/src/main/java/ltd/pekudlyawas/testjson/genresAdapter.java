package ltd.pekudlyawas.testjson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import ltd.pekudlyawas.testjson.jsonData.FILMDATA;

public class genresAdapter extends RecyclerView.Adapter<genresAdapter.ExampleViewHolder> {

    class genres_for_filter {
        String Titl;
        ArrayList<Integer> ids;

        public genres_for_filter(String titl, Integer id) {
            Titl = titl;
            ids = new ArrayList<>();
            ids.add(id);
        }
    }

    private static ArrayList<genres_for_filter> genresList;
    private OnItemClickListener mListener;
    private static int Selected = -1;

    public interface OnItemClickListener {
        void onItemClick(int position, ArrayList<Integer> ids);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_genre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            if (Selected == position) {
                                Selected = -1;
                                listener.onItemClick(position, null);
                            } else {
                                Selected = position;
                                listener.onItemClick(position, genresList.get(position).ids);
                            }
                        }
                    }
                }
            });

        }
    }

    public genresAdapter() {
        genresList = new ArrayList<>();
    }


    public void setData(ArrayList<FILMDATA.Film> value){
        ArrayList<String> tmpTitles = new ArrayList<>();

        for (FILMDATA.Film film : value) {
            for (String val : film.filmGenres)
                if (val != null) {
                    String v = val.trim();
                    if (!v.isEmpty()) {
                        v = v.substring(0, 1).toUpperCase() + v.substring(1);
                        int indx = tmpTitles.indexOf(v);
                        if(indx == -1) {
                            tmpTitles.add(v);
                            genresList.add(new genres_for_filter(v, film.filmId));
                        } else genresList.get(indx).ids.add(film.filmId);
                    }
                }

    }}


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gernre_item, parent, false);

        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        holder.mTextView.setText(genresList.get(position).Titl);
        holder.mTextView.setSelected(position == Selected);
        //((View) holder.mTextView.getParent()).setSelected(position == Selected);

        //Log.d(MainActivity.LOG, "Selected = " +Selected + "\n position = " +  position);
       /* if (Selected == position) {
            ((View) holder.mTextView.getParent()).setBackgroundColor(Color.parseColor("#0D7BD3"));
        } //else ((View) holder.mTextView.getParent()).setBackgroundColor(Color.parseColor("#FFF"));
*/
    }

    @Override
    public int getItemCount() {
        return genresList.size();
    }
}
