package duoc.cl.PerfulandiaProject.Service;

import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Model.StockId;
import duoc.cl.PerfulandiaProject.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public String getStock(int productId, int ubicationId) {
        StockId id = new StockId(productId, ubicationId);
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
            return "Cantidad disponible: " + stock.get().getQuantityDisponible();
        } else {
            return "Stock no encontrado";
        }
    }

    public String createStock(Stock stock) {
        StockId id = new StockId(stock.getProductId(), stock.getUbicationId());
        if (stockRepository.existsById(id)) {
            return "El stock para ese producto y ubicación ya existe";
        } else {
            stockRepository.save(stock);
            return "Stock creado correctamente";
        }
    }

    public String updateStock(int productId, int ubicationId, Stock stock) {
        StockId id = new StockId(productId, ubicationId);
        if (stockRepository.existsById(id)) {
            stock.setProductId(productId);
            stock.setUbicationId(ubicationId);
            stockRepository.save(stock);
            return "Stock actualizado correctamente";
        } else {
            return "Stock no encontrado";
        }
    }

    public String deleteStock(int productId, int ubicationId) {
        StockId id = new StockId(productId, ubicationId);
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return "Stock eliminado correctamente";
        } else {
            return "Stock no encontrado";
        }
    }
}