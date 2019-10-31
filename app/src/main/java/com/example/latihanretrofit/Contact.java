package com.example.latihanretrofit;

import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String message;

    public Contact(String id, String name, String email, String value, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.value = value;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
