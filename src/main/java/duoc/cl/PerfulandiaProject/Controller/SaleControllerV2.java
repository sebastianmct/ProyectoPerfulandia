package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("test")
@RestController
@RequestMapping("/sales")
public class SaleControllerV2 {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<String> getAllSales() {
        String response = saleService.getAllSales();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getSaleById(@PathVariable int id) {
        String response = saleService.getSaleById(id);
        if (response == null || response.toLowerCase().contains("no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> addSale(@RequestBody Sale sale) {
        String response = saleService.addSale(sale);
        if (response.toLowerCase().contains("error") || response.toLowerCase().contains("inválido")) {
            return ResponseEntity.badRequest().body(response); // ✅ devuelve el mensaje de error
        }
        return ResponseEntity.ok("Venta agregada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSale(@PathVariable int id, @RequestBody Sale sale) {
        String response = saleService.updateSale(id, sale);
        if (response.toLowerCase().contains("no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Venta actualizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable int id) {
        String response = saleService.deleteSale(id);
        if (response.toLowerCase().contains("no encontrada")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Venta eliminada");
    }
}
