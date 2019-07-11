package com.example.roomdatabase.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

@Entity(tableName = "users")
public class Users {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name , age  , userName , password,stateUser ;
    Bitmap image;

    @Ignore
    public Users(String name, String age, String userName,String password,String stateUser, Bitmap image) {
        this.name = name;
        this.age = age;
        this.userName = userName;
        this.image=image;
        this.password=password;
        this.stateUser=stateUser;
    }

    public Users(int id, String name, String age, String userName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStateUser() {
        return stateUser;
    }

    public void setStateUser(String stateUser) {
        this.stateUser = stateUser;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
