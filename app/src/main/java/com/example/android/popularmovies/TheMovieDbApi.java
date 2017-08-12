package com.example.android.popularmovies;


import com.example.android.popularmovies.pojos.TheMovieDbResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TheMovieDbApi {

	@GET("{sortingMethod}")
	Observable<TheMovieDbResponse> fetchTopMoviesResponse(@Path("sortingMethod") String sortingMethod);
}
