package com.retailer.reward.model;

import java.util.List;

public class RewardResponse {
    List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
