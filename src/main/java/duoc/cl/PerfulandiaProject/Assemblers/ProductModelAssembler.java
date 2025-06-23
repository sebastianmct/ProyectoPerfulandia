package duoc.cl.PerfulandiaProject.Assemblers;

import duoc.cl.PerfulandiaProject.Controller.ProductController;
import duoc.cl.PerfulandiaProject.Model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getProductById(product.getProductId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).getAllProducts()).withRel("get"),
                linkTo(methodOn(ProductController.class).updateProduct(product.getProductId(), product)).withRel("update"),
                linkTo(methodOn(ProductController.class).deleteProduct(product.getProductId())).withRel("delete")
        );
    }
}
