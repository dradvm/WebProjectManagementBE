package com.WebProjectManagementBE.service;

import com.WebProjectManagementBE.model.NguoiDung;
import com.WebProjectManagementBE.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiDungDetailsService implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        return new User(nguoiDung.getEmail(), nguoiDung.getMatKhau(), List.of(new SimpleGrantedAuthority(nguoiDung.getMaQuyen().getMaQuyen())));
    }
}
