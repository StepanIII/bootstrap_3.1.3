package ru.kata.spring.boot_security.demo;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitData {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public InitData(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void createUser() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        role = roleRepository.save(role);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername("stepan");
        user.setPassword(bCryptPasswordEncoder.encode("stepan"));
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

}
