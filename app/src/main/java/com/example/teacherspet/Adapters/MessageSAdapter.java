package com.example.teacherspet.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherspet.DataClass.MessageSt;
import com.example.teacherspet.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageSAdapter extends RecyclerView.Adapter{

    Context contexts;
    ArrayList<MessageSt> messages;

    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public MessageSAdapter(Context contexts, ArrayList<MessageSt> messages){
            this.contexts = contexts;
            this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT){
            View view = LayoutInflater.from(contexts).inflate(R.layout.item_sent, parent, false);
            return new SentViewHolderS(view);
        }else {
            View view = LayoutInflater.from(contexts).inflate(R.layout.item_reciever, parent, false);
            return new ReceiveViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        MessageSt messageSt = messages.get(position);
        if (FirebaseAuth.getInstance().getUid().equals(messageSt.getSenderId())){
            return ITEM_SENT;
        }
        else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageSt messageSt = messages.get(position);

        if (holder.getClass() == SentViewHolderS.class){
            SentViewHolderS viewHolderS = (SentViewHolderS)holder;
            viewHolderS.sent.setText(messageSt.getMessageS());
        }else {
            ReceiveViewHolder viewHolderS = (ReceiveViewHolder) holder;
            viewHolderS.receive.setText(messageSt.getMessageS());
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolderS extends RecyclerView.ViewHolder{

        private TextView sent;

        public SentViewHolderS(@NonNull View itemView) {
            super(itemView);

            sent = itemView.findViewById(R.id.sent);
        }
    }

    public class ReceiveViewHolder extends RecyclerView.ViewHolder{

        private TextView receive;

        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);

            receive = itemView.findViewById(R.id.recieve);
        }
    }
}
