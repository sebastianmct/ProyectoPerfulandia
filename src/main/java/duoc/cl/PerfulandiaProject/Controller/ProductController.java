package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Repository.ProductRepository;
import duoc.cl.PerfulandiaProject.Assemblers.ProductModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CONTROLADOR PARA PRODUCTOS", description = "Gestiona operaciones CRUD para productos disponibles en el sistema.")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductModelAssembler assembler;

    @Operation(summary = "Obtener todos los productos", description = "Lista todos los productos registrados.")
    @GetMapping
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<EntityModel<Product>> products = productRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(products,
                linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }

    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto específico mediante su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido o malformado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable int id) {
        return productRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Product>> addProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity
                .created(linkTo(methodOn(ProductController.class).getProductById(savedProduct.getProductId())).toUri())
                .body(assembler.toModel(savedProduct));
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza los datos de un producto existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setProductName(product.getProductName());
                    existing.setProductDescription(product.getProductDescription());
                    existing.setProductPrice(product.getProductPrice());
                    productRepository.save(existing);
                    return ResponseEntity.ok(assembler.toModel(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "ID inválido o malformado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
