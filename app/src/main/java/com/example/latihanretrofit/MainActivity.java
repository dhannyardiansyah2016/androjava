package com.example.latihanretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Adapter adapter;
    List<Contact>contacts;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.pg_progress);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

    }
    public void fetchData(){
        Call<List<Contact>> call = apiInterface.getContact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                contacts = response.body();
                adapter = new Adapter(MainActivity.this,contacts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                startActivity(new Intent(MainActivity.this,Editor.class));
            default: return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
