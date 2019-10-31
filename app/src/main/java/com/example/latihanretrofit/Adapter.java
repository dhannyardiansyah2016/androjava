package com.example.latihanretrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    private List<Contact>contacts;

    public Adapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {

        holder.nama.setText(contacts.get(position).getName());
        holder.email.setText(contacts.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nama,email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context,Editor.class);
            i.putExtra("id",contacts.get(getAdapterPosition()).getId());
            i.putExtra("name",contacts.get(getAdapterPosition()).getName());
            i.putExtra("email",contacts.get(getAdapterPosition()).getEmail());
            context.startActivity(i);
        }
    }
}
