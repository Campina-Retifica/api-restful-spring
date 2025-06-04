package br.edu.unifacisa.projeto_integrador.provided_service;

import br.edu.unifacisa.projeto_integrador.customer.Customer;
import br.edu.unifacisa.projeto_integrador.customer.CustomerEnum;
import br.edu.unifacisa.projeto_integrador.customer.CustomerRepository;
import br.edu.unifacisa.projeto_integrador.offered_service.OfferedService;
import br.edu.unifacisa.projeto_integrador.offered_service.OfferedServiceEnum;
import br.edu.unifacisa.projeto_integrador.offered_service.OfferedServiceRepository;
import br.edu.unifacisa.projeto_integrador.security.exceptions.InactiveCustomerException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProvidedServiceBusiness {
    private final ProvidedServiceRepository repository;
    private final OfferedServiceRepository offeredServiceRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public ProvidedServiceResponse save(ProvidedServiceRequest data) {
        OfferedService service = offeredServiceRepository.findById(data.serviceId()).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        Customer customer = customerRepository.findById(data.customerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        validateCustomerStatus(customer);

        ProvidedService newService = new ProvidedService(service, customer, data.startDate(), data.endDate());
        ProvidedService saved = repository.save(newService);

        return new ProvidedServiceResponse(saved);
    }

    public Page<ProvidedServiceResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ProvidedServiceResponse::new);
    }

    public ProvidedServiceResponse findById(Long id) {
        return repository.findById(id).map(ProvidedServiceResponse::new).orElseThrow(() -> new EntityNotFoundException("Service not found"));
    }

    @Transactional
    public void delete(Long id) {
        ProvidedService service = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        service.setStatus(OfferedServiceEnum.CANCELED);
    }

    @Transactional
    public void makePayment(Long id) {
        ProvidedService service = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        service.setPaid(true);
    }

    @Transactional
    public void completeService(Long id) {
        ProvidedService service = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found"));
        service.setStatus(OfferedServiceEnum.COMPLETED);
        service.setEndDate(LocalDateTime.now());
    }

    private void validateCustomerStatus(Customer customer) {
        if (customer.getStatus().equals(CustomerEnum.INACTIVE)) {
            throw new InactiveCustomerException("It is not possible to assign a new service to an inactive customer.");
        }
    }
}