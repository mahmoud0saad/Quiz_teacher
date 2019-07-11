package com.example.roomdatabase.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

@Entity (tableName = "question")
public class Questions {
    @PrimaryKey (autoGenerate = true)
    int questionId;

    String lessonId;
    String theQuestion ,chooserFirst,chooserSecond,chooserThird,chooserFour,theAnswer;
    Bitmap questionPhotoBitmap;
    @Ignore
    public Questions(String lessonId, String theQuestion, String chooserFirst, String chooserSecond, String chooserThird, String chooserFour, String theAnswer, Bitmap questionPhotoBitmap) {
        this.lessonId = lessonId;
        this.theQuestion = theQuestion;
        this.chooserFirst = chooserFirst;
        this.chooserSecond = chooserSecond;
        this.chooserThird = chooserThird;
        this.chooserFour = chooserFour;
        this.theAnswer = theAnswer;
        this.questionPhotoBitmap = questionPhotoBitmap;
    }

    public String getChooserFirst() {
        return chooserFirst;
    }

    public void setChooserFirst(String chooserFirst) {
        this.chooserFirst = chooserFirst;
    }

    public String getChooserSecond() {
        return chooserSecond;
    }

    public void setChooserSecond(String chooserSecond) {
        this.chooserSecond = chooserSecond;
    }

    public String getChooserThird() {
        return chooserThird;
    }

    public void setChooserThird(String chooserThird) {
        this.chooserThird = chooserThird;
    }

    public String getChooserFour() {
        return chooserFour;
    }

    public void setChooserFour(String chooserFour) {
        this.chooserFour = chooserFour;
    }

    public Bitmap getQuestionPhotoBitmap() {
        return questionPhotoBitmap;
    }

    public void setQuestionPhotoBitmap(Bitmap questionPhotoBitmap) {
        this.questionPhotoBitmap = questionPhotoBitmap;
    }

    public Questions(){

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        this.theQuestion = theQuestion;
    }

    public String getTheAnswer() {
        return theAnswer;
    }

    public void setTheAnswer(String theAnswer) {
        this.theAnswer = theAnswer;
    }


}
