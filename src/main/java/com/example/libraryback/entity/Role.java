package com.example.libraryback.entity;

import com.example.libraryback.entity.enums.PermissionEnum;
import com.example.libraryback.entity.template.AbsUuid;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<PermissionEnum> permissions;
}
