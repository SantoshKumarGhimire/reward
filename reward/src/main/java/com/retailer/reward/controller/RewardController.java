package com.retailer.reward.controller;

import com.retailer.reward.controller.exception.CustomerNotFoundException;
import com.retailer.reward.model.Reward;
import com.retailer.reward.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardController {

    @Autowired
    RewardService rewardService;

    @GetMapping("/customer/{id}/reward")
    public Reward getReward(@PathVariable("id") Integer customerId) throws CustomerNotFoundException {

        return rewardService.calculateReward(customerId);
    }
}
