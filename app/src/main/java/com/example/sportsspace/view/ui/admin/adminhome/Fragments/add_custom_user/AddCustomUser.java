package com.example.sportsspace.view.ui.admin.adminhome.Fragments.add_custom_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AddCustomUserBinding;
import com.example.sportsspace.viewmodels.AddCustomUserVM;

public class AddCustomUser extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AddCustomUserBinding customUserBinding = DataBindingUtil.inflate(
                inflater , R.layout.add_custom_user ,container , false
        );

        customUserBinding.setCustomUser(new AddCustomUserVM());
        customUserBinding.executePendingBindings();
        View view = customUserBinding.getRoot();
        return view;
    }
}
