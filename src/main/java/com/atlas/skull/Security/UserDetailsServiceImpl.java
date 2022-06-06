package com.atlas.skull.Security;

import com.atlas.skull.BaseSkullModel.User;
import com.atlas.skull.BaseSkullRepository.UserRepository;
import com.atlas.skull.Exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new ResourceNotFound(username + "not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isActive(),
                true, true, true, Arrays.stream(user.getUserRoles().getRole()
                .split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
