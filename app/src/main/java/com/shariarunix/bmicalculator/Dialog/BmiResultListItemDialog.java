package com.shariarunix.bmicalculator.Dialog;

import static com.shariarunix.bmicalculator.StaticName.SHARED_PREF_NAME;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.shariarunix.bmicalculator.Database.BmiResultModel;
import com.shariarunix.bmicalculator.R;

import java.util.Objects;

public class BmiResultListItemDialog extends Dialog {

    Context context;
    BmiResultModel bmiResultModel;
    SharedPreferences sharedPreferences;

    public BmiResultListItemDialog(@NonNull Context context, BmiResultModel bmiResultModel) {
        super(context);
        this.context = context;
        this.bmiResultModel = bmiResultModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_bmi_result_list);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", null);

        TextView txtDialogBmiUser = findViewById(R.id.txt_dialog_bmi_user);
        TextView txtDialogBmiShowBmi = findViewById(R.id.txt_dialog_bmi_show_bmi);
        TextView txtDialogBmiResult = findViewById(R.id.txt_dialog_bmi_result);
        TextView txtDialogBmiSuggestion = findViewById(R.id.txt_dialog_bmi_suggestion);

        TextView listLayoutShowAge = findViewById(R.id.dialog_bmi_show_age);
        TextView listLayoutShowGender = findViewById(R.id.dialog_bmi_show_gender);
        TextView listLayoutShowWeight = findViewById(R.id.dialog_bmi_show_weight);
        TextView listLayoutShowHeight = findViewById(R.id.dialog_bmi_show_height);

        AppCompatButton btnPos = findViewById(R.id.btn_dialog_bmi_pos);

        int userWeightKG = bmiResultModel.getUserWeightKg();
        int userWeightGM = bmiResultModel.getUserHeightIn();
        int userHeightFT = bmiResultModel.getUserHeightFt();
        int userHeightIN = bmiResultModel.getUserHeightIn();

        int userWeight = userWeightKG + (userWeightGM / 1000);
        double userHeight = (userHeightFT * 0.3048) + (userHeightIN * 0.0254);

        double userBMIDouble = userWeight / Math.pow(userHeight, 2);

        String userBmiResult = "", userSuggestion = "", userMessage = "";
        int userConsiderAbleWeight = userWeight - (int) Math.round(Math.pow(userHeight, 2) * 23);

        if (userBMIDouble < 18.5) {
            userBmiResult = "Under Weight";
            userConsiderAbleWeight = (int) Math.round(Math.pow(userHeight, 2) * 19) - userWeight;
            userSuggestion = "To improve your health, aim to gain about '" + userConsiderAbleWeight + "' kilograms through a balanced and nutritious lifestyle.";
        } else if (userBMIDouble < 25) {
            userBmiResult = "Normal Weight";
            userSuggestion = "Maintain your current weight by continuing a balanced diet and regular physical activity";
        } else if (userBMIDouble < 30) {
            userBmiResult = "Over Weight";
            userSuggestion = "To lead a healthier lifestyle, consider losing around '" + userConsiderAbleWeight + "' kilograms of weight.";
        } else {
            userBmiResult = "Obesity";
            userSuggestion = "To lead a healthier lifestyle, consider losing around '" + userConsiderAbleWeight + "' kilograms of weight and seek guidance from healthcare professionals.";
        }

        if (userName == null) {
            userMessage = "Your Bmi is";
        } else {
            userMessage += ", Your Bmi is";
        }

        String userBmi = bmiResultModel.getUserBmi();
        String userAge = bmiResultModel.getUserAge() + " years";
        String userGender = bmiResultModel.getUserGender() > 0 ? "Female" : "Male";
        String userWeightSt = bmiResultModel.getUserWeightKg() + "." + bmiResultModel.getUserWeightGm();
        String userHeightSt = bmiResultModel.getUserHeightFt() + "." + bmiResultModel.getUserHeightIn();

        listLayoutShowAge.setText(userAge);
        listLayoutShowGender.setText(userGender);
        listLayoutShowWeight.setText(userWeightSt);
        listLayoutShowHeight.setText(userHeightSt);

        txtDialogBmiShowBmi.setText(userBmi);
        txtDialogBmiUser.setText(userMessage);
        txtDialogBmiResult.setText(userBmiResult);
        txtDialogBmiSuggestion.setText(userSuggestion);

        btnPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
