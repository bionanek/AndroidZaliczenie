
package com.example.artur.timeger;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;


/**
 * Created by bionanek on 25-11-2016.
 */


public class DataBase
{
    public static SQLiteDatabase db;

    public static void createDatabase(Context con)
    {
        db = con.openOrCreateDatabase(con.getString(R.string.database_name), con.MODE_PRIVATE, null);
        try
        {
            db.execSQL(con.getString(R.string.create_database));
            Log.e("Database status", "Database has been created");
        }
        catch (Exception ex)
        {
            Log.e("Database status", "Database has not been created. Exception:" + ex.toString());
        }
    }

    public static void deleteDatabase(Context con)
    {
        con.deleteDatabase(con.getString(R.string.database_name));
    }


    public static void execSQL(String query, Context con)
    {
        try
        {
            db = con.openOrCreateDatabase(con.getString(R.string.database_name), con.MODE_PRIVATE, null);
            db.execSQL(query);
        }
        catch (Exception ex)
        {
            Log.e("execSQL error: ", ex.toString());
        }
    }


    /*
    public static SQLiteDatabase timegerDB = null;

    public static String DB_NAME = "Timeger.db";
    public static int DB_VERSION = 1;
    public static String TABLE_QUESTS = "quests";
    public static String TABLE_USER = "user";
    public static String DB_CREATE = "CREATE TABLE IF NOT EXISTS "+ TABLE_QUESTS +"(" +
            "_id INTEGER primary key autoincrement, " +
            "description VARCHAR not null, " +
            "quest_date VARCHAR," +
            "alarm_date VARCHAR," +
            "quest_place VARCHAR," +
            "type VARCHAR," +
            "comments VARCHAR" +
            "CREATE TABLE IF NOT EXISTS "+ TABLE_USER +"(" +
            "_id INTEGER primary key autoincrement, " +
            "login VARCHAR, " +
            "pass VARCHAR, " +
            "name VARCHAR, " +
            "surname VARCHAR);";

    public DataBase(Context con)
    {
        super(con, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DataBase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }

    public boolean createDatabase()
    {
        return true;
    }

    public boolean checkDatabase()
    {
        File database = getApplicationContext().getDatabasePath("Timeger.db");
        Log.e("Check", "istnieje chyba");
        return database.exists();
    }
    */
}

