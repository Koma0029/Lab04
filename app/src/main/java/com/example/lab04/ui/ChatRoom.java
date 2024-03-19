package com.example.lab04.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlab4.R;
import com.example.newlab4.databinding.ActivityMainBinding;
import com.example.newlab4.databinding.ReceivedMessageBinding;
import com.example.newlab4.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.ChatMessage;
import data.ChatroomViewModel;

public class ChatRoom extends AppCompatActivity {
    ActivityMainBinding binding;
    ChatroomViewModel chatModel;
    ArrayList<ChatMessage> messages = new ArrayList<>();

    RecyclerView.Adapter<MyRowHolder> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatroomViewModel.class);

        messages = chatModel.messages.getValue();
        if (messages == null) {
            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
        }

        myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceivedMessageBinding binding = ReceivedMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTimeSent());
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                ChatMessage obj = messages.get(position);
                return obj.isSentButton() ? 0 : 1;
            }
        };

        binding.recyclerView.setAdapter(myAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.sendButton.setOnClickListener(click -> sendMessage(true));
        binding.receiveButton.setOnClickListener(click -> sendMessage(false));
    }

    private void sendMessage(boolean isSent) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateandTime = sdf.format(new Date());
        messages.add(new ChatMessage(binding.inputText.getText().toString(), currentDateandTime, isSent));
        myAdapter.notifyItemInserted(messages.size() - 1);
        binding.inputText.setText("");
    }

    static class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}
