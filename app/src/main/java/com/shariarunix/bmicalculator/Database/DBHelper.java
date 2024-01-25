package com.shariarunix.bmicalculator.Database;

import static com.shariarunix.bmicalculator.StaticName.BMI_ID;
import static com.shariarunix.bmicalculator.StaticName.BMI_TABLE;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_AGE;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_BMI;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_GENDER;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_HEIGHT_FT;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_HEIGHT_IN;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_WEIGHT_GM;
import static com.shariarunix.bmicalculator.StaticName.BMI_USER_WIGHT_KG;
import static com.shariarunix.bmicalculator.StaticName.DB_NAME;
import static com.shariarunix.bmicalculator.StaticName.DB_VERSION;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String BMI_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + BMI_TABLE +
            "(" + BMI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BMI_USER_AGE + " INTEGER, " + BMI_USER_GENDER + " INTEGER, " +
            BMI_USER_WIGHT_KG + " INTEGER, " + BMI_USER_WEIGHT_GM + " INTEGER, " +
            BMI_USER_HEIGHT_FT + " INTEGER, " + BMI_USER_HEIGHT_IN + " INTEGER, " + BMI_USER_BMI + " TEXT);";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(BMI_TABLE_SQL);
        } catch (Exception e){
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
