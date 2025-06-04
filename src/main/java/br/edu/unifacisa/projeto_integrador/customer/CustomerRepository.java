package br.edu.unifacisa.projeto_integrador.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Page<Customer> findAllByStatus(CustomerEnum status, Pageable pageable);
    boolean existsByDocumentAndStatus(String document, CustomerEnum status);
    boolean existsByEmailAndStatus(String email, CustomerEnum status);
    boolean existsByTelephoneAndStatus(String telephone, CustomerEnum status);
}