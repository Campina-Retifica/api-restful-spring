package br.edu.unifacisa.projeto_integrador.customer;

import br.edu.unifacisa.projeto_integrador.security.exceptions.DuplicateResourceException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Page<CustomerDTO> listAllCustomers(Pageable pageable) {
        return customerRepository.findAllByStatus(CustomerEnum.ACTIVE, pageable).map(CustomerDTO::new);
    }

    public CustomerDTO searchCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return new CustomerDTO(customer);
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customer) {
        performUniquenessValidation(customer.document(), customer.email(), customer.telephone());

        Customer newCustomer = new Customer(customer);
        Customer savedCustomer = customerRepository.save(newCustomer);
        return new CustomerDTO(savedCustomer);
    }

    @Transactional
    public void updateCustomer(Long id, CustomerUpdateDTO customerUpdate) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        if (customerUpdate.document() != null && !customerUpdate.document().equals(customer.getDocument())) {
            if (customerRepository.existsByDocumentAndStatus(customerUpdate.document(), CustomerEnum.ACTIVE)) {
                throw new DuplicateResourceException("Document already in use");
            }
            customer.setDocument(customerUpdate.document());
        }

        if (customerUpdate.email() != null && !customerUpdate.email().equals(customer.getEmail())) {
            if (customerRepository.existsByEmailAndStatus(customerUpdate.email(), CustomerEnum.ACTIVE)) {
                throw new DuplicateResourceException("Email already in use");
            }
            customer.setEmail(customerUpdate.email());
        }

        if (customerUpdate.telephone() != null && !customerUpdate.telephone().equals(customer.getTelephone())) {
            if (customerRepository.existsByTelephoneAndStatus(customerUpdate.telephone(), CustomerEnum.ACTIVE)) {
                throw new DuplicateResourceException("Telephone already in use");
            }
            customer.setTelephone(customerUpdate.telephone());
        }

        if (customerUpdate.firstName() != null) {
            customer.setFirstName(customerUpdate.firstName());
        }
        if (customerUpdate.middleName() != null) {
            customer.setMiddleName(customerUpdate.middleName());
        }
        if (customerUpdate.lastName() != null) {
            customer.setLastName(customerUpdate.lastName());
        }
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customer.setStatus(CustomerEnum.INACTIVE);
    }

    private void performUniquenessValidation(String document, String email, String telephone) {
        if (customerRepository.existsByDocumentAndStatus(document, CustomerEnum.ACTIVE)) {
            throw new DuplicateResourceException("Document already in use");
        }

        if (customerRepository.existsByEmailAndStatus(email, CustomerEnum.ACTIVE)) {
            throw new DuplicateResourceException("Email already in use");
        }

        if (customerRepository.existsByTelephoneAndStatus(telephone, CustomerEnum.ACTIVE)) {
            throw new DuplicateResourceException("Telephone already in use");
        }
    }
}