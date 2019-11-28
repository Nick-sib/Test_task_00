package ltd.pekudlyawas.testjson.jsonData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FILMDATA {

    public ArrayList<Film> films;

    public static class Film {
        @SerializedName("id")
        public int filmId;
        @SerializedName("localized_name")
        public String filmLocalized_Name;
        @SerializedName("name")
        public String filmName;
        @SerializedName("year")
        public int filmYear;
        @SerializedName("rating")
        public float filmRating;
        @SerializedName("image_url")
        public String filmImage_url;
        @SerializedName("Description")
        public String filmDescription;
        @SerializedName("genres")
        public String[] filmGenres;
    }

}
