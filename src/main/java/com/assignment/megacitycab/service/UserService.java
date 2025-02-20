package com.assignment.megacitycab.service;

import com.assignment.megacitycab.model.AppUser;
import com.assignment.megacitycab.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userDetails = user.get();
            return User.builder().username(userDetails.getUsername()).password(userDetails.getPassword()).build();

        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public AppUser register(AppUser appUser) {
      //  appUser.setPassword(encoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
        return appUser;
    }


}
