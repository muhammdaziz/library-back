package com.example.libraryback.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Website {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String subtitle;

    @OneToOne
    private FileImg logo;

    @Column
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ElementCollection
    private List<Double> location;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<WebPages> webpages;
}
