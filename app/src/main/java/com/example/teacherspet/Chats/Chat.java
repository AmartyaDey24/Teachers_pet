package com.example.teacherspet.Chats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teacherspet.Adapters.MessageSAdapter;
import com.example.teacherspet.DataClass.MessageSt;
import com.example.teacherspet.DataClass.UserTeacher;
import com.example.teacherspet.R;
import com.example.teacherspet.TeacherInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    private EditText chat;
    private RecyclerView chatRecyclerView;
    private ImageView sendButton;

    private TextView userName;
    private ImageView userIfo;

    String senderRoom, receiverRoom;

    FirebaseDatabase sDatabase;

    MessageSAdapter adapter;
    ArrayList<MessageSt> messageSts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_student);

        getSupportActionBar().hide();

        chat = findViewById(R.id.chat);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sendButton = findViewById(R.id.sendButtton);

        userName = findViewById(R.id.EmailT);
        userIfo = findViewById(R.id.userInfo);

        userIfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, TeacherInfo.class);
                startActivity(intent);
            }
        });

        messageSts = new ArrayList<>();
        adapter = new MessageSAdapter(this, messageSts);
        chatRecyclerView.setAdapter(adapter);

        String names = getIntent().getStringExtra("name");
        String receiverUids = getIntent().getStringExtra("uid");
        String senderUids = FirebaseAuth.getInstance().getUid();

        senderRoom = senderUids + receiverUids;
        receiverRoom = receiverUids + senderUids;

        sDatabase = FirebaseDatabase.getInstance();

        sDatabase.getReference().child("SChats")
                .child(senderRoom)
                .child("Messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageSts.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            MessageSt messageSt = new MessageSt((String) snapshot1.getValue(), senderUids);
                            messageSts.add(messageSt);
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageTxt = chat.getText().toString();

                MessageSt messageSt = new MessageSt(messageTxt, senderUids);
                chat.setText("");

                sDatabase.getReference().child("SChats")
                        .child(senderRoom)
                        .child("Messages")
                        .setValue(messageSt).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        sDatabase.getReference().child("SChats")
                                .child(receiverRoom)
                                .child("Messages")
                                .setValue(messageSt).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
            }
        });

        userName.setText(names);

    }
}