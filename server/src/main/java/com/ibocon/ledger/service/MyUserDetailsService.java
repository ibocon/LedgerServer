package com.ibocon.ledger.service;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.ibocon.ledger.repository.user.LedgerUser;
import com.ibocon.ledger.repository.user.LedgerUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final LedgerUserRepository userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LedgerUser user = userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException(email)
        );

        return user;
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        LedgerUser user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException(Long.toString(id))
        );

        return user;
    }
}