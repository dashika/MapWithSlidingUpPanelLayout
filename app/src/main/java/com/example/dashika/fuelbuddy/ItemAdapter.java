package com.example.dashika.fuelbuddy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dashika on 30/05/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final List<Element> mValues;
    Context context;

    public ItemAdapter(List<Element> items, Context context) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.init();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        Element mItem;
        @BindView(R.id.twCost)
        TextView twCost;
        @BindView(R.id.twTime)
        TextView twTime;
        @BindView(R.id.imgLogo)
        ImageView imgLogo;
        @BindView(R.id.twName)
        TextView twName;
        @BindView(R.id.twAddress)
        TextView twAddress;
        @BindView(R.id.twDistance)
        TextView twDistance;

        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this,view);
        }

        void init() {
            if (mItem == null) return;

            twAddress.setText(mItem.getAddress());
            twCost.setText(mItem.getCoast() + " \u20BD");
            twDistance.setText(mItem.getDistance() + " км");
            twName.setText(mItem.getName());
            String time = DateUtils.getRelativeTimeSpanString(mItem.getTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            twTime.setText(time);


        }
    }

}
