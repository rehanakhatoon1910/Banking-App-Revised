package com.game.basicbankingapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_ClientList extends RecyclerView.Adapter<view_holder> {

    user_list UserList;
    List<cstomr_model> cstomrmodelList;
    Context context;

    public adapter_ClientList(user_list userList, List<cstomr_model> cstomrmodelList) {
        this.UserList = userList;
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
                UserList.nextActivity(position);
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
}
