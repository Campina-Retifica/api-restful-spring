package br.edu.unifacisa.projeto_integrador.services;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record OfferedServiceRequestUpdate(@Nullable
                                          String name,
                                          @Nullable
                                          String description,
                                          @Nullable
                                          BigDecimal value) {
}
