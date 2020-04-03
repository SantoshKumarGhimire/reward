package com.retailer.reward.repository;

import com.retailer.reward.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RewardRepository {

    public List<Transaction> findAllTransactionByCustomerId(int customerId) {
        return Data.transactionList
                .stream()
                .filter(transaction -> transaction.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }
}
