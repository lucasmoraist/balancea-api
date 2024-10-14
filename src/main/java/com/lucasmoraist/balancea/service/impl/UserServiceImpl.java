package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateUser;
import com.lucasmoraist.balancea.domain.dto.DataDetailsUser;
import com.lucasmoraist.balancea.domain.dto.DataLoginUser;
import com.lucasmoraist.balancea.domain.dto.TokenDTO;
import com.lucasmoraist.balancea.domain.entity.User;
import com.lucasmoraist.balancea.infra.security.TokenService;
import com.lucasmoraist.balancea.repository.UserRepository;
import com.lucasmoraist.balancea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DataDetailsUser register(DataCreateUser data) {
        var findEmail = this.repository.findByEmail(data.email());
        if(findEmail != null) {
            throw new IllegalArgumentException("Email already registered");
        }

        var user = User.builder()
                .name(data.name())
                .email(data.email())
                .password(passwordEncoder.encode(data.password()))
                .build();

        this.repository.save(user);

        return new DataDetailsUser(user);
    }

    @Override
    public TokenDTO login(DataLoginUser data) {

        var user = this.repository.findByEmail(data.email());

        if(user == null) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var token = tokenService.generateToken(user.getUsername());

        return new TokenDTO(token);
    }
}
