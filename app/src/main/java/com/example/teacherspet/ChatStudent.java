package com.example.teacherspet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatStudent extends AppCompatActivity {

    private EditText chat;
    private RecyclerView chatRecyclerView;
    private ImageView sendButton;

    String senderRoom, receiverRoom;

    FirebaseDatabase sDatabase;

    MessageSAdapter adapter;
    ArrayList<MessageSt> messageSts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_student);

        chat = findViewById(R.id.chat);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        sendButton = findViewById(R.id.sendButtton);

        messageSts = new ArrayList<>();
        adapter = new MessageSAdapter(this, messageSts);

        String names = getIntent().getStringExtra("names");
        String receiverUids = getIntent().getStringExtra("uids");
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

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageTxt = chat.getText().toString();

                MessageSt messageSt = new MessageSt(messageTxt,senderUids);
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

        getSupportActionBar().setTitle(names);

    }
}