package com.aknik.pdfbook;

//had tkhrbi9 tayafiche lina lm3lomat dyol Care  f lwajiha dyalna  View

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolider>{

    Context context ;
    ArrayList<Upload> pdf ;
    public Adapter(Context c , ArrayList<Upload> pp){
        context = c ;
        pdf = pp ;

    }
    @NonNull
    @Override
    public MyViewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolider(LayoutInflater.from(context).inflate(R.layout.activity_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolider holder, int position) {
        holder.tvName.setText(pdf.get(position).getName());

        //Picasso.get().load(care.get(position).getPhotoCare()).into(holder.tvImage);
    }

    @Override
    public int getItemCount() {
        return pdf.size();
    }

    class MyViewHolider extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView tvImage ;
        public MyViewHolider(View itemview){
            super(itemview);
            tvName=(TextView)itemview.findViewById(R.id.tvName);
            tvImage=(ImageView) itemview.findViewById(R.id.tvImage);

            // tvImage=(ImageView)itemview.findViewById(R.id.tvimg);
        }
    }


}






