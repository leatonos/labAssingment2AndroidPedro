package com.pedroapp.labassingment.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pedroapp.labassingment.room.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDb extends RoomDatabase {

    private static final String DB_NAME = "contact_room_db";

    public abstract ContactDao contactDao();

    private static volatile ContactRoomDb INSTANCE;

    public static ContactRoomDb getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContactRoomDb.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }

}
