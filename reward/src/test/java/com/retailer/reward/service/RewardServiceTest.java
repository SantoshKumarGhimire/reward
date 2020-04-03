package com.retailer.reward.service;

import com.retailer.reward.controller.exception.CustomerNotFoundException;
import com.retailer.reward.model.Customer;
import com.retailer.reward.model.Reward;
import com.retailer.reward.model.Transaction;
import com.retailer.reward.repository.RewardRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest {

    @Mock
    RewardRepository rewardRepository;

    @InjectMocks
    RewardService rewardService;

    @Test
    public void calculateReward_totalReward() throws CustomerNotFoundException {

        Mockito.when(rewardRepository.getCustomerByCustomerId(1)).thenReturn(getCustomer());

        Mockito.when(rewardRepository.findAllTransactionByCustomerId(1)).thenReturn(getTransactionList());

        Reward reward = rewardService.calculateReward(1);

        Assertions.assertEquals(100, reward.getTotalRewardPoint());
    }

    @Test
    public void calculateReward_monthlyReward() throws CustomerNotFoundException {
        Mockito.when(rewardRepository.getCustomerByCustomerId(1)).thenReturn(getCustomer());

        Mockito.when(rewardRepository.findAllTransactionByCustomerId(1)).thenReturn(getTransactionList());

        Reward reward = rewardService.calculateReward(1);

        Assertions.assertEquals(90, reward.getMonthlyReward().get(0).getMonthlyRewardPoint());
        Assertions.assertEquals(10, reward.getMonthlyReward().get(1).getMonthlyRewardPoint());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void calculateReward_customerNotFound() throws CustomerNotFoundException {

        try {
            Mockito.when(rewardRepository.getCustomerByCustomerId(1)).thenReturn(null);

            Reward reward = rewardService.calculateReward(1);
        } catch (CustomerNotFoundException exception) {
            Assertions.assertEquals(true, exception.getMessage().endsWith("CustomerId '" + 1 + "' not found"));

            throw exception;
        }
    }

    private Customer getCustomer() {
        return new Customer(1, "Santosh", "(641) 451-3678", "santosh1@gmail.com");
    }

    private List<Transaction> getTransactionList() {
        return Arrays.asList(
                new Transaction(1, 1, LocalDate.of(2020, 1, 1), BigDecimal.valueOf(120)),
                new Transaction(1, 1, LocalDate.of(2020, 2, 5), BigDecimal.valueOf(30)),
                new Transaction(1, 1, LocalDate.of(2020, 2, 5), BigDecimal.valueOf(30))

        );
    }
}
