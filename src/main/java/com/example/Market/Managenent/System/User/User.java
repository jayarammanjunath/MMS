package com.example.Market.Managenent.System.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id

    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "village", nullable = false)
    private String village;

    public User(String userId, String username, String phoneNumber, String village) {
        this.userId = userId;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.village = village;
    }

}


