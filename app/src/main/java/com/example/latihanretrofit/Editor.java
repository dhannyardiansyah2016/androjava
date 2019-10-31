package com.example.latihanretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editor extends AppCompatActivity {

    EditText nama,mail;
    Button simpan;
    String extraid,name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        extraid = i.getStringExtra("id");
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");

        setContentView(R.layout.activity_editor);
        nama = findViewById(R.id.ed_name);
        mail = findViewById(R.id.ed_email);
        simpan = findViewById(R.id.btn_simpan);
        kondisi(extraid);
    }

    private void insertData(){
        String namaku = nama.getText().toString();
        String emailku = mail.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Contact> call = apiInterface.insertUser(namaku,emailku);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                if(response.body().getValue().equals("1")){
                    Toast.makeText(Editor.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Editor.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });


    }
    private void updateData(){
        String namaku = nama.getText().toString();
        String emailku = mail.getText().toString();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Contact> call = apiInterface.updateUser(extraid,namaku,emailku);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                if(response.body().getValue().equals("1")){
                    Toast.makeText(Editor.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Editor.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });


    }
    private void kondisi(String kondisi){
        if (kondisi==null){
            simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    insertData();
                }
            });
        }else{
            nama.setText(name);
            mail.setText(email);
            simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateData();
                }
            });
        }
    }

}
