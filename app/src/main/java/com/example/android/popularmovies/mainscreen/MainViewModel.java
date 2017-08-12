package com.example.android.popularmovies.mainscreen;


import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.TheMovieDbApi;
import com.example.android.popularmovies.pojos.ApiKey;
import com.example.android.popularmovies.pojos.SortingMethod;
import com.example.android.popularmovies.pojos.TheMovieDbResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {

	private final TheMovieDbApi theMovieDbApi = createTheMovieDbApi();

	private SortingMethod sortingMethod = SortingMethod.popular;

	public MainViewModel() {
		super();
	}

	private TheMovieDbApi createTheMovieDbApi() {
		Retrofit retrofit = new Retrofit.Builder()
				.client(new OkHttpClient.Builder()
						/* Append the API key to every Retrofit request. If you downloaded this project
						 * from GitHub, you must replace the ApiKey.API_KEY constant with your own API key,
						 * which you can obtain from themoviedb.org */
						.addInterceptor(new Interceptor() {
							@Override
							public Response intercept(@NonNull Chain chain) throws IOException {
								Request original = chain.request();
								HttpUrl originalHttpUrl = original.url();

								HttpUrl url = originalHttpUrl.newBuilder()
										.addQueryParameter("api_key", ApiKey.API_KEY)
										.build();

								// Request customization: add request headers
								Request.Builder requestBuilder = original.newBuilder()
										.url(url);

								Request request = requestBuilder.build();
								return chain.proceed(request);
							}
						})
						.build())
				.baseUrl("http://api.themoviedb.org/3/movie/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();

		return retrofit.create(TheMovieDbApi.class);
	}

	Observable<TheMovieDbResponse> requestMovies() {
		return theMovieDbApi.fetchTopMoviesResponse(sortingMethod.toString())
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public SortingMethod getSortingMethod() {
		return sortingMethod;
	}

	public void setSortingMethod(SortingMethod sortingMethod) {
		this.sortingMethod = sortingMethod;
	}
}
