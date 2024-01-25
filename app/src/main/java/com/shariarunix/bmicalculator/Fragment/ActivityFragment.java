package com.shariarunix.bmicalculator.Fragment;

import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shariarunix.bmicalculator.Database.BmiResultModel;
import com.shariarunix.bmicalculator.Database.BmiTableClass;
import com.shariarunix.bmicalculator.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

        //noinspection resource
        ArrayList<BmiResultModel> bmiResults = new BmiTableClass(requireActivity()).readAllResult();
        Collections.reverse(bmiResults);

        ListView listShowBmiResults = view.findViewById(R.id.list_show_bmi_results);
        listShowBmiResults.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return bmiResults.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @SuppressLint("SetTextI18n")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.layout_show_bmi_result, parent, false);
                }

                TextView listLayoutShowBmi = convertView.findViewById(R.id.list_layout_show_bmi);
                TextView listLayoutShowAge = convertView.findViewById(R.id.list_layout_show_age);
                TextView listLayoutShowGender = convertView.findViewById(R.id.list_layout_show_gender);
                TextView listLayoutShowWeight = convertView.findViewById(R.id.list_layout_show_weight);
                TextView listLayoutShowHeight = convertView.findViewById(R.id.list_layout_show_height);

                BmiResultModel bmiResultModel = bmiResults.get(position);

                String userBmi = bmiResultModel.getUserBmi();
                String userAge = bmiResultModel.getUserAge() + " years";
                String userGender = bmiResultModel.getUserGender() > 0 ? "Female" : "Male";
                String userWeight = bmiResultModel.getUserWeightKg() + "." + bmiResultModel.getUserWeightGm();
                String userHeight = bmiResultModel.getUserHeightFt() + "." + bmiResultModel.getUserHeightIn();

                listLayoutShowBmi.setText(userBmi);
                listLayoutShowAge.setText(userAge);
                listLayoutShowGender.setText(userGender);
                listLayoutShowWeight.setText(userWeight);
                listLayoutShowHeight.setText(userHeight);

                return convertView;
            }
        });
    }
}