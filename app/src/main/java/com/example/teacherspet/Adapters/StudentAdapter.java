package com.example.teacherspet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherspet.Chats.Chat;
import com.example.teacherspet.R;
import com.example.teacherspet.DataClass.UserStudent;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context contextS;
    ArrayList<UserStudent> userStudents;

    public StudentAdapter(Context contexts, ArrayList<UserStudent> userStudents){
        this.contextS = contexts;
        this.userStudents = userStudents;

    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextS).inflate(R.layout.userlistst, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        UserStudent userStudent = userStudents.get(position);
        holder.userNameS.setText(userStudent.getStudentName());
        holder.userUsn.setText(userStudent.getStudentUsn());
        holder.userEmailS.setText(userStudent.getStudentEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contextS, Chat.class);
                intent.putExtra("name", userStudent.getStudentName());
                intent.putExtra("uid", userStudent.getUidS());
                contextS.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return userStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView userNameS, userUsn, userEmailS;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            userNameS = itemView.findViewById(R.id.userNameS);
            userUsn = itemView.findViewById(R.id.userUsn);
            userEmailS = itemView.findViewById(R.id.userEmailS);

        }
    }
}
