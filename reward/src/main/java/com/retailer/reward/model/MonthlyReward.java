package com.retailer.reward.model;

import java.time.LocalDate;

public class MonthlyReward {

    String month;

    int monthlyRewardPoint;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getMonthlyRewardPoint() {
        return monthlyRewardPoint;
    }

    public void setMonthlyRewardPoint(int monthlyRewardPoint) {
        this.monthlyRewardPoint = monthlyRewardPoint;
    }
}
