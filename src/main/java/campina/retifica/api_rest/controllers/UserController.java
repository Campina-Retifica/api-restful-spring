package campina.retifica.api_rest.controllers;

import campina.retifica.api_rest.business.user.UserService;
import campina.retifica.api_rest.controllers.dtos.in.UserLoginRequest;
import campina.retifica.api_rest.controllers.dtos.out.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserLoginRequest body) {
        TokenResponse token = userService.login(body);
        return ResponseEntity.ok(token);
    }
}
