
package com.example.android.popularmovies.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersResponse implements Parcelable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("reviews")
    @Expose
    public List<Trailer> trailers = null;
    public final static Creator<TrailersResponse> CREATOR = new Creator<TrailersResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TrailersResponse createFromParcel(Parcel in) {
            TrailersResponse instance = new TrailersResponse();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.trailers, (Trailer.class.getClassLoader()));
            return instance;
        }

        public TrailersResponse[] newArray(int size) {
            return (new TrailersResponse[size]);
        }

    }
    ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(trailers);
    }

    public int describeContents() {
        return  0;
    }

}
