package org.example.library_management_system.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false,length=20)
    String name;

    @Column(unique = true,nullable = false,length = 30)
    String email;

    @CreationTimestamp
    Date createOn;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties(value = "author")
    List<Book> books;

    @UpdateTimestamp
    Date updateOn;
}
