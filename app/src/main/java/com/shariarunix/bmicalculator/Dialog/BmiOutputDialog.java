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

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.shariarunix.bmicalculator.R;

import java.util.Objects;

public class BmiOutputDialog extends Dialog {
    String userMessage, userBmi, userBmiResult, userSuggestion;

    public BmiOutputDialog(@NonNull Context context,
                           String userMessage,
                           String userBmi,
                           String userBmiResult,
                           String userSuggestion) {

        super(context);
        this.userMessage = userMessage;
        this.userBmi = userBmi;
        this.userBmiResult = userBmiResult;
        this.userSuggestion = userSuggestion;
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
                dismiss();
            }
        });
    }
}
