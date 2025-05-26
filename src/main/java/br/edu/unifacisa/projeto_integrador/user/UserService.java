package br.edu.unifacisa.projeto_integrador.user;

import br.edu.unifacisa.projeto_integrador.security.JWTService;
import br.edu.unifacisa.projeto_integrador.security.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public TokenDTO login(UserLoginDTO data) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var token = jwtService.create((User) authentication.getPrincipal());

        return new TokenDTO(token);
    }
}