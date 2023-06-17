package com.example.libraryback.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE book SET deleted=true WHERE id=?")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title", "author_id"}))
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private Float price;

    private String isbn;

    @Column(nullable = false)
    private String title;

    @OneToOne(optional = false)
    private FileImg image;

    @ManyToOne(optional = false)
    private Author author;

    @Column(nullable = false)
    private String language;

    private boolean deleted;

    private String publisher;

    @OneToOne(optional = false)
    private FileImg document;

    @ManyToOne
    private Discount discount;

    private Date publishedDate;

    @Column(columnDefinition = "text")
    private String description;

    private String editionFormat;
}
