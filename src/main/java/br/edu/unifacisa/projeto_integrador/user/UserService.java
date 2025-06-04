package br.edu.unifacisa.projeto_integrador.user;

import br.edu.unifacisa.projeto_integrador.token.TokenService;
import br.edu.unifacisa.projeto_integrador.token.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private Authentication authenticate(String username, String password) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    public TokenDTO login(UserLoginDTO data) {
        var authentication = authenticate(data.username(), data.password());

        var token = tokenService.create((User) authentication.getPrincipal());

        return new TokenDTO(token);
    }
}