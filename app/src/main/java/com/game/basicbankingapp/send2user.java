package com.game.basicbankingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class send2user extends AppCompatActivity {

    List<cstomr_model> cstomrmodelList_sendtouser = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    myAdapter_SendToUser adapter;

    String phonenumber, name, currentamount, transferamount, remainingamount;
    String selectuser_phonenumber, selectuser_name, selectuser_balance, date;
    ImageView tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendtouser);

        mRecyclerView = findViewById(R.id.recyclerview);
        tick = findViewById(R.id.tick);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        date = simpleDateFormat.format(calendar.getTime());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            phonenumber = bundle.getString("phonenumber");
            name = bundle.getString("name");
            currentamount = bundle.getString("currentamount");
            transferamount = bundle.getString("transferamount");
            showData(phonenumber);
        }
    }
    private void showData(String phonenumber) {
        cstomrmodelList_sendtouser.clear();
        Log.d("DEMO",phonenumber);
        Cursor cursor = new myDBHelper(this).readselectuserdata(phonenumber);
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            cstomr_model cstomrmodel = new cstomr_model(cursor.getString(0), cursor.getString(1), price);
            cstomrmodelList_sendtouser.add(cstomrmodel);
        }

        adapter = new myAdapter_SendToUser(send2user.this, cstomrmodelList_sendtouser);
        mRecyclerView.setAdapter(adapter);
    }
    public void selectuser(int position) {
        selectuser_phonenumber = cstomrmodelList_sendtouser.get(position).getPhoneno();
        Cursor cursor = new myDBHelper(this).readparticulardata(selectuser_phonenumber);
        while(cursor.moveToNext()) {
            selectuser_name = cursor.getString(1);
            selectuser_balance = cursor.getString(2);
            Double Dselectuser_balance = Double.parseDouble(selectuser_balance);
            Double Dselectuser_transferamount = Double.parseDouble(transferamount);
            Double Dselectuser_remainingamount = Dselectuser_balance + Dselectuser_transferamount;

            new myDBHelper(this).insertTransferData(date, name, selectuser_name, transferamount, "Success");
            new myDBHelper(this).updateAmount(selectuser_phonenumber, Dselectuser_remainingamount.toString());
            calculateAmount();
            final LoadingDialog2 loadingDialog = new LoadingDialog2(send2user.this);
            loadingDialog.startLoadingDialog();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingDialog.dismissDialog();
                    Toast.makeText(send2user.this, "Transaction Successfully Completed!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(send2user.this, user_list.class));
                    finish();
                }
            },3000);

        }
    }
    private void calculateAmount() {
        Double Dcurrentamount = Double.parseDouble(currentamount);
        Double Dtransferamount = Double.parseDouble(transferamount);
        Double Dremainingamount = Dcurrentamount - Dtransferamount;
        remainingamount = Dremainingamount.toString();
        new myDBHelper(this).updateAmount(phonenumber, remainingamount);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(send2user.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new myDBHelper(send2user.this).insertTransferData(date, name, "Not selected", transferamount, "Failed");
                        Toast.makeText(send2user.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(send2user.this, user_list.class));
                        finish();
                    }
                }).setNegativeButton("No", null);
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<cstomr_model> newList = new ArrayList<>();
                for(cstomr_model cstomrmodel : cstomrmodelList_sendtouser){
                    String name = cstomrmodel.getName().toLowerCase();
                    if(name.contains(newText)){
                        newList.add(cstomrmodel);
                    }
                }
                adapter.setFilter(newList);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
