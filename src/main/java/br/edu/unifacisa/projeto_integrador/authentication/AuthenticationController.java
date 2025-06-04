package br.edu.unifacisa.projeto_integrador.authentication;

import br.edu.unifacisa.projeto_integrador.token.TokenDTO;
import br.edu.unifacisa.projeto_integrador.user.UserLoginDTO;
import br.edu.unifacisa.projeto_integrador.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserLoginDTO body) {
        return ResponseEntity.ok(userService.login(body));
    }
}