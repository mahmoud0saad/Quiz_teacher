package com.example.roomdatabase.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Users.class,Lesson.class,Questions.class,ResultAnswerLesson.class},version =4 ,exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase mAppDatabaseInstance=null;
    private static Object lock=new Object();
    private static String DATABASE_NAME="student";

    public static AppDatabase getInstance(Context context){
        if (mAppDatabaseInstance==null){
            synchronized (lock){
                if (mAppDatabaseInstance==null){
                    mAppDatabaseInstance= Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME)
                            .build();
                    return mAppDatabaseInstance;
                }
            }
        }
        return mAppDatabaseInstance;
    }

    public abstract StudentDao studentDao();
}
