package com.example.Market.Managenent.System.AuditLog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    public AuditLog(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
