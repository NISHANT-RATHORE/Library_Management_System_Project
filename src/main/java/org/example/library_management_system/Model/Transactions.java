package org.example.library_management_system.Model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.library_management_system.enums.TransactionStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true,nullable = false,length = 30)
    String transactionId;

    @ManyToOne
    Book book;

    @ManyToOne
    User user;

    int settlementAmount;

    @Enumerated(value = EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    Date createOn;

    @UpdateTimestamp
    Date updateOn;
}
