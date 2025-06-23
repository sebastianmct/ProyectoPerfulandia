package duoc.cl.PerfulandiaProject.Assemblers;

import duoc.cl.PerfulandiaProject.Controller.StockController;
import duoc.cl.PerfulandiaProject.Model.Stock;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StockModelAssembler implements RepresentationModelAssembler<Stock, EntityModel<Stock>> {
    @Override
    public EntityModel<Stock> toModel(Stock stock) {
        return EntityModel.of(stock,
                linkTo(methodOn(StockController.class)
                        .updateStock(stock.getProductId(), stock.getUbicationId(), stock))
                        .withRel("update"),
                linkTo(methodOn(StockController.class)
                        .deleteStock(stock.getProductId(), stock.getUbicationId()))
                        .withRel("delete")
        );
    }
}
