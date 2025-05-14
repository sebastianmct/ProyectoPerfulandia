package duoc.cl.PerfulandiaProject.Event;

import duoc.cl.PerfulandiaProject.Model.Stock;
import duoc.cl.PerfulandiaProject.Model.StockId;
import duoc.cl.PerfulandiaProject.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaleEventListener {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ApplicationEventPublisher publisher;

    @EventListener
    public void handleSaleRegistered(SaleRegisteredEvent event) {
        System.out.println("Evento recibido: Venta registrada para el cliente ID: " + event.getClientId());

        // Logic to update stock
        for (int productId : event.getProductIds()) {
            // Assuming ubicationId is 1 for simplicity.  You'll need logic to determine the correct ubicationId.
            StockId stockId = new StockId(productId, 1);
            Optional<Stock> stockOptional = stockRepository.findById(stockId);
            if (stockOptional.isPresent()) {
                Stock stock = stockOptional.get();
                int quantitySold = 1; // Assuming 1 quantity sold per product.  You'll need to get the actual quantity from SalesLine
                int newQuantity = stock.getQuantityDisponible() - quantitySold;
                stock.setQuantityDisponible(newQuantity);
                stockRepository.save(stock);

                // Publish the inventory updated event
                UpdatedInventoryEvent updateEvent = new UpdatedInventoryEvent(productId, 1, newQuantity); // Assuming ubicationId is 1
                publisher.publishEvent(updateEvent);
                System.out.println("Stock actualizado para el producto ID: " + productId + ", Nueva cantidad: " + newQuantity);
            } else {
                System.out.println("Stock no encontrado para el producto ID: " + productId);
            }
        }
    }
}