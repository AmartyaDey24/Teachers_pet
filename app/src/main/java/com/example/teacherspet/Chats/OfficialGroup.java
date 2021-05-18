package com.example.teacherspet.Chats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.teacherspet.Adapters.GroupMessageAdapter;
import com.example.teacherspet.DataClass.MessageSt;
import com.example.teacherspet.databinding.ActivityOfficialGroupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OfficialGroup extends AppCompatActivity {

    ActivityOfficialGroupBinding binding;

    FirebaseDatabase sDatabase;

    GroupMessageAdapter adapter;
    ArrayList<MessageSt> messageSts;

    String senderUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfficialGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Official Group");

        senderUid = FirebaseAuth.getInstance().getUid();

        messageSts = new ArrayList<>();
        adapter = new GroupMessageAdapter(this, messageSts);
        binding.chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.chatRecyclerView.setAdapter(adapter);
        sDatabase = FirebaseDatabase.getInstance();

        sDatabase.getReference().child("GroupChat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageSts.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                            MessageSt messageSt = snapshot1.getValue(MessageSt.class);
                            messageSts.add(messageSt);
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            binding.sendButtton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String messageTxt = binding.chat.getText().toString();

                    MessageSt messageSt = new MessageSt(messageTxt, senderUid);
                    binding.chat.setText("");

                    sDatabase.getReference().child("GroupChat")
                            .push()
                            .setValue(messageSt);
                }
            });
    }
}