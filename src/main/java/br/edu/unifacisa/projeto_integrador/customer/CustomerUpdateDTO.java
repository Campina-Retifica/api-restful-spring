package br.edu.unifacisa.projeto_integrador.customer;

public record CustomerUpdateDTO(
        String firstName,
        String middleName,
        String lastName,
        String document,
        String email,
        String telephone
) {
}