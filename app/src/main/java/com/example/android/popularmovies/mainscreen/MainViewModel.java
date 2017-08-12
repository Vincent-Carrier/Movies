package com.example.android.popularmovies.mainscreen;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.android.popularmovies.App;
import com.example.android.popularmovies.TheMovieDbApi;
import com.example.android.popularmovies.pojos.SortingMethod;
import com.example.android.popularmovies.pojos.TheMovieDbResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

	@Inject TheMovieDbApi theMovieDbApi;

	private SortingMethod sortingMethod = SortingMethod.popular;

	public MainViewModel(Application app) {
		super(app);
		((App) app).getNetComponent().inject(this);
	}

	Observable<TheMovieDbResponse> requestMovies() {
		return theMovieDbApi.fetchTopMoviesResponse(sortingMethod.toString())
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread());
	}

	SortingMethod getSortingMethod() {
		return sortingMethod;
	}

	void setSortingMethod(SortingMethod sortingMethod) {
		this.sortingMethod = sortingMethod;
	}
}
