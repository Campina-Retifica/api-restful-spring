package campina.retifica.api_rest.business.user;

import campina.retifica.api_rest.controllers.dtos.in.UserLoginRequest;
import campina.retifica.api_rest.controllers.dtos.out.TokenResponse;
import campina.retifica.api_rest.domain.user.User;
import campina.retifica.api_rest.infrastructure.security.TokenService;
import campina.retifica.api_rest.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public TokenResponse login(UserLoginRequest data) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(usernamePasswordToken);

        String token = generateToken(authentication);

        return new TokenResponse(token);
    }

    private String generateToken(Authentication authentication) {
        return tokenService.generateToken((User) authentication.getPrincipal());
    }
}
