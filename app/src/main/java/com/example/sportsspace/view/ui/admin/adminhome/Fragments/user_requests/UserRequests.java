package com.example.sportsspace.view.ui.admin.adminhome.Fragments.user_requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.sportsspace.R;
import com.example.sportsspace.databinding.UserRequestsBinding;
import com.example.sportsspace.model.userdata.UserData;
import com.example.sportsspace.view.ui.admin.adminhome.adapter.UserRequestsAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserRequests extends Fragment {
    DatabaseReference userRequestReference;
    UserRequestsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        UserRequestsBinding userRequestBinding = DataBindingUtil.inflate(
                inflater, R.layout.user_requests, container, false
        );

        userRequestReference = FirebaseDatabase.getInstance().getReference().child("/admin/user_requests");

        FirebaseRecyclerOptions<UserData> options
                = new FirebaseRecyclerOptions
                .Builder<UserData>()
                .setQuery(userRequestReference,UserData.class)
                .build();

        adapter = new UserRequestsAdapter(options,getContext());
        userRequestBinding.setAdminrequestAdapter(adapter);
        View view = userRequestBinding.getRoot();

        //Mark all the users as seen
        markAllUserRequestAsSeen();

        return view;

    }


    public void markAllUserRequestAsSeen(){
        DatabaseReference approvedUserRef = FirebaseDatabase.getInstance().getReference()
                .child("admin")
                .child("approved_user");

        approvedUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()){
                    snap.child("seen").getRef().setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
