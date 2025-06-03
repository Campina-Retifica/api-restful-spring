package br.edu.unifacisa.projeto_integrador.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    private ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> listAll() {
        return ResponseEntity.ok(clientService.listAll());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(Client client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> searchId(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.searchId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody Client clientUpdate) {
        clientService.updateClient(id, clientUpdate);
        return ResponseEntity.noContent().build();
    }

}
