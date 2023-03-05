package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.RoleType;
import com.tpe.dto.request.RegisterRequest;
import com.tpe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    public void saveUser(RegisterRequest registerRequest){
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());

        String password = registerRequest.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);

        Role role = roleService.getRoleTypeByName(RoleType.ROLE_INSTRUCTOR);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);

    }
}
