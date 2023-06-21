package com.example.libraryback.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebPages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    private FileImg image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String link;
}
