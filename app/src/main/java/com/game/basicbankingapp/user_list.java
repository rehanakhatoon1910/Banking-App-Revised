package com.game.basicbankingapp;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class user_list extends AppCompatActivity {
    List<cstomr_model> cstomrmodelList_showlist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    adapter_ClientList adapter;

    String phonenumber;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allusers_list);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        cstomrmodelList_showlist.clear();
        Cursor cursor = new myDBHelper(this).readalldata();
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            cstomr_model cstomrmodel = new cstomr_model(cursor.getString(0), cursor.getString(1), price);
            cstomrmodelList_showlist.add(cstomrmodel);
        }

        adapter = new adapter_ClientList(user_list.this, cstomrmodelList_showlist);
        mRecyclerView.setAdapter(adapter);

    }

    public void nextActivity(int position) {
        phonenumber = cstomrmodelList_showlist.get(position).getPhoneno();
        Intent intent = new Intent(user_list.this, user_data.class);
        intent.putExtra("phonenumber",phonenumber);
        mainBtn1(view,intent);

    }
    public void mainBtn1(View view,Intent intent){
        startActivity(intent);
        Animatoo.animateFade(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_history){
            mainBtn2(view);
        }
        return super.onOptionsItemSelected(item);
    }
    public void mainBtn2(View view){
        startActivity(new Intent(user_list.this, history_list.class));
        Animatoo.animateZoom(this);
    }
}