package com.shariarunix.bmicalculator.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.shariarunix.bmicalculator.Database.BmiResultModel;
import com.shariarunix.bmicalculator.Database.BmiTableClass;
import com.shariarunix.bmicalculator.R;

import java.util.Objects;

public class BmiOutputDialog extends Dialog {
    String userMessage, userBmi, userBmiResult, userSuggestion;
    BmiResultModel bmiResultModel;
    Context context;

    public BmiOutputDialog(@NonNull Context context,
                           String userMessage,
                           String userBmi,
                           String userBmiResult,
                           String userSuggestion,
                           BmiResultModel bmiResultModel) {

        super(context);
        this.context = context;
        this.userMessage = userMessage;
        this.userBmi = userBmi;
        this.userBmiResult = userBmiResult;
        this.userSuggestion = userSuggestion;
        this.bmiResultModel = bmiResultModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bmi_output);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        TextView txtDialogBmiUser = findViewById(R.id.txt_dialog_bmi_user);
        TextView txtDialogBmiShowBmi = findViewById(R.id.txt_dialog_bmi_show_bmi);
        TextView txtDialogBmiResult = findViewById(R.id.txt_dialog_bmi_result);
        TextView txtDialogBmiSuggestion = findViewById(R.id.txt_dialog_bmi_suggestion);

        AppCompatButton btnDialogBmiPos = findViewById(R.id.btn_dialog_bmi_pos);

        txtDialogBmiUser.setText(userMessage);
        txtDialogBmiShowBmi.setText(userBmi);
        txtDialogBmiResult.setText(userBmiResult);
        txtDialogBmiSuggestion.setText(userSuggestion);

        btnDialogBmiPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //noinspection resource
                BmiTableClass bmiTableClass = new BmiTableClass(context);

                long rowID = bmiTableClass.insertBmiResult(bmiResultModel);

                if (rowID > 0) {
                    Toast.makeText(context, "Result Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Ups, Got an error", Toast.LENGTH_SHORT).show();
                }

                dismiss();
            }
        });
    }
}
