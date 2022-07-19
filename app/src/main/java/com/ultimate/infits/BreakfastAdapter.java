package com.ultimate.infits;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastViewHolder> {
    Context con;
    //    OnFoodItemClickListener onFoodItemClickListener;
    List<List_Food> obj;
    int color;
    public BreakfastAdapter(List<List_Food> obj, Context context,int color) {
        this.obj = obj;
        this.con = context;
        this.color = color;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.breakfast_recepies,parent,false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {
        List_Food pos= obj.get(position);
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(con,FoodDetails.class);
                in.putExtra("Food Name",pos.getFoodName());
                con.startActivity(in);
            }
        });
        holder.br_card.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return obj.size();
    }

    protected class BreakfastViewHolder extends RecyclerView.ViewHolder {
        TextView name,time,serving;
        ImageView image;
        ImageButton next;
        CardView br_card;
        public BreakfastViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.br_food_name);
            time=view.findViewById(R.id.br_time);
            serving=view.findViewById(R.id.br_serving);
            image=view.findViewById(R.id.br_image);
            next=view.findViewById(R.id.br_next);
            br_card = view.findViewById(R.id.br_card);
        }
    }
}