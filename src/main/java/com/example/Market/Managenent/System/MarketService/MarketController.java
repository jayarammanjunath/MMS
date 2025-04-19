package com.example.Market.Managenent.System.MarketService;

import com.example.Market.Managenent.System.Bill.Bill;
import com.example.Market.Managenent.System.Item.Item;
import com.example.Market.Managenent.System.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class MarketController {
    @Autowired
    private MarketService marketService;

    @Value("${shop.name:Sri Ram Market}")
    private String shopName;
    @Value("${shop.address:V. Kota}")
    private String shopAddress;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return marketService.registerUser(user);
    }

    @PostMapping("/bill")
    public Bill generateBill(@RequestParam String userId, @RequestBody List<Item> items) {
        Bill bill = marketService.generateBill(userId, shopName, shopAddress, items);
        marketService.printBillSlip(bill); // Print line by line to console
        return bill;
    }

    @GetMapping("/bills/{userId}")
    public List<Bill> getBillsByUser(@PathVariable String userId) {
        return marketService.getBillsByUser(userId);
    }
}
