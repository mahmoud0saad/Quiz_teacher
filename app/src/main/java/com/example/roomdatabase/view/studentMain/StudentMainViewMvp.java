package com.example.roomdatabase.view.studentMain;

import com.example.roomdatabase.model.database.Lesson;

import java.util.List;

public interface StudentMainViewMvp {
    void setLessonToRecycler(List<Lesson> lessonList);
}
