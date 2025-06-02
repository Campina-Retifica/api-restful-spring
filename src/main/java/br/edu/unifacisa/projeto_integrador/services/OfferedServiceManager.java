package br.edu.unifacisa.projeto_integrador.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferedServiceManager {
    private final OfferedServiceRepository repository;

    public OfferedServiceManager(OfferedServiceRepository repository) {
        this.repository = repository;
    }

    public void createService (OfferedServiceRequest request) {
        OfferedService job = new OfferedService(request);
        repository.save(job);
    }

    public Page<OfferedServiceResponse> findAllService (Pageable pageable) {
        return repository.findAll(pageable)
                        .map(OfferedServiceResponse::new);
    }

    public OfferedServiceResponse findById (Long id) {
        OfferedService responseId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id: " + id + " not found!"));
        return new OfferedServiceResponse(responseId);
    }

    public void cancelService (Long id) {
        OfferedService cancelId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id: " + id + " not found!"));

        cancelId.setStatus(OfferedServiceEnum.CANCELADO);
        repository.save(cancelId);
    }

    public void updateService(Long id, OfferedServiceRequestUpdate requestUpdate) {
        OfferedService updateId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id: " + id + " not found!"));

        if (requestUpdate.name() != null) {
            updateId.setName(requestUpdate.name());
        }
        if (requestUpdate.description() != null) {
            updateId.setDescription(requestUpdate.description());
        }
        if (requestUpdate.value() != null) {
            updateId.setValue(requestUpdate.value());
        }

        repository.save(updateId);
    }

}
