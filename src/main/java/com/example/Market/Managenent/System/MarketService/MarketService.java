package com.example.Market.Managenent.System.MarketService;

import com.example.Market.Managenent.System.AuditLog.AuditLog;
import com.example.Market.Managenent.System.AuditLog.AuditLogRepository;
import com.example.Market.Managenent.System.Bill.Bill;
import com.example.Market.Managenent.System.Bill.BillRepository;
import com.example.Market.Managenent.System.Item.Item;
import com.example.Market.Managenent.System.User.User;
import com.example.Market.Managenent.System.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MarketService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;
    private final AtomicInteger billCounter = new AtomicInteger(1);

    public User registerUser(User user) {
        user.setUserId("USER" + (userRepository.count() + 1));
        return userRepository.save(user);
    }

    public Bill generateBill(String userId, String shopName, String address, List<Item> items) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found!");
        }
        String billNo = "BILL" + String.format("%03d", billCounter.getAndIncrement());
        Bill bill = new Bill(billNo, userId, shopName, address, items);
        billRepository.save(bill);
        auditLogRepository.save(new AuditLog("Bill " + billNo + " generated for User " + userId, LocalDateTime.now().toString()));
        return bill;

    }
    public List<Bill> getBillsByUser(String userId) {
        return billRepository.findByUserId(userId);
    }
    public void printBillSlip(Bill bill) {
        System.out.println(bill.getShopName());
        System.out.println("Address: " + bill.getAddress());
        System.out.println("Bill No: " + bill.getBillNo());
        User user = userRepository.findById(bill.getUserId()).orElse(new User());
        System.out.println("Customer: " + user.getUsername() + " | Phone: " + user.getPhoneNumber());
        System.out.println("--------------------------------------");
        for (Item item : bill.getItems()) {
            System.out.println("Item: " + item.getName() + " | " + item.getBoxes() + " Boxes | " +
                    item.getKgPerBox() + " KG/Box | $" + item.getRatePerKg() + "/KG | Total: $" + item.getTotal());
        }
        System.out.println("--------------------------------------");
        System.out.println("Grand Total: $" + bill.getGrandTotal());
    }

    }

