package br.edu.unifacisa.projeto_integrador.services;

import java.math.BigDecimal;

public record OfferedServiceResponse(String name,
                                     BigDecimal value,
                                     OfferedServiceEnum status) {
    public OfferedServiceResponse(OfferedService offeredService) {
        this(offeredService.getName(), offeredService.getValue(),offeredService.getStatus());
    }
}
