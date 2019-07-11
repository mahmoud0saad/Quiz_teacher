package com.example.roomdatabase.view.quizquestion;

import com.example.roomdatabase.model.database.Questions;

import java.util.List;

public interface QuizQuestionViewMvp {
   void setAllQuestion(List<Questions> questionsList);
}
