package com.lucasmoraist.balancea.controller.user;

import com.lucasmoraist.balancea.domain.dto.DataLoginUser;
import com.lucasmoraist.balancea.domain.dto.TokenDTO;
import com.lucasmoraist.balancea.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1")
@Tag(name = "User", description = "Operações relacionadas a usuários")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Realiza o login de um usuário", description = "Realiza o login de um usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário logado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para o login"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    })
    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid DataLoginUser data, UriComponentsBuilder uriBuilder) {
        log.info("Iniciando processo de login para o usuário: {}", data.email());

        var token = this.userService.login(data);

        var uri = uriBuilder
                .path("/auth/signin")
                .build()
                .toUri();

        log.info("Usuário logado com sucesso: {}", data.email());
        return ResponseEntity.created(uri).body(token);
    }


}
