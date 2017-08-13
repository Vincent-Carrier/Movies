package com.example.android.popularmovies.detailsscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.pojos.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

	private List<Trailer> trailers;
	private Context context;

	public TrailerAdapter(Context context) {
		this.context = context;
	}

	public void setTrailers(List<Trailer> trailers) {
		this.trailers = trailers;
	}

	class TrailerViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.trailer_name)
		TextView trailerName;

		TrailerViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
	@Override
	public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.trailer_list_item, parent, false);
		return new TrailerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TrailerViewHolder holder, int position) {
		holder.trailerName.setText(trailers.get(position).name);
	}

	@Override
	public int getItemCount() {
		return trailers != null ? trailers.size() : 0;
	}
}
