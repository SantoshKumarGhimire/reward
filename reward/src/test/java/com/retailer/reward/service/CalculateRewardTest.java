package com.retailer.reward.service;

import com.retailer.reward.model.Customer;
import com.retailer.reward.model.RewardRequest;
import com.retailer.reward.model.RewardResponse;
import com.retailer.reward.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CalculateRewardTest {
    CalculateReward calculateReward;
    RewardRequest rewardRequest;

    @BeforeEach
    public void init() {
        calculateReward = new CalculateReward();
        rewardRequest = createRewardRequest();
    }

    @Test
    public void runActionWithResult() {
        RewardResponse rewardResponse = calculateReward.runActionWithResult(rewardRequest);

        Assertions.assertEquals(1, rewardResponse.getRewardList().size());
        Assertions.assertEquals(90, rewardResponse.getRewardList().get(0).getTotalRewardPoint());
        Assertions.assertEquals(40, rewardResponse.getRewardList().get(0).getMonthlyReward().get(0).getMonthlyRewardPoint());
    }

    private RewardRequest createRewardRequest() {
        RewardRequest rewardRequest = new RewardRequest();
        rewardRequest.setCustomers(createCustomers());

        return rewardRequest;
    }

    private List<Customer> createCustomers() {
        Customer customer = new Customer();

        customer.setCustomerPhone("(641) 451-3596");
        customer.setCustomerName("Santosh Ghimire");
        customer.setTransactionList(createTransactionList());

        return Arrays.asList(customer);
    }

    private List<Transaction> createTransactionList() {
        return Arrays.asList(createTransaction(BigDecimal.valueOf(90), LocalDate.now()),
                createTransaction(BigDecimal.valueOf(30), LocalDate.now().plusMonths(1)));
    }

    private Transaction createTransaction(BigDecimal transactionAmount, LocalDate transactionDate) {
        Transaction transaction = new Transaction();

        transaction.setTransactionAmount(transactionAmount);
        transaction.setTransactionDate(transactionDate);

        return transaction;
    }
}
