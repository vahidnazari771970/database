package com.example.vahid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databasemanager extends SQLiteOpenHelper {

    private static final String DatabaseName = "myinfo.db";
    private static final int version = 1;

    private static final String tablename ="tbl_person";
    private static final String Did ="id";
    private static final String Dname="name";
    private static final String Dfamily ="family";



    public databasemanager(Context cnt) {
        super(cnt, DatabaseName, null, version);
        Log.i("mahdi", "Database created");

    }

    @Override
    public void onCreate(SQLiteDatabase cdb) {


        String cQuery = "CREATE TABLE " + tablename + "(" + Did + " INTEGER  PRIMARY KEY," + Dname + " TEXT , " + Dfamily + " TEXT );";
        cdb.execSQL(cQuery);
        Log.i("mahdi", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertperson(person iprs){

        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();

        icv.put(Did,iprs.Pid);
        icv.put(Dname,iprs.Pname);
        icv.put(Dfamily,iprs.Pfamily);

        idb.insert(tablename,null,icv);
        idb.close();

        Log.i("mahdi", "insert person method");






    }

    public person getperson(String gid){

        person gprs = new person();
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gquery = "Select * From " + tablename ;
        Cursor gcur = gdb.rawQuery(gquery,null);
        if (gcur.moveToFirst()){

            gprs.Pname = gcur.getString(1);
            gprs.Pfamily = gcur.getString(2);
        }

        Log.i("mahdi", "get person method");
        return gprs;

    }
    public void updateperson(person uprs){
        SQLiteDatabase udb = this.getWritableDatabase();
        ContentValues ucv = new ContentValues();
        ucv.put(Dname,uprs.Pname);
        ucv.put(Dfamily,uprs.Pfamily);
        udb.update(tablename,ucv,Did + "=?",new String[]{String.valueOf(uprs.Pid)});
        Log.i("mahdi", "update person method");


    }
    public  Boolean deleteperson(person dprs){
        SQLiteDatabase ddb = this.getWritableDatabase();
        int dresult = ddb.delete(tablename,Did + "=?",new String[]{String.valueOf(dprs.Pid)});
        Log.i("mahdi", "delete person method");
        if (dresult== 0)
            return false;
        else
            return true;



    }



}
