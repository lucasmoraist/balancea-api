package com.lucasmoraist.balancea.controller.user;

import com.lucasmoraist.balancea.domain.dto.DataCreateUser;
import com.lucasmoraist.balancea.domain.dto.DataDetailsUser;
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
public class CreateUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<DataDetailsUser> signup(@RequestBody @Valid DataCreateUser data, UriComponentsBuilder uriBuilder) {
        var response = this.userService.register(data);

        var uri = uriBuilder
                .path("/auth/signup")
                .build()
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
