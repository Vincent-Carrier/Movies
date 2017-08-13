package com.example.android.popularmovies.di;

import android.support.annotation.NonNull;

import com.example.android.popularmovies.TheMovieDbApi;
import com.example.android.popularmovies.pojos.ApiKey;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetModule {

	@Provides
	@Singleton
	TheMovieDbApi provideTheMovieDbApi(Retrofit retrofit) {
		return retrofit.create(TheMovieDbApi.class);
	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(OkHttpClient client) {
		return new Retrofit.Builder()
				.client(client)
				.baseUrl("http://api.themoviedb.org/3/movie/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}

	@Provides
	@Singleton
	OkHttpClient provideOkHttpClient() {
		return new OkHttpClient.Builder()
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
				.build();
	}
}
