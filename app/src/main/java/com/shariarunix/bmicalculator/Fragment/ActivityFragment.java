package com.shariarunix.bmicalculator.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shariarunix.bmicalculator.R;

public class ActivityFragment extends Fragment {
    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity myActivity = (AppCompatActivity) getActivity();
            // Floating Action Button for deleting all BMI result
            FloatingActionButton deleteBmiResult = myActivity.findViewById(R.id.f_btn_bmi_calc);

            deleteBmiResult.setBackgroundTintList(ColorStateList.valueOf(myActivity.getColor(R.color.red)));
            deleteBmiResult.setImageResource(R.drawable.ic_delete);

            deleteBmiResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(myActivity, "BONK", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}