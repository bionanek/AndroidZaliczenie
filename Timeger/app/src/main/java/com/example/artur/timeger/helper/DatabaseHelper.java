package com.example.artur.timeger.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.artur.timeger.model.Quest;
import com.example.artur.timeger.model.Tag;
import com.example.artur.timeger.model.User;

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
    private static final String KEY_ID = "_id";
    private static final String KEY_CREATED_AT = "created_at";

    // QUEST Table - column names
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_STATUS = "status";
    private static final String KEY_PRIORITY = "priority";
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
    private static final String KEY_EMAIL = "email";

    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name";

    // NOTE_TAGS Table - column names
    private static final String KEY_QUEST_ID = "quest_id";
    private static final String KEY_TAG_ID = "tag_id";

    //Table create statements
    //Quest table create
    private static String CREATE_TABLE_QUEST =
                                    "CREATE TABLE IF NOT EXISTS "+TABLE_QUEST+"("   +
                                    KEY_ID + " INTEGER primary key autoincrement,"  +
                                    KEY_DESCRIPTION + " TEXT not null,"             +
                                    KEY_STATUS + " INTEGER,"                        +
                                    KEY_PRIORITY + " TEXT,"                         +
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
                                    KEY_SURNAME + " TEXT,"                          +
                                    KEY_EMAIL + " TEXT,"                            +
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

        ContentValues values = this.setQuestObjectValues(quest);

        // insert row
        long quest_id = db.insert(TABLE_QUEST, null, values);

        // assigning tags to quest
        for (long tag_id : tag_ids)
        {
            createQuestTag(quest_id, tag_id);
        }

        return quest_id;
    }

    public Quest getQuestById(long quest_id)
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

        c.close();

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
        Cursor cursor = db.rawQuery(selectQuery, null);
        quests = this.getListOfQuests(cursor);
        cursor.close();

        return quests;
    }

    public List<Quest> getAllQuestsByTag(String tagName)
    {
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST + " qst, "
                + TABLE_TAG + " tg, " + TABLE_QUEST_TAG + " qt WHERE tg."
                + KEY_TAG_NAME + " = '" + tagName + "'" + " AND tg." + KEY_ID
                + " = " + "qt." + KEY_TAG_ID + " AND qst." + KEY_ID + " = "
                + "qt." + KEY_QUEST_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Quest> quests = this.getListOfQuests(cursor);
        cursor.close();

        return quests;
    }

    public int updateQuest(Quest quest)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = this.setQuestObjectValues(quest);

        return db.update(TABLE_QUEST, values, KEY_ID + " = ?",
                new String[] {String.valueOf(quest.getId())} );

    }

    public void deleteQuest(int questId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUEST, KEY_ID + " = ?",
                new String[] {String.valueOf(questId)} );
    }

    // TAG METHODS
    public long createTag(Tag tag)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TAG_NAME, tag.getTagName());
        values.put(KEY_CREATED_AT, getDateTime());

        //insert row and return id;
        return db.insert(TABLE_TAG, null, values);
    }

    public List<Tag> getAllTags()
    {
        List<Tag> tags = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TAG;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst())
        {
            do
            {
                Tag t = new Tag();
                t.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                t.setTagName(c.getString(c.getColumnIndex(KEY_TAG_NAME)));

                tags.add(t);
            }while(c.moveToNext());
        }
        c.close();
        return tags;
    }

    public int updateTag(Tag tag)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG_NAME, tag.getTagName());

        return db.update(TABLE_TAG, values, KEY_ID + " = ?",
                new String[] { String.valueOf(tag.getId()) });
    }

    public void deleteTag(Tag tag, boolean deleteAllTagQuests)
    {
        SQLiteDatabase db = getWritableDatabase();

        //check if quests udner this tag should be deleted too
        if(deleteAllTagQuests)
        {
            List<Quest> allTagQuests = getAllQuestsByTag(tag.getTagName());

            // delete all quests
            for(Quest quest : allTagQuests)
            {
                deleteQuest(quest.getId());
            }
        }
        //delete the tag
        db.delete(TABLE_TAG, KEY_ID + " = ?",
                new String[] { String.valueOf(tag.getId()) });
    }

    // QUEST_TAG TABLE METHODS
    public long createQuestTag(int questId, int tagId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUEST_ID, questId);
        values.put(KEY_TAG_ID, tagId);
        values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_QUEST_TAG, null, values);

        return id;
    }

    // following method will remove the tag assigned to a quest
    public void deleteToDoTag(long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_QUEST, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public int updateQuestTag(long questId, long tagId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG_ID, tagId);

        return db.update(TABLE_QUEST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(questId) });
    }

    //USER TABLE METHODS
    public long createUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = this.setUserObjectValues(user);

        return db.insert(TABLE_USER, null, values);
    }

    public void deleteUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public long updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = this.setUserObjectValues(user);

        return db.update(TABLE_TAG, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public User getUserById(long userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_USER +
                " WHERE " + KEY_ID + " = " + userID;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        User user = new User();

        user.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        user.setLogin(c.getString(c.getColumnIndex(KEY_LOGIN)));
        user.setPass(c.getString(c.getColumnIndex(KEY_PASSWORD)));
        user.setName(c.getString(c.getColumnIndex(KEY_NAME)));
        user.setSurname(c.getString(c.getColumnIndex(KEY_SURNAME)));
        user.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));

        c.close();

        return user;
    }


    // CLOSING DATABASE CONNECTION
    public void closeDB()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }

    // helpers classes
    private ContentValues setUserObjectValues(User user)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN, user.getLogin());
        values.put(KEY_PASSWORD, user.getPass());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurname());
        values.put(KEY_EMAIL, user.getEmail());
        return values;
    }

    private String getDateTime()
    {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }

    private List<Quest> getListOfQuests(Cursor c)
    {
        List<Quest> quests = new ArrayList<>();
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

                //adds this row to list
                quests.add(quest);

            }while(c.moveToNext());
        }
        c.close();
        return quests;
    }

    private ContentValues setQuestObjectValues(Quest quest)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, quest.getDescription());
        values.put(KEY_STATUS, quest.getStatus());
        values.put(KEY_QUEST_DATE, quest.getQuestDate());
        values.put(KEY_ALARM_DATE, quest.getAlarmDate());
        values.put(KEY_QUEST_PLACE, quest.getPlace());
        values.put(KEY_TYPE, quest.getType());
        values.put(KEY_COMMENTS, quest.getComments());
        values.put(KEY_CREATED_AT, getDateTime());

        return values;
    }
}
