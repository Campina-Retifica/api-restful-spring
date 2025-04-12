package campina.retifica.api_rest.controllers.dtos.in;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
