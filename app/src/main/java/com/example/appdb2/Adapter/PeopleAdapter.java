package com.example.appdb2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdb2.MainActivity;
import com.example.appdb2.Model.People;
import com.example.appdb2.Others.ItemClickListener;
import com.example.appdb2.R;

import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {


   private ArrayList<People> alPeople;
   private Context context;
   private PeopleAdapterListener peopleAdapterListener;

    public PeopleAdapter(ArrayList<People> alPeople, Context context, PeopleAdapterListener peopleAdapterListener) {
        this.alPeople = alPeople;
        this.context = context;
        this.peopleAdapterListener = peopleAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.people_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(String.valueOf(alPeople.get(position).getName()));
        holder.txtAdd.setText(alPeople.get(position).getAdd()+"-"+alPeople.get(position).getPhone());
        if (String.valueOf(alPeople.get(position).getSex()).equals("Male")){
            holder.imgIc.setImageResource(R.drawable.male);
        }else holder.imgIc.setImageResource(R.drawable.female);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                peopleAdapterListener.click(v,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return alPeople.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtName, txtAdd;
        public ImageView imgIc;
        public ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAdd = itemView.findViewById(R.id.txtAdd);
            imgIc = itemView.findViewById(R.id.imgIc);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getAdapterPosition());

        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
    public interface PeopleAdapterListener {
        void click(View v, int position);
    }


}
