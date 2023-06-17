package com.example.libraryback.component;

import com.example.libraryback.entity.FileImg;
import com.example.libraryback.entity.Role;
import com.example.libraryback.entity.User;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.example.libraryback.entity.enums.RoleEnum;
import com.example.libraryback.repository.FileRepository;
import com.example.libraryback.repository.RoleRepository;
import com.example.libraryback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final String adminPassword = "123";
    private final String adminUsername = "admin@admin.com";

    private final String superAdminPassword = "123";
    private final String superAdminUsername = "superAdmin@admin.com";

    private final FileRepository fileRepository;

    @Override
    public void run(String... args) {

        if (Objects.equals(ddlMode, "create")) {
            System.out.println("\n\n\n\n\n\n\n\nyou are running with CREATE ddl mode");
            System.out.println("enter 12 to continue");
            Scanner scanner = new Scanner(System.in);

            int input = scanner.nextInt();

            if (!Objects.equals(input, 12))
                System.exit(1);
        }


        if (Objects.equals(ddlMode, "create")) {

            Role roleSuperAdmin = Role
                    .builder()
                    .description("SUPER ADMIN")
                    .name(RoleEnum.SUPER_ROLE_ADMIN.name())
                    .permissions(Set.of(PermissionEnum.values()))
                    .build();

            roleRepository.save(roleSuperAdmin);


            Role roleADMIN = Role
                    .builder()
                    .description("ADMIN")
                    .name(RoleEnum.ROLE_ADMIN.name())
                    .permissions(Set.of(PermissionEnum.ADD_BOOK))
                    .build();

            roleRepository.save(roleADMIN);

            FileImg fileImg = fileRepository.save(new FileImg(
                    UUID.fromString("12345678-1234-1234-1234-123456789013"),
                    "/dw ewrw erew werewr",
                    "dsdssds"
            ));

            User superAdmin = User
                    .builder()
                    .enabled(true)
                    .lastname("Admin")
                    .firstname("Super")
                    .role(roleSuperAdmin)
                    .email(superAdminUsername)
                    .password(passwordEncoder.encode(superAdminPassword))
                    .avatar(fileImg)
                    .build();

            userRepository.save(superAdmin);

            fileImg = fileRepository.save(new FileImg(
                    UUID.fromString("12345678-1234-1234-1234-123456789012"),
                    "/dw ewrw erew werewr",
                    "dsdssds"
            ));

            User admin = User
                    .builder()
                    .enabled(true)
                    .role(roleADMIN)
                    .lastname("Just")
                    .avatar(fileImg)
                    .firstname("Admin")
                    .email(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .build();

            userRepository.save(admin);
        }
    }

}
