package br.edu.unifacisa.projeto_integrador.client;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> listAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> searchId(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client clientUpdate) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientUpdate.setId(id);
                    return clientRepository.save(clientUpdate);
                });
    }
}
