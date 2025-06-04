package br.edu.unifacisa.projeto_integrador.offered_service;

import java.math.BigDecimal;

public record OfferedServiceDTO(Long id, String name, String description, BigDecimal value) {
    public OfferedServiceDTO(OfferedService offeredService) {
        this(offeredService.getId(), offeredService.getName(), offeredService.getDescription(), offeredService.getValue());
    }
}