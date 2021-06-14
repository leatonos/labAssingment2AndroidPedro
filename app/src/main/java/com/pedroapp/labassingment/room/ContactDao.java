package com.pedroapp.labassingment.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(Contact contact);

    @Query("DELETE FROM contact")
    void deleteAllContacts();

    @Query("DELETE FROM contact WHERE id = :id" )
    int deleteContact(int id);

    @Query("UPDATE contact SET first_name = :first_name, last_name = :last_name, email = :email, phone_number = :phone_number, address = :address WHERE id = :id")
    int updateContact(int id, String first_name, String last_name, String email, String phone_number, String address);

    @Query("SELECT * FROM contact ORDER BY first_name")
    List<Contact> getAllContacts();

}
