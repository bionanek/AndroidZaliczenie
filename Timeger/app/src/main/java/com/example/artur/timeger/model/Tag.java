package com.example.artur.timeger.model;

/**
 * Created by bionanek on 26-11-2016.
 */

public class Tag
{
    private int _id;

    String _tagName;

    public void setId(int id)
    {
        this._id = id;
    }

    public int getId()
    {
        return this._id;
    }

    public void setTagName(String tagName)
    {
        this._tagName = tagName;
    }

    public String getTagName()
    {
        return this._tagName;
    }
}
