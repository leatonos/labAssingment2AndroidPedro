package com.pedroapp.labassingment.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

@Entity(tableName = "contact")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "first_name")
    private String first_name;

    @NonNull
    @ColumnInfo(name = "last_name")
    private String last_name;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "phone_number")
    private String phone_number;

    @NonNull
    @ColumnInfo(name = "address")
    private String address;



    public Contact(@NonNull String first_name, @NonNull String last_name, @NonNull String email, @NonNull String phone_number, @NonNull String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
    }

    //Gets
    public int getId() {
        return id;
    }

    @NonNull
    public String getFirst_name() {
        return first_name;
    }

    @NonNull
    public String getLast_name(){ return last_name;}

    @NonNull
    public String getEmail(){return email;}

    @NonNull
    public  String getPhone_number(){return phone_number;}

    @NonNull
    public  String getAddress(){return address;}


    //Sets
    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(@NonNull String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(@NonNull String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPhone_number(@NonNull String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }



}
