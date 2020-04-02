package com.retailer.reward.model;

import java.util.List;

public class Reward {
    private Customer customer;
    private int totalRewardPoint;
    private List<MonthlyReward> monthlyReward;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
