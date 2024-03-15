package com.example.lab04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recyclerview extends RecyclerView.Adapter<Recyclerview.MyViewHolder> {
private ArrayList<User> userslist;
public Recyclerview(ArrayList<User> userslist){
    this.userslist = userslist;
}
public class MyViewHolder extends RecyclerView.ViewHolder{
    private TextView nametext;

    public MyViewHolder(final View view){
        super(view);
        nametext = view.findViewById(R.id.textView2);
    }
}

    @NonNull
    @Override
    public Recyclerview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_room, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return userslist.size();
    }
}
