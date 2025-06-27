package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Assemblers.ClientModelAssembler;
import duoc.cl.PerfulandiaProject.Model.Client;
import duoc.cl.PerfulandiaProject.Repository.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "CONTROLADOR PARA CLIENTES", description = "Gestiona operaciones CRUD para los clientes del sistema.")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientModelAssembler assembler;

    @Operation(summary = "Obtener todos los clientes", description = "Lista todos los clientes registrados.")
    @GetMapping
    public CollectionModel<EntityModel<Client>> getAllClients() {
        List<EntityModel<Client>> clients = clientRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clients,
                linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel());
    }

    @Operation(summary = "Obtener cliente por ID", description = "Obtiene un cliente específico mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Client>> getClientById(@PathVariable int id) {
        return clientRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en el sistema.")
    @PostMapping
    public ResponseEntity<EntityModel<Client>> addClient(@RequestBody Client client) {
        Client saved = clientRepository.save(client);
        return ResponseEntity
                .created(linkTo(methodOn(ClientController.class).getClientById(saved.getClientId())).toUri())
                .body(assembler.toModel(saved));
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Client>> updateClient(@PathVariable int id, @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(existing -> {
                    existing.setClientName(client.getClientName());
                    existing.setClientEmail(client.getClientEmail());
                    existing.setClientPassword(client.getClientPassword());
                    existing.setClientPhone(client.getClientPhone());
                    existing.setClientAddress(client.getClientAddress());
                    clientRepository.save(existing);
                    return ResponseEntity.ok(assembler.toModel(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Void>> deleteClient(@PathVariable int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.deleteById(id);

                    EntityModel<Void> response = EntityModel.of(null,
                            linkTo(methodOn(ClientController.class).getAllClients()).withRel("all-clients"),
                            linkTo(methodOn(ClientController.class).addClient(null)).withRel("add-client"));

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
