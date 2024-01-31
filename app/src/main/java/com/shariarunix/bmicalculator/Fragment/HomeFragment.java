package com.shariarunix.bmicalculator.Fragment;

import static com.shariarunix.bmicalculator.StaticName.SHARED_PREF_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shariarunix.bmicalculator.Database.BmiResultModel;
import com.shariarunix.bmicalculator.Dialog.BmiOutputDialog;
import com.shariarunix.bmicalculator.Dialog.NoInputDialog;
import com.shariarunix.bmicalculator.R;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    int userAge, userWeightKG, userWeightGM, userHeightFT, userHeightIN, userGender; // 0 Male :: 1 Female
    boolean isIncrementing = false, isDecrementing = false, isGenderSelected = false;
    Handler myHandler = new Handler();
    TextView txtShowWeightKg, txtShowHeightFT;

    SharedPreferences sharedPreferences;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", null);

        TextView txtShowAge = view.findViewById(R.id.txt_show_age);
        ImageView imgBtnAgeDec = view.findViewById(R.id.img_btn_age_dec);
        ImageView imgBtnAgeInc = view.findViewById(R.id.img_btn_age_inc);

        txtShowWeightKg = view.findViewById(R.id.txt_show_weight_kg);
        ImageView imgBtnWeightKGDec = view.findViewById(R.id.img_btn_weight_kg_dec);
        ImageView imgBtnWeightKGInc = view.findViewById(R.id.img_btn_weight_kg_inc);

        TextView txtShowWeightGm = view.findViewById(R.id.txt_show_weight_gm);
        ImageView imgBtnWeightGmDec = view.findViewById(R.id.img_btn_weight_gm_dec);
        ImageView imgBtnWeightGmInc = view.findViewById(R.id.img_btn_weight_gm_inc);

        txtShowHeightFT = view.findViewById(R.id.txt_show_height_ft);
        ImageView imgBtnHeightFTDec = view.findViewById(R.id.img_btn_height_ft_dec);
        ImageView imgBtnHeightFTInc = view.findViewById(R.id.img_btn_height_ft_inc);

        TextView txtShowHeightIN = view.findViewById(R.id.txt_show_height_in);
        ImageView imgBtnHeightINDec = view.findViewById(R.id.img_btn_height_in_dec);
        ImageView imgBtnHeightINInc = view.findViewById(R.id.img_btn_height_in_inc);

        LinearLayout llBtnMale = view.findViewById(R.id.ll_btn_male);
        LinearLayout llBtnFemale = view.findViewById(R.id.ll_btn_female);

        llBtnMale.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if (isGenderSelected) {
                    llBtnFemale.setBackground(requireActivity().getDrawable(R.drawable.gender_bg_white_sc));
                } else {
                    isGenderSelected = true;
                }
                llBtnMale.setBackground(requireActivity().getDrawable(R.drawable.gender_selected_bg));
                userGender = 0;
            }
        });

        llBtnFemale.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if (isGenderSelected) {
                    llBtnMale.setBackground(requireActivity().getDrawable(R.drawable.gender_bg_white_sc));
                } else {
                    isGenderSelected = true;
                }
                llBtnFemale.setBackground(requireActivity().getDrawable(R.drawable.gender_selected_bg));
                userGender = 1;
            }
        });

        // Age Increment AUTO
        imgBtnAgeInc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userAge = Integer.parseInt(txtShowAge.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startIncrementing(txtShowAge, userAge);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopIncrementing();
                        break;
                }
                return false;
            }
        });

        // Age Decrement AUTO
        imgBtnAgeDec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userAge = Integer.parseInt(txtShowAge.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startDecrementing(txtShowAge, userAge);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopDecrementing();
                        break;
                }
                return false;
            }
        });

        // Age Increment SINGLE
        imgBtnAgeInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAge = Integer.parseInt(txtShowAge.getText().toString());
                if (userAge < 60) {
                    userAge++;
                    txtShowAge.setText(String.valueOf(userAge));
                }
            }
        });

        // Age Decrement SINGLE
        imgBtnAgeDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAge = Integer.parseInt(txtShowAge.getText().toString());
                if (userAge > 5) {
                    userAge--;
                    txtShowAge.setText(String.valueOf(userAge));
                }
            }
        });

        // WeightKG Increment AUTO
        imgBtnWeightKGInc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startIncrementing(txtShowWeightKg, userWeightKG);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopIncrementing();
                        break;
                }
                return false;
            }
        });

        // WeightKG Decrement AUTO
        imgBtnWeightKGDec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startDecrementing(txtShowWeightKg, userWeightKG);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopDecrementing();
                        break;
                }
                return false;
            }
        });

        // WeightKG Increment SINGLE
        imgBtnWeightKGInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());
                if (userWeightKG < 200) {
                    userWeightKG++;
                    txtShowWeightKg.setText(String.valueOf(userWeightKG));
                }
            }
        });

        // WeightKG Decrement SINGLE
        imgBtnWeightKGDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());
                if (userWeightKG > 10) {
                    userWeightKG--;
                    txtShowWeightKg.setText(String.valueOf(userWeightKG));
                }
            }
        });

        // WightGm Increment Auto
        imgBtnWeightGmInc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userWeightGM = Integer.parseInt(txtShowWeightGm.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startIncrementing(txtShowWeightGm, userWeightGM);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopIncrementing();
                        break;
                }
                return false;
            }
        });

        // WightGm Increment SINGLE
        imgBtnWeightGmInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userWeightGM = Integer.parseInt(txtShowWeightGm.getText().toString());
                if (userWeightGM <= 1000) {
                    if (userWeightKG == 200 && userWeightGM == 990) {
                        return;
                    }
                    userWeightGM += 10;
                    if (userWeightGM == 1000) {
                        userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());
                        userWeightKG++;
                        userWeightGM = 0;
                        txtShowWeightKg.setText(String.valueOf(userWeightKG));
                    }
                    txtShowWeightGm.setText(String.valueOf(userWeightGM));
                }
            }
        });

        // WightGm Decrement AUTO
        imgBtnWeightGmDec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userWeightGM = Integer.parseInt(txtShowWeightGm.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startDecrementing(txtShowWeightGm, userWeightGM);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopDecrementing();
                        break;
                }
                return false;
            }
        });

        // WightGm Decrement SINGLE
        imgBtnWeightGmDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userWeightGM = Integer.parseInt(txtShowWeightGm.getText().toString());
                if (userWeightGM >= 0) {
                    if (userWeightKG == 10 && userWeightGM == 0) {
                        return;
                    }
                    if (userWeightGM == 0) {
                        userWeightKG--;
                        userWeightGM = 1000;
                        txtShowWeightKg.setText(String.valueOf(userWeightKG));
                    }
                    userWeightGM -= 10;
                    txtShowWeightGm.setText(String.valueOf(userWeightGM));
                }
            }
        });

        // HeightFT Increment AUTO
        imgBtnHeightFTInc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startIncrementing(txtShowHeightFT, userHeightFT);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopIncrementing();
                        break;
                }
                return false;
            }
        });

        // HeightFT Increment SINGLE
        imgBtnHeightFTInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                if (userHeightFT < 8) {
                    userHeightFT++;
                    txtShowHeightFT.setText(String.valueOf(userHeightFT));
                }
            }
        });

        // HeightFT Decrement AUTO
        imgBtnHeightFTDec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startDecrementing(txtShowHeightFT, userHeightFT);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopDecrementing();
                        break;
                }
                return false;
            }
        });

        // HeightFT Decrement SINGLE
        imgBtnHeightFTDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                if (userHeightFT > 1) {
                    userHeightFT--;
                    txtShowHeightFT.setText(String.valueOf(userHeightFT));
                }
            }
        });

        // HeightIN Increment AUTO
        imgBtnHeightINInc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userHeightIN = Integer.parseInt(txtShowHeightIN.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startIncrementing(txtShowHeightIN, userHeightIN);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopIncrementing();
                        break;
                }
                return false;
            }
        });

        // HeightIN Increment SINGLE
        imgBtnHeightINInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHeightIN = Integer.parseInt(txtShowHeightIN.getText().toString());
                if (userHeightIN <= 12) { // User height IN
                    if (userHeightFT == 8 && userHeightIN == 11) {
                        return;
                    }
                    userHeightIN++;
                    if (userHeightIN == 12) {
                        userHeightFT++;
                        userHeightIN = 0;
                        txtShowHeightFT.setText(String.valueOf(userHeightFT));
                    }
                    txtShowHeightIN.setText(String.valueOf(userHeightIN));
                }
            }
        });

        // HeightIN Decrement AUTO
        imgBtnHeightINDec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userHeightIN = Integer.parseInt(txtShowHeightIN.getText().toString());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startDecrementing(txtShowHeightIN, userHeightIN);
                        break;
                    case MotionEvent.ACTION_UP:
                        stopDecrementing();
                        break;
                }
                return false;
            }
        });

        // HeightIN Decrement SINGLE
        imgBtnHeightINDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHeightIN = Integer.parseInt(txtShowHeightIN.getText().toString());
                if (userHeightIN >= 0) { // User height IN
                    if (userHeightFT == 1 && userHeightIN == 0) {
                        return;
                    }
                    if (userHeightIN == 0) {
                        userHeightFT--;
                        userHeightIN = 12;
                        txtShowHeightFT.setText(String.valueOf(userHeightFT));
                    }
                    userHeightIN--;
                    txtShowHeightIN.setText(String.valueOf(userHeightIN));
                }
            }
        });

        // Calculate BMI
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity myActivity = (AppCompatActivity) getActivity();
            // Floating Action Button for Calculating BMI
            FloatingActionButton calcBMI = myActivity.findViewById(R.id.f_btn_bmi_calc);

            calcBMI.setBackgroundTintList(ColorStateList.valueOf(myActivity.getColor(R.color.blue)));
            calcBMI.setImageResource(R.drawable.bmi_calc_logo);

            calcBMI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isGenderSelected) {
                        userAge = Integer.parseInt(txtShowAge.getText().toString());
                        userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());
                        userWeightGM = Integer.parseInt(txtShowWeightGm.getText().toString());
                        userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                        userHeightIN = Integer.parseInt(txtShowHeightIN.getText().toString());

                        int userWeight = userWeightKG + (userWeightGM / 1000);
                        double userHeight = (userHeightFT * 0.3048) + (userHeightIN * 0.0254);

                        double userBMIDouble = userWeight / Math.pow(userHeight, 2);
                        String userBMI = formatDouble(userBMIDouble);

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

                        BmiOutputDialog bmiDialog = new BmiOutputDialog(myActivity,
                                userMessage,
                                userBMI,
                                userBmiResult,
                                userSuggestion,
                                new BmiResultModel(userGender,
                                        userHeightFT,
                                        userHeightIN,
                                        userWeightKG,
                                        userWeightGM,
                                        userAge,
                                        String.valueOf(System.currentTimeMillis()),
                                        userBMI));
                        bmiDialog.show();
                    } else {
                        NoInputDialog noInputDialog = new NoInputDialog(myActivity, "Please\nchoose your gender");
                        noInputDialog.show();
                    }
                }
            });
        }
    }

    private String formatDouble(double value) {
        DecimalFormat valueFormatter = new DecimalFormat("0.00");
        return valueFormatter.format(value);
    }

    private final IncrementRunnable incrementRunnable = new IncrementRunnable();

    private final DecrementRunnable decrementRunnable = new DecrementRunnable();

    private void startIncrementing(TextView textView, int userData) {
        isIncrementing = true;
        incrementRunnable.userData = userData;
        incrementRunnable.textView = textView;
        myHandler.postDelayed(incrementRunnable, 125);
    }

    private void stopIncrementing() {
        isIncrementing = false;
        myHandler.removeCallbacks(incrementRunnable);
    }

    private void startDecrementing(TextView textView, int userData) {
        isDecrementing = true;
        decrementRunnable.userData = userData;
        decrementRunnable.textView = textView;
        myHandler.postDelayed(decrementRunnable, 125);
    }

    private void stopDecrementing() {
        isDecrementing = false;
        myHandler.removeCallbacks(decrementRunnable);
    }

    private class IncrementRunnable implements Runnable {
        int userData;
        TextView textView;

        public IncrementRunnable() {
            // Default Constructor
        }

        @Override
        public void run() {
            if (isIncrementing) {
                userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());

                int textViewID = textView.getId();
                if (textViewID == R.id.txt_show_age && userData < 60) { // User age
                    userData++;
                } else if (textViewID == R.id.txt_show_weight_kg && userData < 200) { // User weight KG
                    userData++;
                } else if (textViewID == R.id.txt_show_weight_gm && userData <= 1000) { // User wight GM
                    if (userWeightKG == 200 && userData == 990) {
                        return;
                    }
                    userData += 10;
                    if (userData == 1000) {
                        userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());
                        userWeightKG++;
                        userData = 0;
                        txtShowWeightKg.setText(String.valueOf(userWeightKG));
                    }
                } else if (textViewID == R.id.txt_show_height_ft && userData < 8) { // User height FT
                    userData++;
                } else if (textViewID == R.id.txt_show_height_in && userData <= 12) { // User height IN
                    if (userHeightFT == 8 && userData == 11) {
                        return;
                    }
                    userData++;
                    if (userData == 12) {
                        userHeightFT++;
                        userData = 0;
                        txtShowHeightFT.setText(String.valueOf(userHeightFT));
                    }
                }
                textView.setText(String.valueOf(userData));
                myHandler.postDelayed(this, 125);
            }
        }
    }

    private class DecrementRunnable implements Runnable {

        int userData;
        TextView textView;

        public DecrementRunnable() {
            // Default Constructor
        }

        @Override
        public void run() {
            if (isDecrementing) {
                userHeightFT = Integer.parseInt(txtShowHeightFT.getText().toString());
                userWeightKG = Integer.parseInt(txtShowWeightKg.getText().toString());

                int textViewID = textView.getId();
                if (textViewID == R.id.txt_show_age && userData > 5) { // User age
                    userData--;
                } else if (textViewID == R.id.txt_show_weight_kg && userData > 10) { // User weight KG
                    userData--;
                } else if (textViewID == R.id.txt_show_weight_gm && userData >= 0) { // User wight GM
                    if (userWeightKG == 10 && userData == 0) {
                        return;
                    }
                    if (userData == 0) {
                        userWeightKG--;
                        userData = 1000;
                        txtShowWeightKg.setText(String.valueOf(userWeightKG));
                    }
                    userData -= 10;
                } else if (textViewID == R.id.txt_show_height_ft && userData > 1) { // User height FT
                    userData--;
                } else if (textViewID == R.id.txt_show_height_in && userData >= 0) { // User height IN
                    if (userHeightFT == 1 && userData == 0) {
                        return;
                    }
                    if (userData == 0) {
                        userHeightFT--;
                        userData = 12;
                        txtShowHeightFT.setText(String.valueOf(userHeightFT));
                    }
                    userData--;
                }
                textView.setText(String.valueOf(userData));
                myHandler.postDelayed(this, 125);
            }
        }
    }
}