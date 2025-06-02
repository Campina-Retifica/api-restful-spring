package br.edu.unifacisa.projeto_integrador.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferedServiceRepository extends JpaRepository<OfferedService, Long> {
    Page<OfferedService> findAll(Pageable pageable);
}
