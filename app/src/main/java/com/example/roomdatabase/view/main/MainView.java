package com.example.roomdatabase.view.main;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.Users;
import com.example.roomdatabase.view.detail.DetailView;

import java.util.ArrayList;
import java.util.List;

public class MainView extends AppCompatActivity implements MainViewMvp{
    private static String TAG=MainView.class.getSimpleName();
    private RecyclerView mStudentRecycler;
    private SwipeRefreshLayout mSwipe;
    private StudentRecyclerAdapter mAdapterStudent;
    private List<Users> mUsersList;
    private FloatingActionButton mFloatingActionButton;
    private int mCount=1;
    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter=new MainPresenter(this,this);

        setUpRecycler();


//        presenter.refreshAllData();

    }



    private void setUpRecycler() {
        mSwipe=findViewById(R.id.swipe_refresh_recycler);
        mStudentRecycler=findViewById(R.id.student_recycler);
        mFloatingActionButton=findViewById(R.id.add_student_fab);

        mUsersList =new ArrayList<>();


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);

        mAdapterStudent=new StudentRecyclerAdapter(this, mUsersList);

        mStudentRecycler.setAdapter(mAdapterStudent);
        mStudentRecycler.setLayoutManager(linearLayoutManager);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainView.this, DetailView.class);
                intent.setAction(getString(R.string.intent_action_add_student));
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                presenter.deleteStudent(mUsersList.get(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(mStudentRecycler);
        
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: "+mCount);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.refreshLimitData(mCount,5);

                        mSwipe.setRefreshing(false);
                    }
                }, 90);

            }
        });
    }

    @Override
    public void addStudent(Users users) {
        mUsersList.add(users);
        mAdapterStudent.notifyDataSetChanged();
    }

    @Override
    public void addListStudent(List<Users> usersList) {
        Log.d(TAG, "addListStudent: "+ usersList);
        if (usersList !=null) {
            mCount += usersList.size();
            Toast.makeText(this, "No Item", Toast.LENGTH_SHORT).show();
        }
        mUsersList.addAll(usersList);
        mAdapterStudent.notifyDataSetChanged();

    }

    @Override
    public void clearAllStudent() {
        mUsersList.clear();
        mAdapterStudent.notifyDataSetChanged();
    }


}
