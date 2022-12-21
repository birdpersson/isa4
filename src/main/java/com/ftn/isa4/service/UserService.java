package com.ftn.isa4.service;

import com.ftn.isa4.dto.UserRequest;
import com.ftn.isa4.model.Authority;
import com.ftn.isa4.model.User;
import com.ftn.isa4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorityService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseGet(null);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findByToken(String token) {
        return repository.findByToken(token);
    }

    public User save(UserRequest request) {
        User u = new User();
        u.setUsername(request.getUsername());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setFirstName(request.getFirstname());
        u.setLastName(request.getLastname());
        u.setAddress(request.getAddress());
        u.setCity(request.getCity());
        u.setCountry(request.getCountry());
        u.setPhone(request.getPhone());
        u.setJmbg(request.getJmbg());
        u.setGender(request.getGender());
        u.setOccupation(request.getOccupation());
        u.setEmployment(request.getEmployment());
        u.setRole(User.Role.USER);
        u.setEnabled(false);
        u.setToken(UUID.randomUUID().toString());

        List<Authority> auth = authService.findByName("ROLE_USER");
        u.setAuthorities(auth);

        return repository.save(u);
    }

    public User enable(User u) {
        u.setEnabled(true);
        u.setToken(null);
        return repository.save(u);
    }

    public User edit(User u) {
        return repository.save(u);
    }

}
