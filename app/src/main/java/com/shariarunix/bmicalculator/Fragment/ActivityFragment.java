package com.shariarunix.bmicalculator.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shariarunix.bmicalculator.BmiListAdapter;
import com.shariarunix.bmicalculator.Database.BmiResultModel;
import com.shariarunix.bmicalculator.Database.BmiTableClass;
import com.shariarunix.bmicalculator.R;

import java.util.ArrayList;
import java.util.Objects;

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
    ArrayList<BmiResultModel> bmiResults;

    /**
     * @noinspection resource
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bmiResults = new BmiTableClass(requireActivity()).readAllResult();

        RecyclerView recListBmi = view.findViewById(R.id.list_show_bmi_results);
        recListBmi.setLayoutManager(new LinearLayoutManager(requireActivity()));

        BmiListAdapter bmiListAdapter = new BmiListAdapter(requireActivity());
        bmiListAdapter.setBmiResultModels(bmiResults);

        recListBmi.setAdapter(bmiListAdapter);

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity myActivity = (AppCompatActivity) getActivity();

            // Floating Action Button for deleting all BMI result
            FloatingActionButton deleteBmiResult = myActivity.findViewById(R.id.f_btn_bmi_calc);

            deleteBmiResult.setBackgroundTintList(ColorStateList.valueOf(myActivity.getColor(R.color.red)));
            deleteBmiResult.setImageResource(R.drawable.ic_delete);

            deleteBmiResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog bmiResultDelete = new Dialog(requireActivity());
                    bmiResultDelete.setContentView(R.layout.dialog_bmi_result_delete);

                    Objects.requireNonNull(bmiResultDelete.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    bmiResultDelete.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    bmiResultDelete.getWindow().setGravity(Gravity.BOTTOM);

                    AppCompatButton btnNo = bmiResultDelete.findViewById(R.id.dialog_bmi_result_btn_neg);
                    AppCompatButton btnYes = bmiResultDelete.findViewById(R.id.dialog_bmi_result_btn_pos);

                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onClick(View v) {
                            BmiTableClass bmiTableClass = new BmiTableClass(myActivity);
                            String deleteResponse = bmiTableClass.deleteAllResult();

                            bmiResults = new BmiTableClass(requireActivity()).readAllResult();
                            bmiListAdapter.setBmiResultModels(bmiResults);
                            bmiListAdapter.notifyDataSetChanged();

                            Toast.makeText(myActivity, deleteResponse, Toast.LENGTH_SHORT).show();
                            bmiResultDelete.dismiss();
                        }
                    });

                    btnNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bmiResultDelete.dismiss();
                        }
                    });

                    bmiResultDelete.show();
                }
            });
        }
    }
}