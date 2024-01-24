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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.shariarunix.bmicalculator.R;

import java.util.Objects;

public class NoInputDialog extends Dialog {
    String titleMessage;

    public NoInputDialog(@NonNull Context context, String titleMessage) {
        super(context);
        this.titleMessage = titleMessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_no_input);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);

        TextView dialogNoInputTxtTitle = findViewById(R.id.dialog_no_input_txt_title);
        AppCompatButton dialogNoInputBtnPos = findViewById(R.id.dialog_no_input_btn_pos);

        dialogNoInputTxtTitle.setText(titleMessage);

        dialogNoInputBtnPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
