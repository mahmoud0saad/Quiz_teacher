package com.example.roomdatabase.view.LessonResults;

import android.graphics.Bitmap;

import com.example.roomdatabase.model.database.ResultAnswerLesson;
import com.example.roomdatabase.model.models.StudentGrade;

import java.util.HashMap;
import java.util.List;

public interface LessonResultViewMvp {
    void setOnlyResultToRecycler(StudentGrade studentGrade);

}

