package com.ultimate.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class add_newAdapter extends RecyclerView.Adapter<add_newAdapter.add_newHolder>{

    Context ct;
    List<add_new> obj;

    public add_newAdapter(Context ct, List<add_new> obj) {
        this.ct = ct;
        this.obj = obj;
    }

    @NonNull
    @Override
    public add_newHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.add_new2,parent,false);
        return new add_newHolder(view).linkAdpter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull add_newHolder holder, int position) {
        add_new pos = obj.get(position);

        holder.name.setText(pos.getName());
        holder.quantity.setText(pos.getQuantity());
        holder.amount.setText(pos.getQuantity());

    }

    @Override
    public int getItemCount() {
        return obj.size();
    }

    public class add_newHolder extends RecyclerView.ViewHolder {
        EditText name,quantity,amount;
        ImageView removeItem;
        add_newAdapter add;
        public add_newHolder(View view) {
            super(view);
            name = view.findViewById(R.id.food_name);
            quantity = view.findViewById(R.id.food_quantity);
            amount = view.findViewById(R.id.food_amount);
            removeItem = view.findViewById(R.id.remove_item);
            removeItem.setOnClickListener(v->{
                add.obj.remove(getAbsoluteAdapterPosition());
                add.notifyItemRemoved(getAbsoluteAdapterPosition());
            });
        }
        public add_newHolder linkAdpter(add_newAdapter add){
            this.add = add;
            return this;
        }
    }
}
