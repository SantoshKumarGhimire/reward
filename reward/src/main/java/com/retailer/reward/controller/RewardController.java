package com.retailer.reward.controller;

import com.retailer.reward.model.Customer;
import com.retailer.reward.model.RewardRequest;
import com.retailer.reward.model.RewardResponse;
import com.retailer.reward.service.CalculateReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RewardController {

    @Autowired
    CalculateReward calculateReward;

    @PostMapping("/calculateReward")
    public RewardResponse getRewardRequest(@RequestBody RewardRequest rewardRequest) {

        return calculateReward.calculateReward(rewardRequest);
    }
}
