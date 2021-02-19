package com.game.basicbankingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog2 {

    private Activity activity;
    private AlertDialog dialog;

    LoadingDialog2(Activity myActivity){
        activity = myActivity;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater =activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.custom_dialog2, null));
        builder.setCancelable(false);

        dialog = builder.create();
        builder.show();
    }

    void dismissDialog(){
        dialog.dismiss();
    }
}
