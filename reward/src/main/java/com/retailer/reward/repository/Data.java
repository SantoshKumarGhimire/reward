package com.retailer.reward.repository;

import com.retailer.reward.model.Customer;
import com.retailer.reward.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static List<Customer> customerList = Arrays.asList(
            new Customer(1, "Santosh", "(641) 451-3677", "santosh@gmail.com"),
            new Customer(2, "Santosh", "(641) 451-3677", "santosh@gmail.com"),
            new Customer(3, "Santosh", "(641) 451-3677", "santosh@gmail.com"),
            new Customer(4, "Santosh", "(641) 451-3677", "santosh@gmail.com")
    );

    public static List<Transaction> transactionList = Arrays.asList(
            new Transaction(1, 1, LocalDate.of(2020, 1, 1), BigDecimal.valueOf(120)),
            new Transaction(2, 1, LocalDate.of(2020, 2, 2), BigDecimal.valueOf(120)),
            new Transaction(3, 1, LocalDate.of(2020, 3, 3), BigDecimal.valueOf(120)),
            new Transaction(4, 1, LocalDate.of(2020, 3, 1), BigDecimal.valueOf(120)),
            new Transaction(5, 2, LocalDate.of(2020, 1, 5), BigDecimal.valueOf(80)),
            new Transaction(6, 2, LocalDate.of(2020, 1, 6), BigDecimal.valueOf(90)),
            new Transaction(7, 2, LocalDate.of(2020, 1, 6), BigDecimal.valueOf(90))

    );


}
