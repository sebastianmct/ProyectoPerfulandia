package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Client;
import duoc.cl.PerfulandiaProject.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")

public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping
    public String addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable int id){
        return clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable int id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/{id}")
    public String updateClient(@PathVariable int id, @RequestBody Client client){
        return clientService.updateProduct(id, client);
    }
}
