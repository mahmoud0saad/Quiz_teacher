package com.example.roomdatabase.view.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.Users;
import com.example.roomdatabase.view.detail.DetailView;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {
    private static String TAG= StudentRecyclerAdapter.class.getSimpleName();
    private Context mContext;
    private List<Users> mUsersList;

    public StudentRecyclerAdapter(Context context , List<Users> usersList) {
        mContext=context;
        mUsersList = usersList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(mContext).inflate(R.layout.student_recycler_item,viewGroup,false);

        StudentViewHolder studentViewHolder =new StudentViewHolder(rootView);

        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        studentViewHolder.onBind(mUsersList.get(i));
    }


    @Override
    public int getItemCount() {
        return mUsersList.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder{
        private ImageView mStudentImage;
        private TextView mStudentName,mStudentAge,mStudentYear;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            mStudentImage=itemView.findViewById(R.id.student_photo_image_view);
            mStudentName=itemView.findViewById(R.id.name_text_view);
            mStudentAge=itemView.findViewById(R.id.age_text_view);
            mStudentYear=itemView.findViewById(R.id.year_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(mContext, DetailView.class);
                    Log.d(TAG, "onClick: "+mStudentName.getTag());

                    intent.putExtra(mContext.getString(R.string.intent_student_update_id), (int) mStudentName.getTag());
                    intent.setAction(mContext.getString(R.string.intent_action_update_student));
                    mContext.startActivity(intent);

                }
            });


        }
        public void onBind(Users users){
            mStudentName.setText(users.getName());
            mStudentAge.setText(users.getAge());
            mStudentYear.setText(String.valueOf(users.getId()));
            mStudentImage.setImageBitmap(users.getImage());

            mStudentName.setTag(users.getId());
        }
    }
}
