package com.example.roomdatabase.model.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("select * from users ")
     LiveData<List<Users>> getAllStudent();

    @Insert
    long insertStudent(Users users);

    @Delete
    void deleteStudent(Users users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateStudent(Users users);

    @Query("select * from users where id=:id")
    LiveData<Users> getStudentById(int id);


    @Query("select * from users limit :first,:count  ")
    LiveData<List<Users>> getstudentBetween(int first, int count);

    ///////////////////////

    @Query("select * from users where userName=:username and password=:password")
    LiveData<Users> checkUserAllowed(String username,String password);

    @Query("select * from users where userName=:username ")
    LiveData<Users> checkUsernameFound(String username);

    @Query("select * from lesson ")
    LiveData<List<Lesson>> getAllLessonForStudent();


    @Query("select * from lesson where teacherId=:userId")
    LiveData<List<Lesson>> getAllLessonForTeacher(int userId);

    @Query("select * from lesson where lessonId=:lessonId")
    LiveData<Lesson> getLessonById(int lessonId);

    @Query("select * from question where lessonId=:lessonId ")
    LiveData<List<Questions>> getAllQuestionForLesson(int lessonId);

    @Query("select * from resultAnswer where studentId=:studentId ")
    LiveData<List<ResultAnswerLesson>> getAllResultForStudent(int studentId);


    @Query("select * from resultAnswer where lessonId=:lessonId ")
    LiveData<List<ResultAnswerLesson>> getAllResultForLesson(String lessonId);

    @Query("select * from resultAnswer where studentId=:studentId and lessonId=:lessonId ")
    LiveData<ResultAnswerLesson> getAllStudentResultForlesson(int lessonId, int studentId);

    @Query("select * from users where id=:userId ")
    LiveData<Users> getUsernameById(int userId);

    @Insert
    long insertLesson(Lesson lesson);

    @Insert
    void insertQuestion(Questions questions);


    @Insert
    void insertResultAnswerLesson(ResultAnswerLesson resultAnswerLesson);








}
