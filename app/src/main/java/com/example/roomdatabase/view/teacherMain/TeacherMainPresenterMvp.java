package com.example.roomdatabase.view.teacherMain;

import com.example.roomdatabase.model.database.Lesson;

import java.util.List;

public interface TeacherMainPresenterMvp {
    void getLessonTeacher(int userId);
    void insertLesson(int teacherid);
}

