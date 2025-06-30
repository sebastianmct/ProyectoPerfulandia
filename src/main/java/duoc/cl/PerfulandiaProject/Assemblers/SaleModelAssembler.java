package duoc.cl.PerfulandiaProject.Assemblers;

import duoc.cl.PerfulandiaProject.Controller.SaleController;
import duoc.cl.PerfulandiaProject.Model.Sale;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SaleModelAssembler implements RepresentationModelAssembler<Sale, EntityModel<Sale>> {
    @Override
    public EntityModel<Sale> toModel(Sale sale) {
        return EntityModel.of(sale,
                linkTo(methodOn(SaleController.class).getSaleById(sale.getSaleId())).withSelfRel(),
                linkTo(methodOn(SaleController.class).getAllSales()).withRel("get"),
                linkTo(methodOn(SaleController.class).updateSale(sale.getSaleId(), sale)).withRel("update"),
                linkTo(methodOn(SaleController.class).deleteSale(sale.getSaleId())).withRel("delete")
        );
    }
}
