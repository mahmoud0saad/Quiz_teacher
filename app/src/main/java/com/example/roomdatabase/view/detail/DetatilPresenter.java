package com.example.roomdatabase.view.detail;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.roomdatabase.R;
import com.example.roomdatabase.Utils;
import com.example.roomdatabase.model.AppExecutors;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.ViewModel.LoadStudentIdViewModel;
import com.example.roomdatabase.model.ViewModel.StudentIdViewModelFactory;
import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.Users;

public class DetatilPresenter implements DetailPresenterMvp {
    private static String TAG=DetatilPresenter.class.getSimpleName();
    private Manager manager;
    private Context mContext;
    private DetailViewMvp mDetailView;
    private int mStudentId=-1;

    public DetatilPresenter( Context context,DetailViewMvp detailViewMvp) {
        mContext=context;
        manager=Manager.getInstance();
        mDetailView=detailViewMvp;
    }

    @Override
    public void addStudent(final Users users) {

            Log.d(TAG, "addStudent: is in add ");
            if (users.getImage()==null){
                Utils.setDialogAlert(mContext, "error ","please enter Image");
            }else {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        manager.getDatabaseHelper(mContext).studentDao().insertStudent(users);

                    }
                });

                mDetailView.finishedActivity();
            }

    }

    @Override
    public void updateStudent(final Users users) {

        users.setId(mStudentId);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                manager.getDatabaseHelper(mContext).studentDao().updateStudent(users);
            }
        });

        mDetailView.finishedActivity();
    }

    @Override
    public void preapareDetailView(Intent intent) {
        if (intent.getAction().equals(mContext.getString(R.string.intent_action_add_student))){
            mDetailView.setIconButtonFab(R.drawable.ic_done_black_24dp);

        }else if (intent.getAction().equals(mContext.getString(R.string.intent_action_update_student))){
            mDetailView.setIconButtonFab(R.drawable.ic_update_black_24dp);
            if (intent.hasExtra(mContext.getString(R.string.intent_student_update_id))) {
                //get id from intent and do default -1
                 mStudentId =intent.getIntExtra(mContext.getString(R.string.intent_student_update_id),-1);

                 //get instance from database helper
                 AppDatabase databaseHelper=Manager.getInstance().getDatabaseHelper(mContext);

                 //create factory
                 StudentIdViewModelFactory factory=new StudentIdViewModelFactory(databaseHelper,mStudentId);

                 //get view model by factory
                 LoadStudentIdViewModel viewModel= ViewModelProviders.of((FragmentActivity) mContext,factory).get(LoadStudentIdViewModel.class);

                 final LiveData<Users> studentLiveData=viewModel.getStudent();
                 studentLiveData.observe((LifecycleOwner) mContext, new Observer<Users>() {
                             @Override
                             public void onChanged(@Nullable Users users) {
                                 studentLiveData.removeObserver(this);

                                 mDetailView.putDataStudentToField(users);
                             }
                 });


            }
        }
    }


}
