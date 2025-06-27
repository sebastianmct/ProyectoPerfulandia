package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Client;
import duoc.cl.PerfulandiaProject.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("test")
@RestController
@RequestMapping("/clients")
public class ClientControllerV2 {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<String> getAllClients() {
        String result = clientService.getAllClients();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getClientById(@PathVariable int id) {
        String result = clientService.getClientById(id);
        if (result == null || result.equals("Cliente no encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        String result = clientService.addClient(client);
        if (result.contains("correctamente")) {
            return ResponseEntity.ok("Cliente agregado");
        } else {
            return ResponseEntity.badRequest().body("Error: datos inválidos");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        String result = clientService.deleteClient(id);
        if (result.equals("Cliente eliminado correctamente")) {
            return ResponseEntity.ok("Cliente eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable int id, @RequestBody Client client) {
        String result = clientService.updateClient(id, client);
        if (result.equals("Cliente actualizado correctamente")) {
            return ResponseEntity.ok("Cliente actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
