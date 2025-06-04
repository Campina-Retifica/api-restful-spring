package br.edu.unifacisa.projeto_integrador.offered_service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class OfferedServiceController {
    private final OfferedServiceBusiness service;

    @PostMapping
    public ResponseEntity<OfferedServiceDTO> createService(@RequestBody @Valid OfferedServiceRequest request) {
        return new ResponseEntity<>(service.createService(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferedServiceDTO> findServiceById(@PathVariable Long id) {
        OfferedServiceDTO response = service.findServiceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<OfferedServiceDTO>> findAllServices(Pageable pageable) {
        Page<OfferedServiceDTO> responses = service.findAllServices(pageable);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateService(@PathVariable Long id, @RequestBody OfferedServiceDTO requestUpdate) {
        service.updateService(id,requestUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}