package br.edu.unifacisa.projeto_integrador.provided_service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidedServiceRepository extends CrudRepository<ProvidedService, Long> {
    Page<ProvidedService> findAll(Pageable pageable);
}