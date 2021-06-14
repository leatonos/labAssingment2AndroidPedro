package com.pedroapp.labassingment.model;

public class Contact {

    int id;
    String first_name, last_name, email,phone_number,address;

    public Contact(int id,String first_name, String last_name, String email, String phone_number, String address){

        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;

    }

   public int getId() {
        return id;
    }

   public String getFirst_name() {
        return first_name;
    }

   public String getLast_name(){ return last_name;}

   public  String getEmail(){return email;}

   public  String getPhone_number(){return phone_number;}

   public  String getAddress(){return address;}

}
