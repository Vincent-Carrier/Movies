package com.example.android.popularmovies.pojos;


public enum PosterWidth {
	XXSMALL("92"), XSMALL("154"), SMALL("185"), MEDIUM("342"),
	LARGE("500"), XLARGE("780"), ORIGINAL("original");

	public final String width;

	public final static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w";

	PosterWidth(String width) {
		this.width = width;
	}
}
