package com.cashrich.coins.controller;

import com.cashrich.coins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/api/coin")
public class CoinController {
    private final UserService userService;

    @Autowired
    public CoinController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<String> getCoinData(@PathVariable Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", "27ab17d1-215f-49e5-9ca4-afd48810c149");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC,ETHLTC",
                HttpMethod.GET,
                entity,
                String.class
        );

        userService.saveUserHistory(userId);

        return response;
    }
}
