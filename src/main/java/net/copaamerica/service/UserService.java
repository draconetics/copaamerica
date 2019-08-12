package net.copaamerica.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.copaamerica.model.User;
import net.copaamerica.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
