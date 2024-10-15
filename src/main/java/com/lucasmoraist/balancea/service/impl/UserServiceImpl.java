package com.lucasmoraist.balancea.service.impl;

import com.lucasmoraist.balancea.domain.dto.DataCreateUser;
import com.lucasmoraist.balancea.domain.dto.DataDetailsUser;
import com.lucasmoraist.balancea.domain.dto.DataLoginUser;
import com.lucasmoraist.balancea.domain.dto.TokenDTO;
import com.lucasmoraist.balancea.domain.entity.User;
import com.lucasmoraist.balancea.exceptions.CredentialsException;
import com.lucasmoraist.balancea.exceptions.EmailDuplicateException;
import com.lucasmoraist.balancea.infra.security.TokenService;
import com.lucasmoraist.balancea.repository.UserRepository;
import com.lucasmoraist.balancea.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DataDetailsUser register(DataCreateUser data) {
        log.info("Tentando registrar usuário com email: {}", data.email());

        var findEmail = this.repository.findByEmail(data.email());
        if (findEmail != null) {
            log.warn("Tentativa de registro falhou: email já cadastrado: {}", data.email());
            throw new EmailDuplicateException("Email already registered");
        }

        var user = User.builder()
                .name(data.name())
                .email(data.email())
                .password(passwordEncoder.encode(data.password()))
                .build();

        this.repository.save(user);
        log.info("Usuário registrado com sucesso: {}", user.getEmail());

        return new DataDetailsUser(user);
    }

    @Override
    public TokenDTO login(DataLoginUser data) {
        log.info("Tentando fazer login com email: {}", data.email());

        var user = this.repository.findByEmail(data.email());

        if (user == null) {
            log.warn("Login falhou: credenciais inválidas para email: {}", data.email());
            throw new CredentialsException("Invalid credentials");
        }

        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            log.warn("Login falhou: senha inválida para email: {}", data.email());
            throw new CredentialsException("Invalid credentials");
        }

        var token = tokenService.generateToken(user.getUsername());
        log.info("Login bem-sucedido para usuário: {}", user.getUsername());

        return new TokenDTO(token);
    }

}
