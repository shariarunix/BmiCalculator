package com.shariarunix.bmicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shariarunix.bmicalculator.Database.BmiResultModel;
import com.shariarunix.bmicalculator.Dialog.BmiResultListItemDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class BmiListAdapter extends RecyclerView.Adapter<BmiListAdapter.ViewHolder> {

    ArrayList<BmiResultModel> bmiResultModels;
    Context context;

    public BmiListAdapter(Context context) {
        this.context = context;
    }

    public void setBmiResultModels(ArrayList<BmiResultModel> bmiResultModels) {
        this.bmiResultModels = bmiResultModels;
        Collections.reverse(bmiResultModels);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_show_bmi_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BmiResultModel bmiResultModel = bmiResultModels.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a   MMM dd, yyyy", Locale.getDefault());
        Date resultDate = new Date(Long.parseLong(bmiResultModel.getUserResultTime()));
        String dateAndTime = sdf.format(resultDate);

        String userBmi = bmiResultModel.getUserBmi();
        String userAge = bmiResultModel.getUserAge() + " years";
        String userGender = bmiResultModel.getUserGender() > 0 ? "Female" : "Male";
        String userWeight = bmiResultModel.getUserWeightKg() + "." + bmiResultModel.getUserWeightGm();
        String userHeight = bmiResultModel.getUserHeightFt() + "." + bmiResultModel.getUserHeightIn();
        String userResultTime = "Time : " + dateAndTime;

        holder.listLayoutShowBmi.setText(userBmi);
        holder.listLayoutShowAge.setText(userAge);
        holder.listLayoutShowGender.setText(userGender);
        holder.listLayoutShowWeight.setText(userWeight);
        holder.listLayoutShowHeight.setText(userHeight);
        holder.listLayoutShowTime.setText(userResultTime);

        holder.listLayoutShowBmiDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmiResultListItemDialog bmiResultListItemDialog = new BmiResultListItemDialog(context, bmiResultModel);
                bmiResultListItemDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bmiResultModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listLayoutShowBmi, listLayoutShowAge, listLayoutShowGender, listLayoutShowWeight, listLayoutShowHeight, listLayoutShowTime;
        LinearLayout listLayoutShowBmiDialog;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listLayoutShowBmi = itemView.findViewById(R.id.list_layout_show_bmi);
            listLayoutShowAge = itemView.findViewById(R.id.list_layout_show_age);
            listLayoutShowGender = itemView.findViewById(R.id.list_layout_show_gender);
            listLayoutShowWeight = itemView.findViewById(R.id.list_layout_show_weight);
            listLayoutShowHeight = itemView.findViewById(R.id.list_layout_show_height);
            listLayoutShowTime = itemView.findViewById(R.id.list_layout_show_time);

            listLayoutShowBmiDialog = itemView.findViewById(R.id.list_layout_show_bmi_dialog);
        }
    }
}
