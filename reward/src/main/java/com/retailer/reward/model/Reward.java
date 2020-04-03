package com.retailer.reward.model;

import java.util.List;

public class Reward {
    private int customerId;
    private int totalRewardPoint;
    private List<MonthlyReward> monthlyReward;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTotalRewardPoint() {
        return totalRewardPoint;
    }

    public void setTotalRewardPoint(int totalRewardPoint) {
        this.totalRewardPoint = totalRewardPoint;
    }

    public List<MonthlyReward> getMonthlyReward() {
        return monthlyReward;
    }

    public void setMonthlyReward(List<MonthlyReward> monthlyReward) {
        this.monthlyReward = monthlyReward;
    }
}
