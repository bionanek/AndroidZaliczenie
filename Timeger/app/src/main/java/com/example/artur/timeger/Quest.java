package com.example.artur.timeger;

import java.util.Date;

/**
 * Created by bionanek on 23-11-2016.
 */

public class Quest
{
    private String _type;
    private String _place;

    private Date _questDate;
    private Date _alarmDate;

    private boolean _isAlarmSet;

    public String GetType()
    {
        return _type;
    }
    public void SetType(String newType)
    {
        this._type = newType;
    }
    public String GetPlace()
    {
        return _place;
    }
    public void SetPlace(String newPlace)
    {
        this._place = newPlace;
    }
    public Date GetQuestDate()
    {
        return _questDate;
    }
    public void SetQuestDate(Date newDate)
    {
        this._questDate = newDate;
    }
    public boolean IsAlarmSet()
    {
        return _isAlarmSet;
    }
    public Date GetAlarmDate()
    {
        return _alarmDate;
    }
    public void SetAlarm(Date newAlarmDate)
    {
        this._alarmDate = newAlarmDate;
        this._isAlarmSet = true;
    }
}