package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("test")
@RestController
@RequestMapping("/stock")
public class StockControllerV2 {

    @Autowired
    private StockService stockService;

    @GetMapping("/{productId}/{ubicationId}")
    public ResponseEntity<String> getStock(@PathVariable int productId, @PathVariable int ubicationId) {
        String response = stockService.getStock(productId, ubicationId);
        if (response == null || response.equals("Stock no encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> createStock(@RequestBody Stock stock) {
        String response = stockService.createStock(stock);
        if (response.toLowerCase().contains("error") || response.toLowerCase().contains("inválida")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok("Stock creado");
    }

    @PutMapping("/{productId}/{ubicationId}")
    public ResponseEntity<String> updateStock(@PathVariable int productId,
                                              @PathVariable int ubicationId,
                                              @RequestBody Stock stock) {
        String response = stockService.updateStock(productId, ubicationId, stock);
        if (response.equals("Stock no encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Stock actualizado");
    }

    @DeleteMapping("/{productId}/{ubicationId}")
    public ResponseEntity<String> deleteStock(@PathVariable int productId, @PathVariable int ubicationId) {
        String response = stockService.deleteStock(productId, ubicationId);
        if (response.equals("Stock no encontrado")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Stock eliminado");
    }
}
