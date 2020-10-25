package com.samcheseny.security.web;

import com.samcheseny.security.dto.UserDto;
import com.samcheseny.security.entity.Role;
import com.samcheseny.security.entity.User;
import com.samcheseny.security.repository.RoleRepository;
import com.samcheseny.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public UsersController(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid
                                             @RequestBody UserDto dto) {

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<String> sentRoles = dto.getRoles()
                .stream()
                .map(Enum::name)
                .map(s -> "ROLE_" + s)
                .collect(Collectors.toSet());

        Set<Role> roles = roleRepository.findByNameIn(sentRoles);
        user.setRoles(roles);

        User saved = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
