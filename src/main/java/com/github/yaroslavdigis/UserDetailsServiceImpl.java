package com.github.yaroslavdigis;

import com.github.yaroslavdigis.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String iid) throws UsernameNotFoundException {
        User appUser = appUserRepo.findByIid(iid);

        if (appUser == null) {
            throw new UsernameNotFoundException(iid);
        }
        return new org.springframework.security.core.userdetails.User(
                appUser.getIid(),
                appUser.getPassword(),
                emptyList()
        );
    }
}