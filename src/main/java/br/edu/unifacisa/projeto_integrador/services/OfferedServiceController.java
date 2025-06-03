package br.edu.unifacisa.projeto_integrador.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class OfferedServiceController {

    private final OfferedServiceManager service;

    public OfferedServiceController(OfferedServiceManager service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createService (@RequestBody OfferedServiceRequest request) {
        service.createService(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferedServiceResponse> findById (@PathVariable Long id) {
        OfferedServiceResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<OfferedServiceResponse>> AllService (Pageable pageable) {
        Page<OfferedServiceResponse> responses = service.findAllService(pageable);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateService (@PathVariable Long id,
                                               @RequestBody OfferedServiceRequestUpdate requestUpdate) {
        service.updateService(id,requestUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelService (@PathVariable Long id) {
        service.cancelService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
