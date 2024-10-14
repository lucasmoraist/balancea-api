package com.lucasmoraist.balancea.controller.user;

import com.lucasmoraist.balancea.domain.dto.DataLoginUser;
import com.lucasmoraist.balancea.domain.dto.TokenDTO;
import com.lucasmoraist.balancea.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid DataLoginUser data, UriComponentsBuilder uriBuilder) {
        var token = this.userService.login(data);

        var uri = uriBuilder
                .path("/auth/signin")
                .build()
                .toUri();

        return ResponseEntity.created(uri).body(token);
    }

}
