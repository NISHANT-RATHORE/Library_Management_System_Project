package org.example.library_management_system.Model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.library_management_system.enums.UserStatus;
import org.example.library_management_system.enums.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false,length=20)
    String name;

    @Column(unique = true,nullable = false,length = 30)
    String email;

    @Column(unique = true,length = 15)
    String phoneNo;

    String address; //255 length

    @OneToMany(mappedBy = "user")
    List<Book> books;

    @OneToMany(mappedBy = "user")
    List<Transactions> transactions;

    @Enumerated(value = EnumType.STRING)
    UserType userType; // Admin, Student

    @Enumerated
    UserStatus userStatus; //0,1,2

    @CreationTimestamp
    Date createOn;

    @UpdateTimestamp
    Date updateOn;
}
