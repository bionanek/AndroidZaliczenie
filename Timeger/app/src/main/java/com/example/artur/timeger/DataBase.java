package com.example.artur.timeger;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;

/**
 * Created by bionanek on 25-11-2016.
 */

public class DataBase extends Activity
{
    public static SQLiteDatabase timegerDB = null;

    public boolean createDatabase()
    {
        try
        {
            timegerDB = this.openOrCreateDatabase("Timeger.db", MODE_PRIVATE, null);
            timegerDB.execSQL("CREATE TABLE IF NOT EXISTS quests(" +
                    "_id INTEGER primary key autoincrement, " +
                    "description VARCHAR not null, " +
                    "quest_date VARCHAR," +
                    "alarm_date VARCHAR," +
                    "quest_place VARCHAR," +
                    "type VARCHAR," +
                    "comments VARCHAR);");
            timegerDB.execSQL("CREATE TABLE IF NOT EXISTS user (" +
                    "_id INTEGER primary key autoincrement, " +
                    "login VARCHAR, " +
                    "pass VARCHAR, " +
                    "name VARCHAR, " +
                    "surname VARCHAR);");

        }
        catch (Exception ex)
        {
            Log.e("Timeger error", "Error Creating Database :(");
            return false;
        }
        return true;
    }

    public boolean checkDatabase()
    {
        File database = getApplicationContext().getDatabasePath("Timeger.db");
        return database.exists();
    }
}
