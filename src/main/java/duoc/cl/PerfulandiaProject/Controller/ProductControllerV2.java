package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Product;
import duoc.cl.PerfulandiaProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("test")
@RestController
@RequestMapping("/products")
public class ProductControllerV2 {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        String result = productService.getAllProducts();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable int id) {
        String result = productService.getProductById(id);
        if (result.equals("Producto no encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String result = productService.addProduct(product);
        if (result.contains("correctamente")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body("Error: datos inválidos");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        String result = productService.deleteProduct(id);
        if (result.equals("Producto eliminado correctamente")) {
            return ResponseEntity.ok("Producto eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        String result = productService.updateProduct(id, product);
        if (result.equals("Producto actualizado correctamente")) {
            return ResponseEntity.ok("Producto actualizado");
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }
}
