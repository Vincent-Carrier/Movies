package com.example.android.popularmovies.detailsscreen;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.android.popularmovies.App;
import com.example.android.popularmovies.TheMovieDbApi;
import com.example.android.popularmovies.pojos.TrailersResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends AndroidViewModel {

	@Inject
	TheMovieDbApi theMovieDbApi;

	public DetailsViewModel(Application app) {
		super(app);
		((App) app).getNetComponent().inject(this);
	}

	Observable<TrailersResponse> requestTrailers(int movieId) {
		return theMovieDbApi.fetchTrailersResponse(String.valueOf(movieId))
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
