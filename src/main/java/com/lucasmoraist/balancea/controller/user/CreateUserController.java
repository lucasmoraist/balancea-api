package com.lucasmoraist.balancea.controller.user;

import com.lucasmoraist.balancea.domain.dto.DataCreateUser;
import com.lucasmoraist.balancea.domain.dto.DataDetailsUser;
import com.lucasmoraist.balancea.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class CreateUserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para a criação do usuário"),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    })
    @PostMapping("/signup")
    public ResponseEntity<DataDetailsUser> signup(@RequestBody @Valid DataCreateUser data, UriComponentsBuilder uriBuilder) {
        log.info("Iniciando processo de cadastro para o usuário: {}", data.email());

        var response = this.userService.register(data);

        var uri = uriBuilder
                .path("/auth/signup")
                .build()
                .toUri();

        log.info("Usuário cadastrado com sucesso: {}", data.email());
        return ResponseEntity.created(uri).body(response);
    }


}
