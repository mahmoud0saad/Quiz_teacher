package com.example.roomdatabase.model;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {

    public static AppExecutors instance=null;
    public static Object lock=new Object();
    Executor diskIO;

    public AppExecutors(Executor executorIO) {
        diskIO=executorIO;
    }
    public static AppExecutors getInstance(){
        if (instance==null){
            synchronized (lock){
                if (instance==null){
                    instance=new AppExecutors(Executors.newSingleThreadExecutor());
                }
            }
        }
        return instance;
    }
    public Executor diskIO(){
        return diskIO;
    }
}
