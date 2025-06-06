package br.edu.unifacisa.projeto_integrador.provided_service;

import br.edu.unifacisa.projeto_integrador.offered_service.OfferedServiceEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProvidedServiceResponse(
        Long id,
        String serviceName,
        BigDecimal price,
        String customerName,
        LocalDateTime startDate,
        LocalDateTime endDate,
        OfferedServiceEnum status,
        Boolean paid
) {
    public ProvidedServiceResponse(ProvidedService service) {
        this(service.getId(), service.getService().getName(), service.getService().getValue(), service.getCustomer().getFirstName(), service.getStartDate(), service.getEndDate(), service.getStatus(), service.getPaid());
    }
}