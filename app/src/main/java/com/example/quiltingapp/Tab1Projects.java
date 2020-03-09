package com.example.quiltingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quiltingapp.R;
import com.example.quiltingapp.ui.main.PageViewModel;

public class Tab1Projects extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    //bodge for testing:
    private String nameList;
    ListView pList;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab1projects, container, false);
        //final TextView textView = root.findViewById(R.id.section_label);
        //pageViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //        public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});


        return root;
    }

}
