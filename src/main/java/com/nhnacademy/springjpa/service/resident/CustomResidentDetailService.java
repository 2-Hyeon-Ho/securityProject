package com.nhnacademy.springjpa.service.resident;

import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class CustomResidentDetailService implements UserDetailsService {
    private final ResidentRepository residentRepository;

    public CustomResidentDetailService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id + " not found"));

        return new User(id, resident.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("resident")));
    }

    public Resident findByEmail(String email) {
        Resident resident = residentRepository.findByEmail(email);
        if(Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }
        return resident;
    }
}
