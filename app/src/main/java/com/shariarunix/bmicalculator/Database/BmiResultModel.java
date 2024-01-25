package com.shariarunix.bmicalculator.Database;

import androidx.annotation.NonNull;

public class BmiResultModel {
    private int userGender, userHeightFt, userHeightIn, userWeightKg, userWeightGm, userAge, _id;
    private String userBmi;

    public BmiResultModel() {
    }

    public BmiResultModel(int userGender,
                          int userHeightFt,
                          int userHeightIn,
                          int userWeightKg,
                          int userWeightGm,
                          int userAge,
                          String userBmi) {

        this.userGender = userGender;
        this.userHeightFt = userHeightFt;
        this.userHeightIn = userHeightIn;
        this.userWeightKg = userWeightKg;
        this.userWeightGm = userWeightGm;
        this.userAge = userAge;
        this.userBmi = userBmi;
    }

    public BmiResultModel(int _id,
                          int userGender,
                          int userAge,
                          int userHeightFt,
                          int userHeightIn,
                          int userWeightKg,
                          int userWeightGm,
                          String userBmi) {

        this.userGender = userGender;
        this.userHeightFt = userHeightFt;
        this.userHeightIn = userHeightIn;
        this.userWeightKg = userWeightKg;
        this.userWeightGm = userWeightGm;
        this.userAge = userAge;
        this._id = _id;
        this.userBmi = userBmi;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public int getUserHeightFt() {
        return userHeightFt;
    }

    public void setUserHeightFt(int userHeightFt) {
        this.userHeightFt = userHeightFt;
    }

    public int getUserHeightIn() {
        return userHeightIn;
    }

    public void setUserHeightIn(int userHeightIn) {
        this.userHeightIn = userHeightIn;
    }

    public int getUserWeightKg() {
        return userWeightKg;
    }

    public void setUserWeightKg(int userWeightKg) {
        this.userWeightKg = userWeightKg;
    }

    public int getUserWeightGm() {
        return userWeightGm;
    }

    public void setUserWeightGm(int userWeightGm) {
        this.userWeightGm = userWeightGm;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserBmi() {
        return userBmi;
    }

    public void setUserBmi(String userBmi) {
        this.userBmi = userBmi;
    }
}
