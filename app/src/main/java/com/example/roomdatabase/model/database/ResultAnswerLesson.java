package com.example.roomdatabase.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "resultAnswer")
public class ResultAnswerLesson {
    @PrimaryKey (autoGenerate = true)
    int answerId;

    String studentId ,lessonId,result;

    public ResultAnswerLesson(String studentId, String lessonId, String result) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.result = result;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
