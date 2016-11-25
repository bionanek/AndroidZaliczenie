package com.example.artur.timeger;

import java.util.Date;

/**
 * Created by bionanek on 23-11-2016.
 */

public class Quest
{
    private String _type;
    private String _place;
    private String _description;
    private int _id;

    private Date _questDate;
    private Date _alarmDate;

    private boolean _isAlarmset;

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
    public Date getQuestDate()
    {
        return _questDate;
    }
    public void setQuestDate(Date newDate)
    {
        this._questDate = newDate;
    }
    public boolean IsAlarmset()
    {
        return _isAlarmset;
    }
    public Date getAlarmDate()
    {
        return _alarmDate;
    }
    public void setAlarm(Date newAlarmDate)
    {
        this._alarmDate = newAlarmDate;
        this._isAlarmset = true;
    }
    @Override
    public String toString(){
        return _type + ", "
                + _place + ", "
                + _description + ", "
                + _questDate;
    }
}