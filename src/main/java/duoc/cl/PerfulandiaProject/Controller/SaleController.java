package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public String getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping  // No es necesario especificar "/sales" ya que ya está en el nivel de clase
    public ResponseEntity<String> addSale(@RequestBody Sale sale) {
        String result = saleService.addSale(sale);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public String getSaleById(@PathVariable int id) {
        return saleService.getSaleById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSale(@PathVariable int id){
        return saleService.deleteSale(id);
    }

    @PutMapping("/{id}")
    public String updateSale(@PathVariable int id, @RequestBody Sale sale) {
        return saleService.updateSale(id, sale);
    }
}
