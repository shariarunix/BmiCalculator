package com.shariarunix.bmicalculator.Database;

import static com.shariarunix.bmicalculator.StaticName.BMI_ID;
import static com.shariarunix.bmicalculator.StaticName.BMI_TABLE;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_AGE;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_BMI;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_GENDER;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_HEIGHT_FT;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_HEIGHT_IN;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_TIME;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_WEIGHT_GM;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_WIGHT_KG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BmiTableClass extends DBHelper {
    public BmiTableClass(@Nullable Context context) {
        super(context);
    }

    public long insertBmiResult(BmiResultModel bmiResultModel) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(BMI_USER_AGE, bmiResultModel.getUserAge());
        cValues.put(BMI_USER_GENDER, bmiResultModel.getUserGender());
        cValues.put(BMI_USER_WIGHT_KG, bmiResultModel.getUserWeightKg());
        cValues.put(BMI_USER_WEIGHT_GM, bmiResultModel.getUserWeightGm());
        cValues.put(BMI_USER_HEIGHT_FT, bmiResultModel.getUserHeightFt());
        cValues.put(BMI_USER_HEIGHT_IN, bmiResultModel.getUserHeightIn());
        cValues.put(BMI_USER_TIME, bmiResultModel.getUserResultTime());
        cValues.put(BMI_USER_BMI, bmiResultModel.getUserBmi());

        long rowID = sqlDB.insert(BMI_TABLE, null, cValues);

        sqlDB.close();
        return rowID;
    }

    public ArrayList<BmiResultModel> readAllResult() {
        ArrayList<BmiResultModel> allResultList = new ArrayList<>();

        SQLiteDatabase sqlDB = getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqlDB.rawQuery("SELECT * FROM " + BMI_TABLE, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int rowID = cursor.getInt(cursor.getColumnIndex(BMI_ID));
            @SuppressLint("Range") int userGender = cursor.getInt(cursor.getColumnIndex(BMI_USER_GENDER));
            @SuppressLint("Range") int userAge = cursor.getInt(cursor.getColumnIndex(BMI_USER_AGE));
            @SuppressLint("Range") int userHeightFT = cursor.getInt(cursor.getColumnIndex(BMI_USER_HEIGHT_FT));
            @SuppressLint("Range") int userHeightIN = cursor.getInt(cursor.getColumnIndex(BMI_USER_HEIGHT_IN));
            @SuppressLint("Range") int userWeightKG = cursor.getInt(cursor.getColumnIndex(BMI_USER_WIGHT_KG));
            @SuppressLint("Range") int userWeightGM = cursor.getInt(cursor.getColumnIndex(BMI_USER_WEIGHT_GM));
            @SuppressLint("Range") String userResultTime = cursor.getString(cursor.getColumnIndex(BMI_USER_TIME));
            @SuppressLint("Range") String userBmiResult = cursor.getString(cursor.getColumnIndex(BMI_USER_BMI));

            allResultList.add(new BmiResultModel(rowID,
                    userGender,
                    userAge,
                    userHeightFT,
                    userHeightIN,
                    userWeightKG,
                    userWeightGM,
                    userResultTime,
                    userBmiResult));
        }

        sqlDB.close();
        return allResultList;
    }

    public String deleteAllResult() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + BMI_TABLE);
        sqLiteDatabase.close();
        return "All data deleted successfully!";
    }

}
