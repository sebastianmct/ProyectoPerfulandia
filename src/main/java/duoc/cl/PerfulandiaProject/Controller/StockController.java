package duoc.cl.PerfulandiaProject.Controller;

import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{productId}/{ubicationId}")
    public String getStock(
            @PathVariable int productId,
            @PathVariable int ubicationId) {
        return stockService.getStock(productId, ubicationId);
    }

    @PostMapping
    public String createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    @PutMapping("/{productId}/{ubicationId}")
    public String updateStock(
            @PathVariable int productId,
            @PathVariable int ubicationId,
            @RequestBody Stock stock) {
        return stockService.updateStock(productId, ubicationId, stock);
    }

    @DeleteMapping("/{productId}/{ubicationId}")
    public String deleteStock(
            @PathVariable int productId,
            @PathVariable int ubicationId) {
        return stockService.deleteStock(productId, ubicationId);
    }
}