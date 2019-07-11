package com.example.roomdatabase.view.teacherMain;

import com.example.roomdatabase.model.database.Lesson;

import java.util.List;

public interface TeacherMainViewMvp {
    void setAllLesson(List<Lesson> lessonList);
}
