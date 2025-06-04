package br.edu.unifacisa.projeto_integrador.provided_service;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProvidedServiceRequest(
        @NotNull
        Long serviceId,
        @NotNull
        Long customerId,
        @NotNull
        LocalDateTime startDate,
        @NotNull
        LocalDateTime endDate
) {
}