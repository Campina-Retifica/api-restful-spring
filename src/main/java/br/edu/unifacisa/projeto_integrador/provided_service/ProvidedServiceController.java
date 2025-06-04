package br.edu.unifacisa.projeto_integrador.provided_service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provided-services")
@RequiredArgsConstructor
public class ProvidedServiceController {
    private final ProvidedServiceBusiness service;

    @PostMapping
    public ResponseEntity<ProvidedServiceResponse> save(@RequestBody @Valid ProvidedServiceRequest body) {
        return new ResponseEntity<>(service.save(body), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProvidedServiceResponse>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvidedServiceResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/pay/{id}")
    public ResponseEntity<Void> makePayment(@PathVariable Long id) {
        service.makePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/complete-service/{id}")
    public ResponseEntity<Void> completeService(@PathVariable Long id) {
        service.completeService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}