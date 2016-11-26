package com.example.artur.timeger.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.artur.timeger.model.Quest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by bionanek on 26-11-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Timeger";

    // Table Names
    private static final String TABLE_QUEST = "quest";
    private static final String TABLE_USER = "user";
    private static final String TABLE_TAG = "tag";
    private static final String TABLE_QUEST_TAG = "quest_tag";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // QUEST Table - column names
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_STATUS = "status";
    private static final String KEY_QUEST_DATE = "quest_date";
    private static final String KEY_ALARM_DATE = "alarm_date";
    private static final String KEY_QUEST_PLACE = "quest_place";
    private static final String KEY_COMMENTS = "comments";
    private static final String KEY_TYPE = "type";
    
    //USER Table - column names
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";

    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name";

    // NOTE_TAGS Table - column names
    private static final String KEY_QUEST_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";

    //Table create statements
    //Quest table create
    private static String CREATE_TABLE_QUEST =
                                    "CREATE TABLE IF NOT EXISTS "+TABLE_QUEST+"("   +
                                    KEY_ID + " INTEGER primary key autoincrement,"  +
                                    KEY_DESCRIPTION + " TEXT not null,"             +
                                    KEY_STATUS + " INTEGER,"                        +
                                    KEY_QUEST_DATE + " DATETIME,"                   +
                                    KEY_ALARM_DATE + " DATETIME,"                   +
                                    KEY_QUEST_PLACE + " TEXT,"                      +
                                    KEY_TYPE + " TEXT,"                             +
                                    KEY_COMMENTS + " TEXT"                          +
                                    KEY_CREATED_AT + " DATETIME);";

    //User table create
    private static String CREATE_TABLE_USER = 
                                    "CREATE TABLE IF NOT EXISTS "+TABLE_USER+"("    +
                                    KEY_ID + " INTEGER primary key autoincrement,"  +
                                    KEY_LOGIN + " TEXT,"                            +
                                    KEY_PASSWORD + " TEXT,"                         +
                                    KEY_NAME + " TEXT,"                             +
                                    KEY_SURNAME + " TEXT"                           +
                                    KEY_CREATED_AT + "DATETIME);";

    //Tag table create
    private static String CREATE_TABLE_TAG =
                                    "CREATE TABLE IF NOT EXISTS "+TABLE_TAG+"("     +
                                    KEY_ID + " INTEGER primary key autoincrement,"  +
                                    KEY_TAG_NAME + " TEXT,"                         +
                                    KEY_CREATED_AT + "DATETIME);";

    //Quest_Tag table create
    private static String CREATE_TABLE_QUEST_TAG =
                                    "CREATE TABLE IF NOT EXISTS "+TABLE_TAG+"("     +
                                    KEY_ID + " INTEGER primary key autoincrement,"  +
                                    KEY_QUEST_ID + " INTEGER,"                      +
                                    KEY_TAG_ID + " INTEGER);";

    public DatabaseHelper(Context con)
    {
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Creating tables
        try
        {
            db.execSQL(CREATE_TABLE_QUEST);
            db.execSQL(CREATE_TABLE_TAG);
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_QUEST_TAG);
        }
        catch (Exception ex)
        {
            Log.e("Error creating tables", ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try
        {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST_TAG);

            this.onCreate(db);
        }
        catch (Exception ex)
        {
            Log.e("Error upgrading db", ex.toString());
        }
    }

    //QUEST METHODS

    public long createQuest(Quest quest, long[] tag_ids)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, quest.getDescription());
        values.put(KEY_STATUS, quest.getStatus());
        values.put(KEY_QUEST_DATE, quest.getQuestDate());
        values.put(KEY_ALARM_DATE, quest.getAlarmDate());
        values.put(KEY_QUEST_PLACE, quest.getPlace());
        values.put(KEY_TYPE, quest.getType());
        values.put(KEY_COMMENTS, quest.getComments());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long quest_id = db.insert(TABLE_QUEST, null, values);

        // assigning tags to quest
        for (long tag_id : tag_ids)
        {
            createQuestTag(quest_id, tag_id);
        }

        return quest_id;
    }

    public Quest getQuest(long quest_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_QUEST +
                " WHERE " + KEY_ID + " = " + quest_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Quest quest = new Quest();
        quest.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        quest.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
        quest.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
        quest.setQuestDate((c.getString(c.getColumnIndex(KEY_QUEST_DATE))));
        quest.setAlarm((c.getString(c.getColumnIndex(KEY_ALARM_DATE))));
        quest.setPlace((c.getString(c.getColumnIndex(KEY_QUEST_PLACE))));
        quest.setType((c.getString(c.getColumnIndex(KEY_TYPE))));
        quest.setComments((c.getString(c.getColumnIndex(KEY_COMMENTS))));
        quest.setCreatedAt((c.getString(c.getColumnIndex(KEY_CREATED_AT))));

        return quest;
    }

    public long createQuestTag(long quest_id, long tag_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUEST_ID, quest_id);
        values.put(KEY_TAG_ID, tag_id);
        values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_QUEST_TAG, null, values);

        return id;
    }

    public List<Quest> getAllQuests()
    {
        List<Quest> quests = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUEST;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through rows -> add them to list
        if(c.moveToFirst())
        {
            do
            {
                Quest quest = new Quest();
                quest.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                quest.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                quest.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
                quest.setQuestDate((c.getString(c.getColumnIndex(KEY_QUEST_DATE))));
                quest.setAlarm((c.getString(c.getColumnIndex(KEY_ALARM_DATE))));
                quest.setPlace((c.getString(c.getColumnIndex(KEY_QUEST_PLACE))));
                quest.setType((c.getString(c.getColumnIndex(KEY_TYPE))));
                quest.setComments((c.getString(c.getColumnIndex(KEY_COMMENTS))));
                quest.setCreatedAt((c.getString(c.getColumnIndex(KEY_CREATED_AT))));

                //ad this row to list
                quests.add(quest);

            }while(c.moveToNext());
        }
        return quests;
    }

    private String getDateTime()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }
}
