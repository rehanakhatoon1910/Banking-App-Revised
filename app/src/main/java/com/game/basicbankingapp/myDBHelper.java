package com.game.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDBHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public myDBHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(7675123980,'John',472.00,'john.02@gmail.com','XXXXXXXXXXXX1231','ABC09876541')");
        db.execSQL("insert into user_table values(7345417538,'Nick',582.67,'nick.143@gmail.com','XXXXXXXXXXXX5712','BCA782638423')");
        db.execSQL("insert into user_table values(8456584784,'Angelina',1359.56,'angeli.2404@gmail.com','XXXXXXXXXXXX3417','CAB87654325')");
        db.execSQL("insert into user_table values(6567886786,'Christopher',189.01,'christo.05@gmail.com','XXXXXXXXXXXX4124','ABC76543213')");
        db.execSQL("insert into user_table values(9678901234,'Ellie',1603.48,'ellie.1506@gmail.com','XXXXXXXXXXXX2342','BCA65432108')");
        db.execSQL("insert into user_table values(8789012345,'Jeff',945.16,'jeff.1107@gmail.com','XXXXXXXXXXXX3459','CAB54321099')");
        db.execSQL("insert into user_table values(7890123456,'Briston',596.00,'briston.0809@gmail.com','XXXXXXXXXXXX4525','ABC43210984')");
        db.execSQL("insert into user_table values(9901234567,'Viele',857.22,'viele.0910@gmail.com','XXXXXXXXXXXX5232','BCA32109879')");
        db.execSQL("insert into user_table values(7012345678,'Mark',398.46,'mark.2000@gmail.com','XXXXXXXXXXXX3458','CAB21098766')");
        db.execSQL("insert into user_table values(8234567809,'Peter',273.90,'peter.01@gmail.com','XXXXXXXXXXXX4560','ABC10987650')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
