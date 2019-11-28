package ltd.pekudlyawas.testjson.jsonData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("films.json")
    Call<FILMDATA> getFilms();
}
