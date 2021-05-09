package com.example.teacherspet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherspet.Chats.ChatStudent;
import com.example.teacherspet.R;
import com.example.teacherspet.DataClass.UserTeacher;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>{

    Context contextT;
    ArrayList<UserTeacher> userTeachers;

    public TeacherAdapter (Context contextT, ArrayList<UserTeacher> userTeachers){
        this.contextT = contextT;
        this.userTeachers = userTeachers;

    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextT).inflate(R.layout.userlistt, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {

        UserTeacher userTeacher = userTeachers.get(position);
        holder.userNameT.setText(userTeacher.getTeacherName());
        holder.userEmailT.setText(userTeacher.getTeacherEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contextT, ChatStudent.class);
                intent.putExtra("names", userTeacher.getTeacherName());
                intent.putExtra("uids", userTeacher.getUidT());
                contextT.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userTeachers.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        private TextView userNameT, userEmailT;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameT = itemView.findViewById(R.id.userNameT);
            userEmailT = itemView.findViewById(R.id.userEmailT);
        }
    }
}
