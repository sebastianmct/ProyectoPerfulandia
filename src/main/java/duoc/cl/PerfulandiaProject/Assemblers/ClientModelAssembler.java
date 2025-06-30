package duoc.cl.PerfulandiaProject.Assemblers;

import duoc.cl.PerfulandiaProject.Controller.ClientController;
import duoc.cl.PerfulandiaProject.Model.Client;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientModelAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    @Override
    public EntityModel<Client> toModel(Client client) {
        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).getClientById(client.getClientId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).getAllClients()).withRel("get"),
                linkTo(methodOn(ClientController.class).updateClient(client.getClientId(), client)).withRel("update"),
                linkTo(methodOn(ClientController.class).deleteClient(client.getClientId())).withRel("delete")
        );
    }
}
