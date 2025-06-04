package br.edu.unifacisa.projeto_integrador.offered_service;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OfferedServiceRequest(@NotNull
                                    String name,
                                    @NotNull
                                    String description,
                                    @NotNull
                                    BigDecimal value) {
}