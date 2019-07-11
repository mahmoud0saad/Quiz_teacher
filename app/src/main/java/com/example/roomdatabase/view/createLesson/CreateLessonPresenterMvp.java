package com.example.roomdatabase.view.createLesson;

import com.example.roomdatabase.model.database.Questions;

import java.util.List;

public interface CreateLessonPresenterMvp {

    void createLesson(String teacerId,String nameLesson);
    void insertAllQuestion(int lessonId, List<Questions> questionsList);

}
