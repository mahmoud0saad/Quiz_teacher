package com.example.roomdatabase.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity (tableName = "Lesson")
public class Lesson {
    @PrimaryKey (autoGenerate = true)
    int lessonId;

    String teacherId,lessonName;

    public Lesson(String teacherId, String lessonName) {
        this.teacherId = teacherId;
        this.lessonName = lessonName;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
