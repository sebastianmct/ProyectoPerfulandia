package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Sale;
import duoc.cl.PerfulandiaProject.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public String getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping
    public String registerSale(@RequestBody Sale sale) {
        return saleService.registerSale(sale);
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
