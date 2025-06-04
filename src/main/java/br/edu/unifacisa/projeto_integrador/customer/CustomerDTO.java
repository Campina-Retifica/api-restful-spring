package br.edu.unifacisa.projeto_integrador.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerDTO(
        Long id,
        @NotBlank @Size(max = 30)
        String firstName,
        @NotBlank @Size(max = 50)
        String middleName,
        @NotBlank @Size(max = 65)
        String lastName,
        @NotBlank
        @CPF
        String document,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(max = 35)
        String telephone
) {
    public CustomerDTO(Customer customer) {
        this(customer.getId(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getDocument(), customer.getEmail(), customer.getTelephone());
    }
}