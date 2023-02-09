package com.example.sportsspace.viewmodels;

import android.content.Context;
import android.widget.Toast;

import com.example.sportsspace.model.userdata.UserData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCustomUserVM {
    public String name, phoneno;


    public void addUser(Context context){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String uid = reference.child("admin").child("approved_user").push().getKey();

        UserData userData = new UserData(name , phoneno ,uid , false , false );
        reference.child("admin").child("approved_user").child(uid).setValue(userData);
        Toast.makeText(context, "User Added", Toast.LENGTH_SHORT).show();

    }
}
