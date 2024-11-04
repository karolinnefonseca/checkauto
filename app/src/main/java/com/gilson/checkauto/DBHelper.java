package com.gilson.checkauto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "checkauto";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_CARRO = "CREATE TABLE carro(" +
            " id INTEGER PRIMARY KEY  AUTOINCREMENT," +
            " marca TEXT NOT NULL, " +
            " modelo TEXT NOT NULL, "+
            " ano INTEGER NOT NULL);";

    private static final String DROP_TABLE_CARRO = "DROP TABLE carro ";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_CARRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE_CARRO);
        onCreate(db);
    }
}
