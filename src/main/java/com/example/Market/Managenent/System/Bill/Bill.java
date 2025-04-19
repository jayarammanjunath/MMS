package com.example.Market.Managenent.System.Bill;

import com.example.Market.Managenent.System.Item.Item;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
public class Bill {
    @Id
    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items;

    @Column(name = "grand_total", nullable = false)
    private double grandTotal;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    public Bill(String billNo, String userId, String shopName, String address, List<Item> items) {
        this.billNo = billNo;
        this.userId = userId;
        this.shopName = shopName;
        this.address = address;
        this.items = items;
        for (Item item : items) {
            item.setBill(this);
        }
        this.grandTotal = calculateGrandTotal();
        this.timestamp = LocalDateTime.now().toString();
    }

    private double calculateGrandTotal() {
        return items != null ? items.stream().mapToDouble(Item::getTotal).sum() : 0.0;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        if (items != null) {
            for (Item item : items) {
                item.setBill(this);
            }
        }
    }

}
