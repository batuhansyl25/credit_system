package com.batuhan.credit.security;
import com.batuhan.credit.model.entity.User;
import com.batuhan.credit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String idcard) throws UsernameNotFoundException {
        User user = userRepository.findByIdcard(idcard);

        if (user == null) {
            throw new UsernameNotFoundException("User  not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(idcard)
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }

}