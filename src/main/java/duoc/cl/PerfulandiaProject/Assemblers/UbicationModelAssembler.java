package duoc.cl.PerfulandiaProject.Assemblers;

import duoc.cl.PerfulandiaProject.Controller.UbicationController;
import duoc.cl.PerfulandiaProject.Model.Ubication;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UbicationModelAssembler implements RepresentationModelAssembler<Ubication, EntityModel<Ubication>> {
    @Override
    public EntityModel<Ubication> toModel(Ubication ubication) {
        return EntityModel.of(ubication,
                linkTo(methodOn(UbicationController.class).getUbicationById(ubication.getUbicationId())).withSelfRel(),
                linkTo(methodOn(UbicationController.class).getAllUbications()).withRel("get"),
                linkTo(methodOn(UbicationController.class).updateUbication(ubication.getUbicationId(), ubication)).withRel("update"),
                linkTo(methodOn(UbicationController.class).deleteUbication(ubication.getUbicationId())).withRel("delete")
        );
    }
}
