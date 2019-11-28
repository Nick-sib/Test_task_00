package ltd.pekudlyawas.testjson;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

/*    public static final String LOG = "myLOG";
    public static final String baseJSONUrl = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/";



    private RecyclerView gRecyclerView, fRecyclerView;
    private genresAdapter gAdapter;
    private filmsAdapter fAdapter;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment_Films_List fragment = Fragment_Films_List.newInstance("example text ", "example text ");

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

 /*       //Creating Adapters
        //GENRES
        gRecyclerView = findViewById(R.id.rv_Genres);
        gRecyclerView.setHasFixedSize(true);
        gAdapter = new genresAdapter();

        gRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        gRecyclerView.setAdapter(gAdapter);

        gAdapter.setOnItemClickListener(new genresAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ArrayList<Integer> ids) {
                gAdapter.notifyDataSetChanged();
                fAdapter.applyFilter(ids);

            }
        });



        //Loading films data
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseJSONUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<FILMDATA> call = jsonPlaceHolderApi.getFilms();

        call.enqueue(new Callback<FILMDATA>() {
            @Override
            public void onResponse(Call<FILMDATA> call, Response<FILMDATA> response) {

                if (!response.isSuccessful()) {
                    Log.d(LOG,"onResponse: " + response.code());
                    Toast.makeText(MainActivity.this, "Response is An Successful " +
                            response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                FILMDATA posts = response.body();
//TODO Добалять в адаптер с приенением фильтра

                gAdapter.setData(posts.films);

                if (gAdapter != null)
                    gAdapter.notifyDataSetChanged();

                //FILMS
                fRecyclerView = findViewById(R.id.rv_Films);
                fRecyclerView.setHasFixedSize(true);
                fAdapter = new filmsAdapter(MainActivity.this, posts.films);

                fRecyclerView.setAdapter(fAdapter);

                AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(MainActivity.this, 250);
                fRecyclerView.setLayoutManager(layoutManager);
                fAdapter.setOnItemClickListener(new filmsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.d(LOG, "position = " + position);
                    }
                });
            }

            @Override
            public void onFailure(Call<FILMDATA> call, Throwable t) {
                Log.d(LOG, "onFailure " + t.getMessage());
                Toast.makeText(MainActivity.this, "Response is An Successful " +
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/


    }
}
