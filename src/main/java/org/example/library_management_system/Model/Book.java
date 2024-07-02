package org.example.library_management_system.Model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.library_management_system.enums.BookType;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 30)
    String BookTitle;

    @Column(length = 10,unique = true,nullable = false)
    String BookNo;

    int SecurityAmount;

    @CreationTimestamp
    Date createOn;

    @UpdateTimestamp
    Date updateOn;

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "book")
    List<Transactions> transactions;

    @ManyToOne
    Author author;

    @Enumerated (value = EnumType.STRING)
    BookType booktype;
}
