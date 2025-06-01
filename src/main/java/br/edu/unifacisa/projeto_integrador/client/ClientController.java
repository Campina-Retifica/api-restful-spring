package br.edu.unifacisa.projeto_integrador.client;

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
    public List<Client> listAll() {
        return clientService.listAll();
    }

    @PostMapping
    public Client createClient(Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> searchId(@PathVariable Long id) {
        return clientService.searchId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientUpdate) {
        return clientService.updateClient(id, clientUpdate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
