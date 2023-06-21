package com.example.libraryback.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"author_id", "title"}))
public class News {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private Date date;
    @Column(columnDefinition = "text")
    private String text;
    @ManyToOne
    private User author;
    @Column(nullable = false)
    private String title;
    @OneToOne
    private FileImg image;
}
