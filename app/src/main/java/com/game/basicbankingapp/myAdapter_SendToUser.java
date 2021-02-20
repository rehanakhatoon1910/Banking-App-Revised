package com.game.basicbankingapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter_SendToUser extends RecyclerView.Adapter<view_holder> {

    send2user SendtoUser;
    List<cstomr_model> cstomrmodelList;
    Context context;

    public myAdapter_SendToUser(send2user sentoUser, List<cstomr_model> cstomrmodelList) {
        this.SendtoUser = sentoUser;
        this.cstomrmodelList = cstomrmodelList;
    }

    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent, false);

        view_holder viewholder = new view_holder(itemView);
        viewholder.setOnClickListener(new view_holder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SendtoUser.selectuser(position);
            }
        });

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {
        holder.mName.setText(cstomrmodelList.get(position).getName());
        holder.mPhonenumber.setText(cstomrmodelList.get(position).getPhoneno());
        holder.mBalance.setText(cstomrmodelList.get(position).getBalance());
    }

    @Override
    public int getItemCount() {
        return cstomrmodelList.size();
    }

    public void setFilter(ArrayList<cstomr_model> newList){
        cstomrmodelList = new ArrayList<>();
        cstomrmodelList.addAll(newList);
        notifyDataSetChanged();
    }
}
