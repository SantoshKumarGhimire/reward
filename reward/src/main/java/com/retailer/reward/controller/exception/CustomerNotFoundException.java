package com.retailer.reward.controller.exception;

public class CustomerNotFoundException extends Exception {
    private int customerId;

    private CustomerNotFoundException(int customerId) {
        this.customerId = customerId;
    }

    public static CustomerNotFoundException createWith(int customerId) {
        return new CustomerNotFoundException(customerId);
    }

    @Override
    public String getMessage() {
        return "CustomerId '" + customerId + "' not found";
    }
}
