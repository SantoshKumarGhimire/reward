package com.retailer.reward.service;

import com.retailer.reward.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculateReward {

    public RewardResponse runActionWithResult(RewardRequest rewardRequest) {
        List<Customer> customers = rewardRequest.getCustomers();

        return getResponse(customers);
    }

    private RewardResponse getResponse(List<Customer> customers) {
        RewardResponse rewardResponse = new RewardResponse();

         rewardResponse.setRewardList(customers.stream()
                .map(customer -> generateReward(customer))
                .collect(Collectors.toList()));

        return rewardResponse;
    }

    private Reward generateReward(Customer customer) {
        Reward reward = new Reward();

        reward.setCustomer(getCustomerProperties(customer));
        reward.setTotalRewardPoint(calculateTotalRewardPoint(customer.getTransactionList()));
        reward.setMonthlyReward(calculateMonthlyRewardPoint(customer.getTransactionList()));

        return reward;
    }

    private Customer getCustomerProperties(Customer customer) {
        Customer customerWithReward = new Customer();

        customerWithReward.setCustomerName(customer.getCustomerName());
        customerWithReward.setCustomerEmail(customer.getCustomerEmail());
        customerWithReward.setCustomerPhone(customer.getCustomerPhone());

        return customerWithReward;
    }

    private int calculateTotalRewardPoint(List<Transaction> transactionList) {
        BigDecimal totalTransactionAmount = getTransactionAmount(transactionList);

        return getRewardPoint(totalTransactionAmount);
    }

    private BigDecimal getTransactionAmount(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> transaction.getTransactionAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int getRewardPoint(BigDecimal transactionAmount) {
        int rewardPoint = 0;

        if (transactionAmount.compareTo(BigDecimal.valueOf(100)) > 0) {
            rewardPoint = rewardPointMoreThanHundredTransaction(transactionAmount);
        }

        if (transactionAmount.compareTo(BigDecimal.valueOf(50)) > 0 && transactionAmount.compareTo(BigDecimal.valueOf(100)) < 0) {
            rewardPoint = rewardPointMoreFiftyTransaction(transactionAmount);
        }

        return rewardPoint;
    }

    private int rewardPointMoreThanHundredTransaction(BigDecimal transactionAmount) {
        return rewardPointMoreFiftyTransaction(transactionAmount) + (transactionAmount.intValue() - 100) * 2;
    }

    private int rewardPointMoreFiftyTransaction(BigDecimal transactionAmount) {
        return transactionAmount.intValue() - 50;
    }

    private List<MonthlyReward> calculateMonthlyRewardPoint(List<Transaction> transactionList) {
        List<MonthlyReward> monthlyRewardList = new ArrayList<>();

        Collections.sort(transactionList, Comparator.comparing(Transaction::getTransactionDate));

        Set<String> yearMonth = new HashSet();

        for (Transaction tr : transactionList) {
            BigDecimal oneMonthTransactionAmount = BigDecimal.ZERO;

            if (!yearMonth.contains(tr.getTransactionDate().getYear() + "-" + tr.getTransactionDate().getMonth())) {
                yearMonth.add(tr.getTransactionDate().getYear() + "-" + tr.getTransactionDate().getMonth());

                for (Transaction tr1 : transactionList) {

                    if (tr.getTransactionDate().getMonth() == tr1.getTransactionDate().getMonth() && tr.getTransactionDate().getYear() == tr1.getTransactionDate().getYear()) {
                        oneMonthTransactionAmount = oneMonthTransactionAmount.add(tr1.getTransactionAmount());
                    }
                }
            }
            if (oneMonthTransactionAmount != BigDecimal.ZERO) {
                monthlyRewardList.add(getMonthlyReward(oneMonthTransactionAmount, tr));
            }
        }
        return monthlyRewardList;
    }

    private MonthlyReward getMonthlyReward(BigDecimal oneMonthTransactionAmount, Transaction tr) {
        MonthlyReward monthlyReward = new MonthlyReward();

        monthlyReward.setMonthlyRewardPoint(getRewardPoint(oneMonthTransactionAmount));
        monthlyReward.setMonth(tr.getTransactionDate().getYear() + " " + tr.getTransactionDate().getMonth().toString());

        return monthlyReward;
    }

  /*  private boolean invalidRewardDate(List<LocalDate> dates) {
        LocalDate maxDate = dates.stream()
                .max(Comparator.comparing(LocalDate::toEpochDay))
                .get();


        LocalDate minDate = dates.stream()
                .min(Comparator.comparing(LocalDate::toEpochDay))
                .get();

        Period diff = Period.between(maxDate, minDate);

        return diff.getMonths() > 3;
    }

    private List<LocalDate> getTransactionDates(List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> transaction.getTransactionDate())
                .collect(Collectors.toList());
    }*/
}
