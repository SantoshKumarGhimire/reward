package com.retailer.reward.service;

import com.retailer.reward.model.MonthlyReward;
import com.retailer.reward.model.Reward;
import com.retailer.reward.model.Transaction;
import com.retailer.reward.repository.RewardRepository;
import com.retailer.reward.service.constant.RewardConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {
    @Autowired
    RewardRepository rewardRepository;

    public Reward calculateReward(int customerId) {

        List<Transaction> transactionList = rewardRepository.findAllTransactionByCustomerId(customerId);

        List<MonthlyReward> monthlyReward = calculateMonthlyReward(transactionList);

        int totalReward = monthlyReward.stream()
                .map(MonthlyReward::getMonthlyRewardPoint)
                .reduce(0, Integer::sum);

        Reward reward = new Reward();

        reward.setCustomerId(customerId);
        reward.setMonthlyReward(monthlyReward);
        reward.setTotalRewardPoint(totalReward);

        return reward;
    }

    private List<MonthlyReward> calculateMonthlyReward(List<Transaction> transactionList) {

        Map<String, List<Transaction>> transactionByMonth = new HashMap<>();

        for (Transaction transaction : transactionList) {
            String month = transaction.getTransactionDate().getMonth().toString();

            if (transactionByMonth.containsKey(month)) {
                transactionByMonth.get(month).add(transaction);
            } else {
                List<Transaction> list = new ArrayList<>();
                list.add(transaction);
                transactionByMonth.put(month, list);
            }
        }

        transactionByMonth.keySet()
                .forEach(mo -> System.out.println(mo + " = " + transactionByMonth.get(mo).size()));

        List<MonthlyReward> monthlyRewardList = transactionByMonth.keySet()
                .stream()
                .map(month -> getMonthlyReward(transactionByMonth.get(month)))
                .collect(Collectors.toList());

        return monthlyRewardList;
    }

    private MonthlyReward getMonthlyReward(List<Transaction> transactions) {
        BigDecimal monthlyTransactionAmount = transactions.stream().map(Transaction::getTransactionAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int rewardPoint = getRewardPoint(monthlyTransactionAmount);

        MonthlyReward monthlyReward = new MonthlyReward();
        String month = transactions.get(0).getTransactionDate().getMonth().toString();
        monthlyReward.setMonth(month);
        monthlyReward.setMonthlyRewardPoint(rewardPoint);

        return monthlyReward;
    }

    private int getRewardPoint(BigDecimal transactionAmount) {
        int rewardPoint = 0;

        if (transactionAmount.compareTo(RewardConstant.MAX_POINT_REWARD_AMOUNT) > 0) {
            BigDecimal pointForMinAmount = RewardConstant.MIN_POINT_REWARD_AMOUNT.multiply(RewardConstant.MIN_POINT);
            BigDecimal pointForMaxAmount = (transactionAmount.subtract(RewardConstant.MAX_POINT_REWARD_AMOUNT)).multiply(RewardConstant.MAX_POINT);

            rewardPoint = pointForMinAmount.add(pointForMaxAmount).intValue();
        }else if(transactionAmount.compareTo(RewardConstant.MIN_POINT_REWARD_AMOUNT) > 0){
            BigDecimal amountForReward  = transactionAmount.subtract(RewardConstant.MIN_POINT_REWARD_AMOUNT);
            BigDecimal pointForMinAmount = amountForReward.multiply(RewardConstant.MIN_POINT);

            rewardPoint = pointForMinAmount.intValue();
        }

        return rewardPoint;
    }
}
