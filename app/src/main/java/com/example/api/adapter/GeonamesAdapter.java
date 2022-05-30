package com.example.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api.CountriesActivity;
import com.example.api.Geonames;
import com.example.api.MainActivity;
import com.example.api.R;
import com.example.api.api.ItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeonamesAdapter extends RecyclerView.Adapter<GeonamesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Geonames> array;

    public GeonamesAdapter(Context context, ArrayList<Geonames> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public GeonamesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull GeonamesAdapter.MyViewHolder holder, int position) {
        Geonames geonames = array.get(position);
        holder.tv_login_name.setText(geonames.countryName);
        Log.d("BBB", geonames.countryName);
        holder.tv_id.setText(geonames.capital);
        Glide.with(context).load("http://img.geonames.org/flags/x/" + geonames.countryCode.toLowerCase() + ".gif").into(holder.iv_avatar);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountriesActivity.class);
                intent.putExtra("chitiet", geonames);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_login_name, tv_id;
        ImageView iv_avatar;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_login_name = itemView.findViewById(R.id.tv_login_name);
            tv_id = itemView.findViewById(R.id.tv_id);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
