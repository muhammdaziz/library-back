package com.example.libraryback.entity;

import com.example.libraryback.entity.template.AbsUuid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstname;

    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Role role;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        accountNonExpired = accountNonLocked = credentialsNonExpired = true;
        enabled = false;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.isNull(role))
            return new ArrayList<>();
        return role.getPermissions();
    }

    @Override
    public String getUsername() {
        return email;
    }

}
