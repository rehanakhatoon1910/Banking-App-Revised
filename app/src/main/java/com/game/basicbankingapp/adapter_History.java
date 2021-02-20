package com.game.basicbankingapp;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_History extends RecyclerView.Adapter<view_holder> {

    history_list HistoryList;
    List<cstomr_model> cstomrmodelList;
    Context context;

    TextView mTransc_status;

    public adapter_History(history_list historyList, List<cstomr_model> cstomrmodelList) {
        this.HistoryList = historyList;
        this.cstomrmodelList = cstomrmodelList;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trnsfr_historylist, parent, false);

        mTransc_status = itemView.findViewById(R.id.transaction_status);

        view_holder viewholder = new view_holder(itemView);
        viewholder.setOnClickListener(new view_holder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.mName1.setText(cstomrmodelList.get(position).getName1());
        holder.mName2.setText(cstomrmodelList.get(position).getName2());
        holder.mBalance.setText(cstomrmodelList.get(position).getBalance());
        holder.mDate.setText(cstomrmodelList.get(position).getDate());
        holder.mTransc_status.setText(cstomrmodelList.get(position).getTransaction_status());

        if(cstomrmodelList.get(position).getTransaction_status().equals("Failed!!!")){
            holder.mTransc_status.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.mTransc_status.setTextColor(Color.parseColor("#3700B3"));
        }
    }

    @Override
    public int getItemCount() {
        return cstomrmodelList.size();
    }
}