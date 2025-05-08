package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Client;
import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String addClient(Client client) {
        return clientRepository.addClient(client);
    }

    public String deleteClient(int id) {
        return clientRepository.removeClient(id);
    }

    public String getAllClients() {
        return clientRepository.getAllClients();
    }

    public String getClientById(int id) {
        return clientRepository.getClientById(id);
    }

    public String updateProduct(int id, Client client) {
        return clientRepository.updateClient(id, client);
    }
}
