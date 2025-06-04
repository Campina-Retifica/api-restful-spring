package br.edu.unifacisa.projeto_integrador.offered_service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedServiceRepository extends CrudRepository<OfferedService, Long> {
    Page<OfferedService> findAll(Pageable pageable);
}