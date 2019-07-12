package com.example.roomdatabase.view.quizquestion;

import com.example.roomdatabase.model.database.Questions;
import com.example.roomdatabase.model.database.ResultAnswerLesson;

import java.util.List;

public interface QuizQuestionViewMvp {
   void setAllQuestion(List<Questions> questionsList);
   void setUpViewQuiz();
   void setUpViewResult(int correctQuestion,int finalDegreeQuiz);
}
