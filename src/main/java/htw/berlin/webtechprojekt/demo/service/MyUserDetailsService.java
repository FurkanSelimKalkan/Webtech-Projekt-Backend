package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.UserEntity;
import htw.berlin.webtechprojekt.demo.persistence.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private UserTransformer userTransformer;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
