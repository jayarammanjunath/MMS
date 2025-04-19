package com.example.Market.Managenent.System.Item;

import com.example.Market.Managenent.System.Bill.Bill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_no", nullable = false)
    @JsonBackReference

    private Bill bill;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "boxes", nullable = false)
    private int boxes;

    @Column(name = "kg_per_box", nullable = false)
    private double kgPerBox;

    @Column(name = "rate_per_kg", nullable = false)
    private double ratePerKg;

    @Column(name = "total", nullable = false)
    private double total;

    public Item(String name, int boxes, double kgPerBox, double ratePerKg) {
        this.name = name;
        this.boxes = boxes;
        this.kgPerBox = kgPerBox;
        this.ratePerKg = ratePerKg;
        this.total = boxes * kgPerBox * ratePerKg;
    }
}
