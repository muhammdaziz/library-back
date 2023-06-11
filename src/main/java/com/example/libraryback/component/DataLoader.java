package com.example.libraryback.component;

import com.example.libraryback.entity.Role;
import com.example.libraryback.entity.User;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.example.libraryback.entity.enums.RoleEnum;
import com.example.libraryback.repository.RoleRepository;
import com.example.libraryback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final String superAdminUsername = "superAdmin@admin.com";

    private final String superAdminPassword = "123";

    private final String adminUsername = "admin@admin.com";

    private final String adminPassword = "123";

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(ddlMode, "create")) {

            Role roleSuperAdmin = new Role();
            roleSuperAdmin.setName(RoleEnum.SUPER_ROLE_ADMIN.name());
            roleSuperAdmin.setDescription("SUPER ADMIN");
            roleSuperAdmin.setPermissions(Set.of(PermissionEnum.values()));
            roleRepository.save(roleSuperAdmin);


            Role roleADMIN = new Role();
            roleADMIN.setName(RoleEnum.ROLE_ADMIN.name());
            roleADMIN.setDescription("ADMIN");
            roleADMIN.setPermissions(Set.of(PermissionEnum.ADD_BOOK));
            roleRepository.save(roleADMIN);

            User superAdmin = new User(
                    superAdminUsername,
                    passwordEncoder.encode(superAdminPassword));
            superAdmin.setFirstname("AAAAAAAAA");
            superAdmin.setLastname("aaaa");
            superAdmin.setRole(roleSuperAdmin);
            superAdmin.setEnabled(true);

            User admin = new User(
                    adminUsername,
                    passwordEncoder.encode(adminPassword));
            admin.setFirstname("BBBBBBBB");
            admin.setLastname("bbbb");
            admin.setRole(roleADMIN);
            admin.setEnabled(true);


            userRepository.save(superAdmin);
            userRepository.save(admin);
        }
    }


}
