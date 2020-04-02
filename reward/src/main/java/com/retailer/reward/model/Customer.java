package com.retailer.reward.model;

import java.util.List;
import java.util.UUID;

public class Customer {

    private UUID customerId;

    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;

    private int totalRewardPoint;

    private List<MonthlyReward> monthlyReward;

    private List<Transaction> transactionList;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
