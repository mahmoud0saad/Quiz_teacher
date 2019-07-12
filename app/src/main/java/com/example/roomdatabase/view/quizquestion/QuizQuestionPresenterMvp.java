package com.example.roomdatabase.view.quizquestion;

public interface QuizQuestionPresenterMvp {
    void getAllQuestionForLesson(int  lessonId);
    void checkQuizOrResult(int lessonId);
    void insertResult(int lessonId, String result);
}
