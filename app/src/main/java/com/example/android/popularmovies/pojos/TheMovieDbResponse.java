
package com.example.android.popularmovies.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TheMovieDbResponse implements Parcelable
{
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("results")
    @Expose
    public List<Movie> movies = null;
    public final static Parcelable.Creator<TheMovieDbResponse> CREATOR = new Creator<TheMovieDbResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TheMovieDbResponse createFromParcel(Parcel in) {
            TheMovieDbResponse instance = new TheMovieDbResponse();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.movies, (Movie.class.getClassLoader()));
            return instance;
        }

        public TheMovieDbResponse[] newArray(int size) {
            return (new TheMovieDbResponse[size]);
        }

    }
    ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(movies);
    }

    public int describeContents() {
        return  0;
    }

}
