package com.example.android.popularmovies;


import com.example.android.popularmovies.pojos.MoviesResponse;
import com.example.android.popularmovies.pojos.ReviewsResponse;
import com.example.android.popularmovies.pojos.TrailersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TheMovieDbApi {

	@GET("{sortingMethod}")
	Observable<MoviesResponse> fetchMoviesResponse(@Path("sortingMethod") String sortingMethod);

	@GET("{id}/videos")
	Observable<TrailersResponse> fetchTrailersResponse(@Path("id") String movieId);

	@GET("{id}/videos")
	Observable<ReviewsResponse> fetchReviewsResponse(@Path("id") String movieId);
}
