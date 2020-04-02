package com.retailer.reward.service;

import com.retailer.reward.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CalculateReward {

    public RewardResponse calculateReward(RewardRequest rewardRequest) {
        List<Customer> customers = rewardRequest.getCustomers();

        RewardResponse rewardResponse = new RewardResponse();

        customers.stream().forEach(customer -> {
            customer.setTotalRewardPoint(calculateTotalRewardPoint(customer.getTransactionList()));
            customer.setMonthlyReward(calculateMonthlyRewardPoint(customer.getTransactionList()));
        });

        rewardResponse.setCustomers(customers);

        return rewardResponse;
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

    private int getRewardPoint(BigDecimal totalTransactionAmount) {
        int totalRewardPoint = 0;

        if (totalTransactionAmount.compareTo(BigDecimal.valueOf(100)) == 1) {
            totalRewardPoint = 50 + (totalTransactionAmount.intValue() - 100) * 2;
        }
        if (totalTransactionAmount.compareTo(BigDecimal.valueOf(50)) == 1 && totalTransactionAmount.compareTo(BigDecimal.valueOf(100)) == -1) {
            totalRewardPoint = (totalTransactionAmount.intValue() - 50) * 1;
        }

        return totalRewardPoint;
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
