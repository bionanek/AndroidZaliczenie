package com.example.artur.timeger.model;

import java.util.Date;

/**
 * Created by bionanek on 23-11-2016.
 */

public class Quest
{
    private String _type;
    private String _place;
    private String _description;
    private String _createdAt;
    private String _questDate;
    private String _alarmDate;
    private String _comments;
    private String _status;

    private int _id;

    private boolean _isAlarmSet;

    public String getStatus()
    {
        return _status;
    }

    public void setStatus(String status)
    {
        this._status = status;
    }

    public String getCreatedAt()
    {
        return _createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this._createdAt = createdAt;
    }

    public String getDescription()
    {
        return this._description;
    }

    public void setDescription(String description)
    {
        this._description = description;
    }

    public int getId()
    {
        return this._id;
    }

    public void setId(int id)
    {
        this._id = id;
    }

    public String getType()
    {
        return _type;
    }

    public void setType(String newType)
    {
        this._type = newType;
    }

    public String getPlace()
    {
        return _place;
    }

    public void setPlace(String newPlace)
    {
        this._place = newPlace;
    }

    public String getQuestDate()
    {
        return _questDate;
    }

    public void setQuestDate(String newDate)
    {
        this._questDate = newDate;
    }

    public boolean IsAlarmSet()
    {
        return _isAlarmSet;
    }

    public String getAlarmDate()
    {
        return _alarmDate;
    }

    public void setComments(String comments)
    {
        this._comments = comments;
    }

    public String getComments()
    {
        return this._comments;
    }

    public void setAlarm(String newAlarmDate)
    {
        this._alarmDate = newAlarmDate;
        this._isAlarmSet = true;
    }
    @Override
    public String toString(){
        return _type + ", "
                + _place + ", "
                + _description + ", "
                + _questDate;
    }
}