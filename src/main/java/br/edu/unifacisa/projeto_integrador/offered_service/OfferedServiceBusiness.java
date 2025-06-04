package br.edu.unifacisa.projeto_integrador.offered_service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferedServiceBusiness {
    private final OfferedServiceRepository repository;

    @Transactional
    public OfferedServiceDTO createService(OfferedServiceRequest request) {
        OfferedService job = new OfferedService(request);
        OfferedService savedJob = repository.save(job);
        return new OfferedServiceDTO(savedJob);
    }

    public Page<OfferedServiceDTO> findAllServices(Pageable pageable) {
        return repository.findAll(pageable)
                        .map(OfferedServiceDTO::new);
    }

    public OfferedServiceDTO findServiceById(Long id) {
        OfferedService responseId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id: " + id + " not found!"));
        return new OfferedServiceDTO(responseId);
    }

    @Transactional
    public void updateService(Long id, OfferedServiceDTO requestUpdate) {
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
    }
}