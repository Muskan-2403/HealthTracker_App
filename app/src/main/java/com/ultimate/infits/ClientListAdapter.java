package com.ultimate.infits;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientListHolder>{
    Context ct;
    List<List_Clients> obj;
   // Selecteditem selecteditem;
    ClientListAdapter(Context ct,List<List_Clients> obj){
        this.ct = ct;
        this.obj=obj;
        //this.selecteditem=selecteditem;
    }

    @NonNull
    @Override
    public ClientListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.client_list_layout,parent,false);
        return new ClientListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientListHolder holder, int position) {
        List_Clients pos= obj.get(position);
        if (pos.isStatus()){
            holder.status_btn.setText("Online");
        }
        if (position == 2 || position == 7){
            Drawable buttonDrawable = holder.status_btn.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.RED);
            holder.status_btn.setText("Offline");
            holder.status_btn.setBackground(buttonDrawable);
        }
        File imgFile = new File(pos.getClient_list_image());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.img.setImageBitmap(myBitmap);

        }
        holder.plan.setText(pos.getPlan_type());
        holder.name.setText(pos.getClient_list_name());
        holder.startdate.setText(pos.getClient_list_startdate());
        holder.enddate.setText(pos.getClient_list_enddate());
        holder.itemView.setOnClickListener(v->{
                ct.startActivity(new Intent(ct,ClientDetails.class));
        });
    }

    @Override
    public int getItemCount() {
        return obj.size();
    }
   // public interface Selecteditem{
     //   void selecteditem(List_Clients obj);
   // }

    public class ClientListHolder extends RecyclerView.ViewHolder{

        Button status_btn;
        ImageView img;
        TextView name,plan,startdate,enddate;
        public ClientListHolder(@NonNull View itemView) {
            super(itemView);
            status_btn = itemView.findViewById(R.id.status);
            img= itemView.findViewById(R.id.client_list_profile_pic);
            name=itemView.findViewById(R.id.client_list_profile_name);
            startdate=itemView.findViewById(R.id.client_list_startdate);
            plan=itemView.findViewById(R.id.client_list_plan_name);
            enddate=itemView.findViewById(R.id.client_list_enddate);
           // itemView.setOnClickListener(new View.OnClickListener() {
            //    @Override
             //   public void onClick(View v) {
                //    selectedItem.selecteditem(obj.get(getAdapterPosition()));
             //   }
          //  });

        }
    }
}
