package com.example.artur.timeger.model;

/**
 * Created by bionanek on 26-11-2016.
 */

public class User
{
    private String _login;
    private String _pass;
    private String _name;
    private String _surname;
    private String _createdAt;

    private int _id;

    public String getCreatedAt()
    {
        return this._createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this._createdAt = createdAt;
    }

    public String getLogin()
    {
        return this._login;
    }

    public void setLogin(String login)
    {
        this._login = login;
    }

    public String getPass()
    {
        return this._pass;
    }

    public void setPass(String pass)
    {
        this._pass = pass;
    }

    public String getName()
    {
        return this._name;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public String getSurname()
    {
        return this._surname;
    }

    public void setSurname(String surname)
    {
        this._surname = surname;
    }

    public int getId()
    {
        return this._id;
    }

    public void setId(int id)
    {
        this._id = id;
    }
}
