package org.example.library_management_system.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    String bookTitle;

    @Column(length = 10,unique = true,nullable = false)
    String bookNo;

    int securityAmount;

    @CreationTimestamp
    Date createOn;

    @UpdateTimestamp
    Date updateOn;

    @ManyToOne
            @JsonIgnore
    User user;

    @OneToMany(mappedBy = "book")
            @JsonIgnore
    List<Transactions> transactions;

    @ManyToOne
            @JsonIgnore
    Author author;

    @Enumerated (value = EnumType.STRING)
    BookType bookType;
}
